package com.example.dbdemo.service;

import com.example.dbdemo.MyUsers;

import java.util.List;

public interface MyUsersService {
    List<MyUsers> findAll();
    List<MyUsers> findByUsername(String username);

    MyUsers save(MyUsers myUsers);
    List<MyUsers> findByMobile(String mobile);

    long count();

}
