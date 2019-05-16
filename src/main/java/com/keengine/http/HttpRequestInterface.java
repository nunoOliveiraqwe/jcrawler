package com.keengine.http;

import java.io.IOException;

public interface HttpRequestInterface {

    byte[] getUrl(String... args) throws Exception;

}
