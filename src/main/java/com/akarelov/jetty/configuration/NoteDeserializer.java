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

import java.io.IOException;

public class NoteDeserializer extends StdDeserializer<Note> {
    private final AuthorDao authorDao;

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
        JsonNode textNode = treeNode.get("text");
        JsonNode authorIdNode = treeNode.get("author_id");
        if (textNode == null || authorIdNode == null) {
            throw new IllegalArgumentException("you passed bad request");

        }
        String text = textNode.asText();
        int authorId = authorIdNode.asInt();
        Author author = authorDao.findById(authorId).orElseThrow(() -> new UserNotExistsException("user not exists"));
        return Note.builder()
                .author(author)
                .text(text)
                .build();
    }
}
