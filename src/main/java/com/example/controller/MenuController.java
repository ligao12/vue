package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bean.Dict;
import com.example.bean.Menu;
import com.example.common.Constants;
import com.example.common.Result;
import com.example.mapper.DictMapper;
import com.example.sercice.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@RestController
@RequestMapping("/menu")
public class MenuController {


    @Autowired
    private MenuService menuService;

    @Autowired
    private DictMapper dictMapper;


    // 新增和修改
    @PostMapping
    public Result save(@RequestBody Menu menu) {
        // 新增或者更新
        return Result.success(menuService.saveOrUpdate(menu));
    }

    //查询所有
    @GetMapping
    public Result findAll(@RequestParam(defaultValue = "")String name){

        return Result.success(menuService.findMenus(name));
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return menuService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
        return menuService.removeByIds(ids);
    }

    @GetMapping("/icons")
    public Result getIcons() {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
        return Result.success(dictMapper.selectList(queryWrapper));
    }

    // 分页查询 - mybatis-plus的方式
    @GetMapping("/page")
    public IPage<Menu> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String name,
                                @RequestParam(defaultValue = "") String description) {
        IPage<Menu> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        if (!"".equals(description)) {
            queryWrapper.like("description", description);
        }
        return menuService.page(page, queryWrapper);
    }


    @GetMapping("/ids")
    public Result findAllIds() {
        return Result.success(menuService.list().stream().map(Menu::getId));
    }


}
