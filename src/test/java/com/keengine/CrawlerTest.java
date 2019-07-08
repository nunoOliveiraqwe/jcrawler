package com.keengine;

import com.keengine.http.HttpParameters;
import com.keengine.http.simple.SimpleHttpGet;
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


}
