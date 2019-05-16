package com.keengine.spider.url;

import org.junit.Test;

import static org.junit.Assert.*;

public class DomainUrlServerTest {

    @Test
    public void TestNonBlocking() {
        DomainUrlServer urlServer = new DomainUrlServer(new NonBlockingUrlBucket());
        String url = urlServer.takeUrl();
        assertNull(url);
        urlServer.putUrl("http://google.pt");
        url = urlServer.takeUrl();
        assertTrue("http://google.pt".equals(url));
    }

}