package com.akarelov.jetty.configuration;

import com.akarelov.jetty.dao.impl.AuthorDaoImpl;
import com.akarelov.jetty.dao.impl.NoteDaoImpl;
import com.akarelov.jetty.dao.interfaces.AuthorDao;
import com.akarelov.jetty.dao.interfaces.NoteDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class NonServletModuleConfiguration extends AbstractModule {
    @Override
    protected void configure() {
        bind(AuthorDao.class).to(AuthorDaoImpl.class).in(Scopes.SINGLETON);
        bind(NoteDao.class).to(NoteDaoImpl.class).in(Scopes.SINGLETON);
        bind(ObjectMapper.class).toInstance(ObjectMapperSingleton.getObjectMapper());
    }
}
