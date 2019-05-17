package com.keengine.http;

import com.keengine.http.Simple.SimpleHttpGet;
import com.keengine.http.TorProxy.TorHttpGet;
import org.junit.Test;

import static org.junit.Assert.*;

public class HttpRequestMakerTest {

    @Test
    public void TestTorGet() {
//        final TorHttpGet httpRequest = new TorHttpGet();
//        assertNotNull(httpRequest);
//        try {
//            byte[] arr = httpRequest.getUrl("http://google.pt","localhost","9050");
//
//            assertTrue(arr.length>0);
//            String response = new String(arr);
//            assertTrue(!response.isEmpty());
//        } catch (Exception e) {
//            fail(e.getMessage());
//        }
    }

    @Test
    public void TestApacheGet(){
        final SimpleHttpGet httpRequest = new SimpleHttpGet();
        assertNotNull(httpRequest);
        try {
            byte[] arr = httpRequest.getUrl("http://google.pt","Keengine Crawler");
            assertTrue(arr.length>0);
            String response = new String(arr);
            assertTrue(!response.isEmpty());
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @Test
    public void TestGetByInterface(){
        final HttpRequestInterface httpRequestInterface = HttpRequestMaker.GetInterface();
        assertNotNull(httpRequestInterface);
        try {
            byte[] arr = httpRequestInterface.getUrl("http://google.pt","localhost","9050");
            assertTrue(arr.length>0);
            String response = new String(arr);
            assertTrue(!response.isEmpty());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}