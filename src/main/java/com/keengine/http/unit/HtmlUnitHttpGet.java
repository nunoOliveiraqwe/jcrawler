package com.keengine.http.unit;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.keengine.http.HttpParameters;
import com.keengine.http.HttpRequestInterface;

/**
 * @author nuno
 * /
 * @project crawler
 * @date 10-08-2019
 **/
public class HtmlUnitHttpGet implements HttpRequestInterface {


    @Override
    public byte[] getUrl(HttpParameters parameters) throws Exception {
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage(parameters.getUrl());
            return page.asText().getBytes();
        }
    }


}
