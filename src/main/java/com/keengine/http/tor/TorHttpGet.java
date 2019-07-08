package com.keengine.http.tor;

import com.keengine.Crawler;
import com.keengine.http.HttpRequestInterface;
import com.keengine.http.HttpParameters;
import com.keengine.http.StreamReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author nuno
 * /
 * @project crawler
 * @date 08-07-2019
 **/
public class TorHttpGet implements HttpRequestInterface, StreamReader {


    @Override
    public byte[] getUrl(HttpParameters parameters) throws IOException {
        SocketAddress sockAddr = new InetSocketAddress(parameters.getTorServiceIp(), parameters.getPort());
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, sockAddr);
        URL url = new URL(parameters.getUrl());
        LOGGER.info("Executing Request to "+ parameters.getUrl() + " via " + proxy);
        InputStream in = url.openConnection(proxy).getInputStream();
        return this.readStream(in);
    }


}
