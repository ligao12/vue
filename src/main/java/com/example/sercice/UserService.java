package com.example.sercice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bean.User;
import com.example.common.Result;
import com.example.controller.dto.UserDto;

/**
 *
 */
public interface UserService extends IService<User> {
    //登录
    UserDto login(UserDto userDto);
    //注册
    Result register(UserDto userDto);
}
