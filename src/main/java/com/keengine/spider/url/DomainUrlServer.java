package com.keengine.spider.url;

public class DomainUrlServer {

    private volatile UrlBucket bucket;

    public DomainUrlServer(){
        this.bucket = new BlockingUrlBucket();
    }

    public DomainUrlServer(UrlBucket bucket){
        this.bucket = bucket;
    }

    public String takeUrl(){
        return this.bucket.takeUrl();
    }

    public void putUrl(String url){
        this.bucket.putUrl(url);
    }



}
