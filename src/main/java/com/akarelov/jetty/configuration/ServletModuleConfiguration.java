package com.akarelov.jetty.configuration;

import com.akarelov.jetty.servlet.author.AddAuthorServlet;
import com.akarelov.jetty.servlet.author.DeleteAuthorServlet;
import com.akarelov.jetty.servlet.author.UpdateAuthorServlet;
import com.google.inject.servlet.ServletModule;

public class ServletModuleConfiguration extends ServletModule {
    @Override
    protected void configureServlets() {
        serve("/author/add").with(AddAuthorServlet.class);
        serve("/author/delete").with(DeleteAuthorServlet.class);
        serve("/author/update").with(UpdateAuthorServlet.class);
    }
}
