package com.keengine.http;

import java.util.logging.Logger;

public interface HttpRequestInterface {


    Logger LOGGER = Logger.getLogger("HttpRequestInterface");

    byte[] getUrl(HttpParameters parameters) throws Exception;

}
