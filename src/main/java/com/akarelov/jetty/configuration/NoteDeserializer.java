package com.akarelov.jetty.configuration;

import com.akarelov.jetty.dao.interfaces.AuthorDao;
import com.akarelov.jetty.domain.Author;
import com.akarelov.jetty.domain.Note;
import com.akarelov.jetty.exception.UserNotExistsException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.inject.Guice;
import com.google.inject.Inject;

import java.io.IOException;

public class NoteDeserializer extends StdDeserializer<Note> {
    @Inject
    private AuthorDao authorDao;

    public NoteDeserializer() {
        this(null);
    }

    public NoteDeserializer(Class<?> vc) {
        super(vc);
        authorDao = Guice.createInjector(NonServletModuleConfiguration.getInstance()).getInstance(AuthorDao.class);
    }

    @Override
    public Note deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode treeNode = jp.getCodec().readTree(jp);
        String text = treeNode.get("text").asText();
        int authorId = treeNode.get("author_id").asInt();
        Author author = authorDao.findById(authorId).orElseThrow(() -> new UserNotExistsException("user not exists"));
        return Note.builder()
                .author(author)
                .text(text)
                .build();
    }
}
