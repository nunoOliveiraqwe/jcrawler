package com.keengine.reader;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class JsonReader implements Reader {

    static {
        Registry.register("json",new JsonReader());
    }


    @Override
    public void read(String path) {
        throw new NotImplementedException();
    }
}

