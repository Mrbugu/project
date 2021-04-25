package com.lx.service.impl;

import com.lx.dao.UserDao;
import com.lx.dao.impl.UserDaoImpl;
import com.lx.pojo.User;
import com.lx.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }



    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String usrname) {

        if (userDao.queryUserByUsername(usrname) == null){
            //等于null表示没查到，说明可用
            return false;
        }
        return true;
    }
}
