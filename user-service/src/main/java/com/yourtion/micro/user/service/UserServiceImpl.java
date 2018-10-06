package com.yourtion.micro.user.service;

import com.yourtion.micro.thrift.user.UserInfo;
import com.yourtion.micro.thrift.user.UserService;
import org.apache.thrift.TException;

public class UserServiceImpl implements UserService.Iface {

    @Override
    public UserInfo getUserById(int id) throws TException {
        return null;
    }

    @Override
    public UserInfo getUserByName(String username) throws TException {
        return null;
    }

    @Override
    public void regiserUser(UserInfo userInfo) throws TException {

    }
}
