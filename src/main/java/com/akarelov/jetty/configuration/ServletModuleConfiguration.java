package com.akarelov.jetty.configuration;

import com.akarelov.jetty.servlet.author.AddAuthorServlet;
import com.akarelov.jetty.servlet.author.DeleteAuthorServlet;
import com.google.inject.servlet.ServletModule;

public class ServletModuleConfiguration extends ServletModule {
    @Override
    protected void configureServlets() {
        serve("/add").with(AddAuthorServlet.class);
        serve("/delete").with(DeleteAuthorServlet.class);
    }
}
