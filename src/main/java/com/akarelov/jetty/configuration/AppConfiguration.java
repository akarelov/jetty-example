package com.akarelov.jetty.configuration;

import com.google.inject.AbstractModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppConfiguration extends AbstractModule {
    @Override
    protected void configure() {
        bind(Logger.class).toInstance(LoggerFactory.getLogger(this.getClass()));
    }
}
