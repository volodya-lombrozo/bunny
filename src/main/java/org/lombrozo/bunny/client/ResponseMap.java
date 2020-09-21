package org.lombrozo.bunny.client;

import org.lombrozo.bunny.message.FutureMessage;
import org.lombrozo.bunny.message.Message;

import java.util.concurrent.ConcurrentHashMap;

public class ResponseMap {

    private final ConcurrentHashMap<String, FutureMessage> map;

    public ResponseMap() {
        this(new ConcurrentHashMap<>(100));
    }

    public ResponseMap(ConcurrentHashMap<String, FutureMessage> map) {
        this.map = map;
    }

    public void put(FutureMessage message){
    }

    public void register(Message message){
    }

}
