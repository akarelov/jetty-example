package com.akarelov.jetty.configuration;

import com.akarelov.jetty.servlet.author.AuthorServlet;
import com.akarelov.jetty.servlet.note.NoteServlet;
import com.google.inject.servlet.ServletModule;

public class ServletModuleConfiguration extends ServletModule {
    @Override
    protected void configureServlets() {
        serve("/authors").with(AuthorServlet.class);
        serve("/notes").with(NoteServlet.class);
    }
}
