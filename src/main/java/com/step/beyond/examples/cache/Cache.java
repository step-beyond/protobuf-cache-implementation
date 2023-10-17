package com.step.beyond.examples.cache;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public final class Cache<T extends MessageLite> {

    private final Parser<T> parser;

    private Cache(Parser<T> parser) {
        this.parser = parser;
    }

    public static <T extends MessageLite> Cache<T> withParser(Parser<T> parser) {
        return new Cache<T>(parser);
    }

    public boolean write(String key, T data) {
        try {
            data.writeTo(new FileOutputStream(String.format("src/main/resources/%s", key)));
            return true;
        } catch (IOException e) {
            // TODO LOGGING
            return false;
        }
    }

    public T read(String key) {
        try {
            return parser.parseFrom(new FileInputStream(String.format("src/main/resources/%s", key)));
        } catch (InvalidProtocolBufferException | FileNotFoundException e) {
            // TODO LOGGING
            return null;
        }
    }
}
