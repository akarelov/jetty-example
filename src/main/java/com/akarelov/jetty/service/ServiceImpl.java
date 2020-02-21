package com.akarelov.jetty.service;

import com.google.inject.Singleton;

@Singleton
public class ServiceImpl implements Service {
    @Override
    public String print() {
        return this.toString();
    }
}
