package com.keengine.http.TorProxy;

import com.keengine.http.HttpRequestInterface;
import com.keengine.http.MarkerParameters;
import com.keengine.http.StreamReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;

public class TorHttpGet implements HttpRequestInterface, StreamReader {

    @Override
    public byte[] getUrl(MarkerParameters parameters) throws IOException {
        SocketAddress sockAddr = new InetSocketAddress(parameters.getTorServiceIp(), parameters.getPort());
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, sockAddr);
        URL url = new URL(parameters.getUrl());
        System.out.println("executing request to " + args[0] + " via " + proxy);
        InputStream in = url.openConnection(proxy).getInputStream();
        return this.readStream(in);
    }


}
