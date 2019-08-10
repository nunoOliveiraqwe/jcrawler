package com.keengine.http;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

/**
 * @author nuno
 * /
 * @project crawler
 * @date 10-08-2019
 **/
public class HttpRequestFactoryTest {

    @Test
    public void getInterface() throws Exception {
        HttpRequestInterface anInterface = HttpRequestFactory.getInterface(AvailableInterfaces.SIMPLE());
        assertNotNull(anInterface);
        String url = "https://www.google.pt/";
        byte[] arr = anInterface.getUrl(new HttpParameters() {
            @Override
            public int getPort() {
                return 0;
            }

            @Override
            public String getUrl() {
                return url;
            }

            @Override
            public String getTorServiceIp() {
                return null;
            }

            @Override
            public String getUserAgent() {
                return "jCrawler";
            }
        });
        assertTrue(arr.length>0);
        String response = new String(arr);
        assertTrue(!response.isEmpty());
    }
}