package com.example.dbdemo.service;

import com.example.dbdemo.CallRecords;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecordService {
    List<CallRecords> findAll();
    Page<CallRecords> findAll(Pageable pageable);

    List<CallRecords> findByFrom(String from);
    List<CallRecords> findByTo(String to);

    long count();
    long countAllByFrom(String from);
    long countAllByTo(String to);

    CallRecords save(CallRecords record);
    void delete(int id);
}
