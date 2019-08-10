package com.keengine.http;



import java.lang.reflect.InvocationTargetException;


/**
 * @author nuno
 * /
 * @project crawler
 * @date 10-08-2019
 **/
public final class HttpRequestFactory {


    private HttpRequestFactory(){}



    public static HttpRequestInterface getInterface(AvailableInterfaces.AvailableInterface hInterface) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (HttpRequestInterface) hInterface.getClazz().getConstructors()[0].newInstance();
    }




}
