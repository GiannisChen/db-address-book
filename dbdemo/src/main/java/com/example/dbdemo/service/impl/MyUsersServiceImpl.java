package com.example.dbdemo.service.impl;

import com.example.dbdemo.MyUsers;
import com.example.dbdemo.repository.MyUsersRepository;
import com.example.dbdemo.service.MyUsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyUsersServiceImpl implements MyUsersService {
    @Resource
    private MyUsersRepository myUsersRepository;

    @Override
    public List<MyUsers> findAll() {
        return myUsersRepository.findAll();
    }

    @Override
    public List<MyUsers> findByUsername(String username) {
        return myUsersRepository.findByUsername(username);
    }

    @Override
    public MyUsers save(MyUsers myUsers) {
        return myUsersRepository.save(myUsers);
    }

    @Override
    public List<MyUsers> findByMobile(String mobile) {
        return myUsersRepository.findByMobile(mobile);
    }

    @Override
    public long count() {
        return myUsersRepository.count();
    }
}
