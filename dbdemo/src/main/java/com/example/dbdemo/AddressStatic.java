package com.example.dbdemo;

public class AddressStatic {

    private Long count;
    private String address;

    public AddressStatic() {}

    public AddressStatic(String address, Long count) {
        this.address = address;
        this.count = count;
    }

    public String getAddress() {
        return address;
    }

    public Long getCount() {
        return count;
    }
}
