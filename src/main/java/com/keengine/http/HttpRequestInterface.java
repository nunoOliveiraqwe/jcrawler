package com.keengine.http;

import java.util.logging.Logger;

/**
 * @author nuno
 * /
 * @project crawler
 * @date 08-07-2019
 **/
public interface HttpRequestInterface {


    Logger LOGGER = Logger.getLogger("HttpRequestInterface");

    byte[] getUrl(HttpParameters parameters) throws Exception;

}
