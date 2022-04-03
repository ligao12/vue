package com.example;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bean.User;
import com.example.sercice.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class VueApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void text01() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", "1104");
        User one = userService.getOne(queryWrapper);
        System.out.println(one);

    }

}
