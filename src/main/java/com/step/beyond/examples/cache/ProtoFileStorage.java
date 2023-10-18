package com.step.beyond.examples.cache;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public final class ProtoFileStorage<T extends MessageLite> implements Cache<T> {

    private final Parser<T> parser;

    private ProtoFileStorage(Parser<T> parser) {
        this.parser = parser;
    }

    public static <T extends MessageLite> ProtoFileStorage<T> withParser(Parser<T> parser) {
        return new ProtoFileStorage<T>(parser);
    }

    @Override
    public boolean put(String key, T data) {
        try {
            data.writeTo(new FileOutputStream(String.format("src/main/resources/%s", key)));
            return true;
        } catch (IOException e) {
            // TODO LOGGING
            return false;
        }
    }

    @Override
    public T get(String key) {
        try {
            return parser.parseFrom(new FileInputStream(String.format("src/main/resources/%s", key)));
        } catch (InvalidProtocolBufferException | FileNotFoundException e) {
            // TODO LOGGING
            return null;
        }
    }
}
