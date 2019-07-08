package com.keengine.reader;

import com.keengine.reader.exception.ProviderNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class Registry {

    private static Map<String, Reader> fileReaderMap = new HashMap<>();


    public static void register(String key, Reader reader){
        fileReaderMap.put(key, reader);
    }

    public static Reader get(String key) throws ProviderNotFoundException{
        Reader reader = fileReaderMap.get(key);
        if(reader ==null)
            throw new ProviderNotFoundException("Invalid reader"+key);
        return reader;
    }


}
