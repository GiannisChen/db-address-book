package com.example.dbdemo.repository;


import com.example.dbdemo.AddressStatic;
import com.example.dbdemo.MyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyContactRepository extends JpaRepository<MyContact, Integer> {

    //== select u from contact u where u.name = ? ;
    List<MyContact> findByName(String name);

    //== select u from contact u where u.name like ? ;
    List<MyContact> findByNameLike(String name);

    List<MyContact> findByAddress(String address);

    @Query("select new com.example.dbdemo.AddressStatic(c.address,count(c)) from MyContact as c group by c.address")
    List<AddressStatic> findAllAddress();
}
