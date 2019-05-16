package com.keengine.spider.url;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class NonBlockingUrlBucket implements UrlBucket {

    private Queue<String> urls;


    public NonBlockingUrlBucket(){
        this.urls = new ConcurrentLinkedQueue<>();
    }

    @Override
    public String takeUrl() {
        return this.urls.poll();
    }

    @Override
    public void putUrl(String url) {
        this.urls.add(url);
    }


}
