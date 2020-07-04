package com.example.dbdemo.repository;


import com.example.dbdemo.MyUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MyUsersRepository extends JpaRepository<MyUsers, Integer> {
    List<MyUsers> findByUsername(String username);

    @Query("select count(u) from MyUsers as u")
    long count();

    List<MyUsers> findByMobile(String mobile);
}
