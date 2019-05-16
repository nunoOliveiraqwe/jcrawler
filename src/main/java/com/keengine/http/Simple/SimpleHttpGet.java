package com.keengine.http.Simple;

import com.keengine.http.HttpRequestInterface;
import com.keengine.http.StreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.InputStream;

public class SimpleHttpGet implements HttpRequestInterface,StreamReader {


    @Override
    public byte[] getUrl(String... args) throws Exception {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(args[0]);
        request.addHeader("User-Agent",args[1]);
        HttpResponse response = client.execute(request);

        System.out.println("Response Code : "+ response.getStatusLine().getStatusCode());

        InputStream stream =  response.getEntity().getContent();
        return this.readStream(stream);
    }

}
