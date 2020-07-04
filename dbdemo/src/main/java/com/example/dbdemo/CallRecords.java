package com.example.dbdemo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "records")
public class CallRecords {

    @Id
    private int id;

    @Column(nullable = false, length = 12)
    private String from;

    @Column(nullable = false, length = 12)
    private String to;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private String date;

    public void CallRecords() {}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public String getDate() {
        return date;
    }
}
