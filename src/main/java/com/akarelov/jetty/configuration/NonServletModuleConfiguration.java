package com.akarelov.jetty.configuration;

import com.akarelov.jetty.service.Service;
import com.akarelov.jetty.service.ServiceImpl;
import com.akarelov.jetty.servlet.CustomSecondServlet;
import com.akarelov.jetty.servlet.CustomServlet;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NonServletModuleConfiguration extends AbstractModule {
    @Override
    protected void configure() {
        bind(Service.class).to(ServiceImpl.class);
        bind(Logger.class).annotatedWith(Names.named("secondLogger")).toInstance(LoggerFactory.getLogger(CustomSecondServlet.class));
        bind(Logger.class).annotatedWith(Names.named("firstLogger")).toInstance(LoggerFactory.getLogger(CustomServlet.class));
    }
}
