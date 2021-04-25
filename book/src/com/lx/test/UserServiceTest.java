package com.lx.test;

import com.lx.pojo.User;
import com.lx.service.UserService;
import com.lx.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null, "qweqwe", "qweqwe","qwe@fao.com" ));
//        userService.registUser(new User(null, "ccj8", "36d","fao.com" ));

    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "admin", "asd", null)));
        System.out.println(userService.login(new User(null, "admin", "admin", null)));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("admin")){
            System.out.println("用户名已存在！");
        }else {
            System.out.println("用户名可用！");
        }
    }
}