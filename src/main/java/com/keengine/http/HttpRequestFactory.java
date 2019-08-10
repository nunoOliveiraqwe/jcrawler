package com.keengine.http;

import com.keengine.http.simple.SimpleHttpGet;
import com.keengine.http.tor.TorHttpGet;
import com.keengine.http.unit.HtmlUnitHttpGet;
import com.keengine.http.unit.HtmlUnitHttpProxyGet;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * @author nuno
 * /
 * @project crawler
 * @date 10-08-2019
 **/
public class HttpRequestFactory {


    private HashMap<String,Class> interfaces;


    public HttpRequestFactory(){
        this.interfaces = new HashMap<>();
        interfaces.put("SIMPLE", SimpleHttpGet.class);
        interfaces.put("TOR", TorHttpGet.class);
        interfaces.put("HTML_UNIT", HtmlUnitHttpGet.class);
        interfaces.put("HTML_UNIT_PROXY", HtmlUnitHttpProxyGet.class);
    }



    public HttpRequestInterface getInterface(HttpRequestInterface.HTTPINTERFACES hInterface) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (HttpRequestInterface) this.interfaces.get(hInterface.toString()).getConstructors()[0].newInstance();
    }




}
