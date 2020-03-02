package com.akarelov.jetty.configuration;

import com.akarelov.jetty.dao.impl.AuthorDaoImpl;
import com.akarelov.jetty.dao.impl.NoteDaoImpl;
import com.akarelov.jetty.dao.interfaces.AuthorDao;
import com.akarelov.jetty.dao.interfaces.NoteDao;
import com.akarelov.jetty.service.impl.AuthorServiceImpl;
import com.akarelov.jetty.service.impl.NoteServiceImpl;
import com.akarelov.jetty.service.interfaces.AuthorService;
import com.akarelov.jetty.service.interfaces.NoteService;
import com.akarelov.jetty.validation.ObjectValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class NonServletModuleConfiguration extends AbstractModule {
    private static NonServletModuleConfiguration nonServletModuleConfiguration;

    private NonServletModuleConfiguration() {

    }

    public static NonServletModuleConfiguration getInstance() {
        if (nonServletModuleConfiguration == null) {
            nonServletModuleConfiguration = new NonServletModuleConfiguration();
        }
        return nonServletModuleConfiguration;
    }

    @Override
    protected void configure() {
        bind(AuthorDao.class).to(AuthorDaoImpl.class).in(Scopes.SINGLETON);
        bind(NoteDao.class).to(NoteDaoImpl.class).in(Scopes.SINGLETON);
        bind(ObjectMapper.class).in(Scopes.SINGLETON);
        bind(ObjectValidator.class).in(Scopes.SINGLETON);
        bind(AuthorService.class).to(AuthorServiceImpl.class).in(Scopes.SINGLETON);
        bind(NoteService.class).to(NoteServiceImpl.class).in(Scopes.SINGLETON);
    }
}
