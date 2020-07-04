package com.example.dbdemo.service;



import com.example.dbdemo.AddressStatic;
import com.example.dbdemo.MyContact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MyContactService {
    Optional<MyContact> findById(int id);
    List<MyContact> findAll();
    MyContact save(MyContact newUser);
    void delete(int id);
    //分页
    Page<MyContact> findAll(Pageable pageable);
    //自定义
    List<MyContact> findByName(String name);
    List<MyContact> findByNameLike(String name);
    List<MyContact> findByAddress(String address);
    List<AddressStatic> findAllAddress();
}
