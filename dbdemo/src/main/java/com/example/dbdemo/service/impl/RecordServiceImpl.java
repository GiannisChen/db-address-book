package com.example.dbdemo.service.impl;

import com.example.dbdemo.CallRecords;
import com.example.dbdemo.repository.RecordRepository;
import com.example.dbdemo.service.RecordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Resource
    private RecordRepository recordRepository;

    @Override
    public List<CallRecords> findAll() {
        return recordRepository.findAll();
    }

    @Override
    public Page<CallRecords> findAll(Pageable pageable) {
        return recordRepository.findAll(pageable);
    }

    @Override
    public List<CallRecords> findByFrom(String from) {
        return recordRepository.findByFrom(from);
    }

    @Override
    public List<CallRecords> findByTo(String to) {
        return recordRepository.findByTo(to);
    }

    @Override
    public long count() {
        return recordRepository.count();
    }

    @Override
    public long countAllByFrom(String from) {
        return recordRepository.countAllByFrom(from);
    }

    @Override
    public long countAllByTo(String to) {
        return recordRepository.countAllByFrom(to);
    }

    @Override
    public CallRecords save(CallRecords record) {
        return recordRepository.save(record);
    }

    @Override
    public void delete(int id) {
        recordRepository.deleteById(id);
    }


}
