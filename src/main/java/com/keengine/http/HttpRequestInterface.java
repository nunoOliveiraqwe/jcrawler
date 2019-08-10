package com.keengine.http;


import com.keengine.http.simple.SimpleHttpGet;
import com.keengine.http.tor.TorHttpGet;
import com.keengine.http.unit.HtmlUnitHttpGet;
import com.keengine.http.unit.HtmlUnitHttpProxyGet;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author nuno
 * /
 * @project crawler
 * @date 08-07-2019
 **/
public interface HttpRequestInterface {


    Logger LOGGER = Logger.getLogger(HttpRequestInterface.class.getSimpleName());

    byte[] getUrl(HttpParameters parameters) throws Exception;

}
