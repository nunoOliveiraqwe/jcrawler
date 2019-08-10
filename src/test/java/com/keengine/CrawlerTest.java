package com.keengine;

import com.keengine.http.HttpParameters;
import com.keengine.http.simple.SimpleHttpGet;
import com.keengine.http.unit.HtmlUnitHttpGet;
import com.keengine.pattern.Match;
import com.keengine.pattern.PatternEntity;
import com.keengine.pattern.ScrappingPatterns;
import org.junit.Assume;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

/**
 * Unit test for simple Crawler.
 */
public class CrawlerTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    @Test
    public void TestSimpleCrawlingWebpage(){

        Assume.assumeFalse("Travis CI build ignoring test","TRUE".equalsIgnoreCase(System.getenv("TRAVIS")));
        String url = "https://www.keengine.com/blog";
        final SimpleHttpGet httpRequest = new SimpleHttpGet();
        assertNotNull(httpRequest);
        try {
            byte[] arr = httpRequest.getUrl(new HttpParameters() {
                @Override
                public int getPort() {
                    return 0;
                }

                @Override
                public String getUrl() {
                    return url;
                }

                @Override
                public String getTorServiceIp() {
                    return null;
                }

                @Override
                public String getUserAgent() {
                    return "jCrawler";
                }
            });
            assertTrue(arr.length>0);
            String response = new String(arr);
            assertTrue(!response.isEmpty());
            ScrappingPatterns.PatternsBuilder patternsBuilder = new ScrappingPatterns.PatternsBuilder();
            patternsBuilder.
                    withPatternEntity(
                            new PatternEntity("\\+[1-9][0-9]{2}.[1-9][0-9]{8}","Match Keengine Phone Number",new int[]{0}));
            final ScrappingPatterns scrappingPatterns = patternsBuilder.build();
            final List<Match> results = scrappingPatterns.match(response);
            assertEquals(1,results.size());
            results.forEach((phoneNumber)->{
                assertTrue(((String)phoneNumber.toDTO().values().get("Match Keengine Phone Number")).contains("+351"));
            });
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }



    /**
     * Tests if a page is rendered so certain fields can be retrived.
     * In the case of the bellow url the email field is protected by cloudflare, so in order
     * to get the email address the page was to be rendered so the cloudflare-static/email-decode.min.js script can execute
     */
    @Test
    public void TestHtmlRenderCrawlingWebpage(){

        Assume.assumeFalse("Travis CI build ignoring test","TRUE".equalsIgnoreCase(System.getenv("TRAVIS")));
        String url = "https://www.keengine.com/blog";
        final HtmlUnitHttpGet httpRequest = new HtmlUnitHttpGet();
        assertNotNull(httpRequest);
        try {
            byte[] arr = httpRequest.getUrl(new HttpParameters() {
                @Override
                public int getPort() {
                    return 0;
                }

                @Override
                public String getUrl() {
                    return url;
                }

                @Override
                public String getTorServiceIp() {
                    return null;
                }

                @Override
                public String getUserAgent() {
                    return "jCrawler";
                }
            });
            assertTrue(arr.length>0);
            String response = new String(arr);
            assertTrue(!response.isEmpty());
            ScrappingPatterns.PatternsBuilder patternsBuilder = new ScrappingPatterns.PatternsBuilder();
            patternsBuilder.
                    withPatternEntity(
                            new PatternEntity("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
                                    "Match Keengine Email",new int[]{0}));
            final ScrappingPatterns scrappingPatterns = patternsBuilder.build();
            final List<Match> results = scrappingPatterns.match(response);
            assertEquals(2,results.size());
            results.forEach((phoneNumber)->{
                assertNotNull(((String)phoneNumber.toDTO().values().get("Match Keengine Email")));
            });
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }


}
