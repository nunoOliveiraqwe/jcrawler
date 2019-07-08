package com.keengine.http;

import com.keengine.http.properties.HttpProperties;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author nuno
 * /
 * @project crawler
 * @date 08-07-2019
 **/
public class HttpRequestMaker {


    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(HttpRequestInterface.class.getSimpleName());

    public static HttpRequestInterface GetInterface(){
        final String factoryClassName = HttpProperties.instance().getRepositoryFactory();
        try {
            return (HttpRequestInterface) Class.forName(factoryClassName).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            LOGGER.error(ex);
            return null;
        }
    }


}
