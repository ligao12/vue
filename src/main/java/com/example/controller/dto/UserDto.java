package com.example.controller.dto;

import com.example.bean.Menu;
import lombok.Data;

import java.util.List;

/**
 *用于接收前端的登录请求参数
 */
@Data
public class UserDto {

    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    //用来检验是否登录
    private String token;
    //该用户拥有的菜单项
    private List<Menu> menus;
    //角色信息
    private String role;
}
