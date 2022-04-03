package com.example.sercice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bean.Menu;

import java.util.List;

/**
 *
 */
public interface MenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
