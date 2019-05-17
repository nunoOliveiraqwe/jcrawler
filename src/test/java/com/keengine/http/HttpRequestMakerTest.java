package com.keengine.http;

import com.keengine.http.Simple.SimpleHttpGet;
import com.keengine.http.TorProxy.TorHttpGet;
import org.junit.Assume;
import org.junit.Test;

import static org.junit.Assert.*;

public class HttpRequestMakerTest {

    @Test
    public void TestTorGet() {
        Assume.assumeFalse("Travis CI build ignoring test","TRUE".equalsIgnoreCase(System.getenv("TRAVIS")));
        final TorHttpGet httpRequest = new TorHttpGet();
        assertNotNull(httpRequest);
        try {
            byte[] arr = httpRequest.getUrl("http://google.pt","localhost","9050");

            assertTrue(arr.length>0);
            String response = new String(arr);
            assertTrue(!response.isEmpty());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void TestApacheGet(){
        Assume.assumeFalse("Travis CI build ignoring test","TRUE".equalsIgnoreCase(System.getenv("TRAVIS")));
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
        Assume.assumeFalse("Travis CI build ignoring test","TRUE".equalsIgnoreCase(System.getenv("TRAVIS")));
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