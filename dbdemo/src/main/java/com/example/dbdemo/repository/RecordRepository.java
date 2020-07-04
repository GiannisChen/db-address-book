package com.example.dbdemo.repository;

import com.example.dbdemo.CallRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordRepository extends JpaRepository<CallRecords,Integer> {

    List<CallRecords> findByFrom(String from);

    List<CallRecords> findByTo(String to);

    @Query("select count(c) from CallRecords as c")
    long count();

    long countAllByFrom(String from);
    long countAllByTo(String to);
}
