package com.example.dbdemo.service.impl;


import com.example.dbdemo.AddressStatic;
import com.example.dbdemo.MyContact;
import com.example.dbdemo.repository.MyContactRepository;
import com.example.dbdemo.service.MyContactService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class MyContactServiceImpl implements MyContactService {

    @Resource
    private MyContactRepository myContactRepository;

    @Override
    public Optional<MyContact> findById(int id) {
        return myContactRepository.findById(id);
    }

    @Override
    public List<MyContact> findAll() {
        return myContactRepository.findAll();
    }

    @Override
    public MyContact save(MyContact newUser) {
        return myContactRepository.save(newUser);
    }

    @Override
    public void delete(int id) {
        myContactRepository.deleteById(id);
    }

    @Override
    public Page<MyContact> findAll(Pageable pageable) {
        return myContactRepository.findAll(pageable);
    }

    @Override
    public List<MyContact> findByName(String name) {
        return myContactRepository.findByName(name);
    }

    @Override
    public List<MyContact> findByNameLike(String name) {
        return myContactRepository.findByNameLike(name);
    }

    @Override
    public List<MyContact> findByAddress(String address) {
        return myContactRepository.findByAddress(address);
    }

    @Override
    public List<AddressStatic> findAllAddress() {
        return  myContactRepository.findAllAddress();
    }
}
