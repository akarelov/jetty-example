package com.akarelov.jetty.configuration;

import com.akarelov.jetty.servlet.CustomSecondServlet;
import com.akarelov.jetty.servlet.CustomServlet;
import com.google.inject.servlet.ServletModule;

public class ServletModuleConfiguration extends ServletModule {
    @Override
    protected void configureServlets() {
        bind(CustomServlet.class);
        bind(CustomSecondServlet.class);

        serve("/number/1").with(CustomServlet.class);
        serve("/number/2").with(CustomSecondServlet.class);
    }
}
