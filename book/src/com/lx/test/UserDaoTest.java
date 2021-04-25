package com.lx.test;

import com.lx.dao.UserDao;
import com.lx.dao.impl.UserDaoImpl;
import com.lx.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
        if (userDao.queryUserByUsername("admin") == null){
            System.out.println("用户名可用！");
        }else {
            System.out.println("用户名已存在");
        }

    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("admin", "admin") == null){
            System.out.println("用户名或者密码错误，登陆失败");
        }else {
            System.out.println("登录成功！");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null, "bugu", "123456", "bugu250@qq,com")));
    }
}