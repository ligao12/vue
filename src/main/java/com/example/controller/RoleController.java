package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bean.Role;
import com.example.common.Result;
import com.example.mapper.RoleMenuMapper;
import com.example.sercice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    // 新增和修改
    @PostMapping
    public Result save(@RequestBody Role user) {
        // 新增或者更新
        return Result.success(roleService.saveOrUpdate(user));
    }

    @GetMapping
    public Result findAll(){
        return Result.success(roleService.list());
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return roleService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
        return roleService.removeByIds(ids);
    }

    // 分页查询 - mybatis-plus的方式
    @GetMapping("/page")
    public IPage<Role> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String name,
                                @RequestParam(defaultValue = "") String description) {
        IPage<Role> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        if (!"".equals(description)) {
            queryWrapper.like("description", description);
        }
        return roleService.page(page, queryWrapper);
    }
    //保存菜单管理数据
    @PostMapping("/roleMenu/{roleId}")
    public Result roleMenu(@PathVariable Integer roleId ,@RequestBody List<Integer> menuIds){
        roleService.setRoleMenu(roleId,menuIds);
        return Result.success();
    }
    //获取菜单管理数据
    @GetMapping("/roleMenu/{roleId}")
    public Result getRoleMenu(@PathVariable Integer roleId){

        return Result.success(roleService.getRoleMenu(roleId));
    }


}
