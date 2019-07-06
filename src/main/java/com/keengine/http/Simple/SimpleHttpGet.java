package com.keengine.http.Simple;

import com.keengine.http.HttpRequestInterface;
import com.keengine.http.MarkerParameters;
import com.keengine.http.StreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.InputStream;

public class SimpleHttpGet implements HttpRequestInterface,StreamReader {


    @Override
    public byte[] getUrl(MarkerParameters parameters) throws Exception {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(parameters.getUrl());
        request.addHeader("User-Agent",parameters.getUserAgent());
        HttpResponse response = client.execute(request);

        System.out.println("Response Code : "+ response.getStatusLine().getStatusCode());

        InputStream stream =  response.getEntity().getContent();
        return this.readStream(stream);
    }

}
