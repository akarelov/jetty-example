package com.akarelov.jetty.service;

public class ServiceImpl implements Service {
    @Override
    public String print() {
        return this.toString();
    }
}
