package com.keengine.spider.url;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlockingUrlBucket implements UrlBucket {

    private BlockingQueue<String> urls;


    public BlockingUrlBucket(){
        this.urls = new LinkedBlockingDeque<>();
    }



    @Override
    public String takeUrl() {
        try {
            return this.urls.take();
        } catch (InterruptedException e) {
            Logger.getLogger(BlockingUrlBucket.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    @Override
    public void putUrl(String url) {
        try {
            this.urls.put(url);
        } catch (InterruptedException e) {
            Logger.getLogger(BlockingUrlBucket.class.getName()).log(Level.SEVERE, null, e);
        }
    }


}
