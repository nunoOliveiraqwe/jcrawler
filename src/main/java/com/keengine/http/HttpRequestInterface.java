package com.keengine.http;


import org.apache.log4j.Logger;

/**
 * @author nuno
 * /
 * @project crawler
 * @date 08-07-2019
 **/
public interface HttpRequestInterface {


    enum HTTPINTERFACES {
        SIMPLE,
        TOR,
        HTML_UNIT,
        HTML_UNIT_PROXY
    }



    Logger LOGGER = Logger.getLogger(HttpRequestInterface.class.getSimpleName());

    byte[] getUrl(HttpParameters parameters) throws Exception;

}
