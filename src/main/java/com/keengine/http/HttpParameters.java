package com.keengine.http;


/**
 * @author nuno
 * /
 * @project crawler
 * @date 08-07-2019
 **/
public interface HttpParameters {


    int getPort();
    String getUrl();
    String getTorServiceIp();
    String getUserAgent();
}
