package com.example.sercice.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bean.Menu;
import com.example.bean.RoleMenu;
import com.example.bean.User;
import com.example.common.Constants;
import com.example.common.Result;
import com.example.controller.dto.UserDto;
import com.example.exception.ServiceException;
import com.example.mapper.RoleMapper;
import com.example.mapper.RoleMenuMapper;
import com.example.mapper.UserMapper;
import com.example.sercice.MenuService;
import com.example.sercice.UserService;
import com.example.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    //这是为了获取关联关系
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private MenuService menuService;
    //用于登录校验
    @Override
    public UserDto login(UserDto userDto) {
        //查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //用户菜单权限
        List<Menu> roleMenu = new ArrayList<>();
        queryWrapper.eq("username", userDto.getUsername())
                .eq("password", userDto.getPassword());
        //这个只能获取单个【多个时报错】
        User one = getOne(queryWrapper);
        if (one != null){
            BeanUtil.copyProperties(one,userDto,true);
            //设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDto.setToken(token);
            //用户角色
            String role = one.getRole();
            Integer roleId = roleMapper.selectByFlag(role);
            //这是为了得到菜单管理的权限
            List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);
            //这是查出系统的所有菜单
            List<Menu> menus = menuService.findMenus("");

            //筛选用户的菜单
            for (Menu menu : menus) {
                if (menuIds.contains(menu.getId())){
                    roleMenu.add(menu);
                }
                List<Menu> children = menu.getChildren();
                children.removeIf(child->!menuIds.contains(child.getId()));
            }

            userDto.setMenus(roleMenu);
            return userDto;
        }else {
            throw new ServiceException(Constants.CODE_500,"用户名或密码错误");
        }
    }

    //实现注册
    @Override
    public Result register(UserDto userDto) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userDto.getUsername());
        User one = getOne(queryWrapper);
        if (one == null){
            one = new User();
            BeanUtil.copyProperties(userDto,one,true);
            save(one);
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名已存在");
        }
        return null;
    }


}
