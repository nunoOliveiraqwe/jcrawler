package com.keengine.http;

import com.keengine.http.simple.SimpleHttpGet;
import com.keengine.http.tor.TorHttpGet;
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
            byte[] arr = httpRequest.getUrl(new HttpParameters() {
                @Override
                public int getPort() {
                    return 9050;
                }

                @Override
                public String getUrl() {
                    return "http://google.pt";
                }

                @Override
                public String getTorServiceIp() {
                    return "localhost";
                }

                @Override
                public String getUserAgent() {
                    return null;
                }
            });

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
            byte[] arr = httpRequest.getUrl(new HttpParameters() {
                @Override
                public int getPort() {
                    return 0;
                }

                @Override
                public String getUrl() {
                    return "http://google.pt";
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
            byte[] arr = httpRequestInterface.getUrl(new HttpParameters() {
                @Override
                public int getPort() {
                    return 9050;
                }

                @Override
                public String getUrl() {
                    return "http://google.pt";
                }

                @Override
                public String getTorServiceIp() {
                    return "localhost";
                }

                @Override
                public String getUserAgent() {
                    return null;
                }
            });
            assertTrue(arr.length>0);
            String response = new String(arr);
            assertTrue(!response.isEmpty());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}