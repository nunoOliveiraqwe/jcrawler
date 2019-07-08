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



    public static HttpRequestInterface GetInterface(){
        final String factoryClassName = HttpProperties.instance().getRepositoryFactory();
        try {
            return (HttpRequestInterface) Class.forName(factoryClassName).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(HttpRequestInterface.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


}
