package com.keengine.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author nuno
 * /
 * @project crawler
 * @date 08-07-2019
 **/
public interface StreamReader {





    default byte[] readStream(InputStream stream) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] stuff = new byte[1024];
        int readBytes = 0;
        while ((readBytes = stream.read(stuff)) > 0) {
            bout.write(stuff, 0, readBytes);
        }
        return bout.toByteArray();
    }



}
