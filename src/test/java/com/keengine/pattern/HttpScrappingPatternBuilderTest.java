package com.keengine.pattern;

import org.junit.Test;

import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class HttpScrappingPatternBuilderTest {



    @Test
    public void TestHttpScrappingBuilderNoFlag(){
        final MatchingGroupBuilder matchingGroupBuilder = new MatchingGroupBuilder().
                withGroupIndex(0).withNext().withGroupIndex(1).withNext().withGroupIndex(2);
        final HttpScrappingPatternBuilder httpScrappingPatternBuilder = new HttpScrappingPatternBuilder();
        httpScrappingPatternBuilder.withMatchingGroupBuilder(matchingGroupBuilder)
                .withName("Random Regex")
                .withRegex("^a\u030A$");
        final HttpScrappingPattern scrappingPattern = httpScrappingPatternBuilder.build();
        final List<String> results = scrappingPattern.match("\u00E5");
        assertEquals(0, results.size());
    }

    @Test
    public void TestHttpScrappingBuilderFlag(){
        final MatchingGroupBuilder matchingGroupBuilder = new MatchingGroupBuilder().
                withGroupIndex(0).withNext().withGroupIndex(1).withNext().withGroupIndex(2);
        final HttpScrappingPatternBuilder httpScrappingPatternBuilder = new HttpScrappingPatternBuilder();
        httpScrappingPatternBuilder.withMatchingGroupBuilder(matchingGroupBuilder)
                .withName("Random Regex")
                .withRegex("^a\u030A$")
                .withPatternFlag(Pattern.CANON_EQ);
        final HttpScrappingPattern scrappingPattern = httpScrappingPatternBuilder.build();
        final List<String> results = scrappingPattern.match("\u00E5");
        assertEquals(1, results.size());
    }

    @Test
    public void TestHttpScrappingBuilderInvalidFlag(){
        final MatchingGroupBuilder matchingGroupBuilder = new MatchingGroupBuilder().
                withGroupIndex(0).withNext().withGroupIndex(1).withNext().withGroupIndex(2);
        final HttpScrappingPatternBuilder httpScrappingPatternBuilder = new HttpScrappingPatternBuilder();
        httpScrappingPatternBuilder.withMatchingGroupBuilder(matchingGroupBuilder)
                .withName("Random Regex")
                .withRegex("([a-zA-Z0-9]+)([\\s]+)([a-zA-Z ]+)([\\s]+)([0-9]+)")
                .withPatternFlag(8);
        final HttpScrappingPattern scrappingPattern = httpScrappingPatternBuilder.build();
        final List<String> results = scrappingPattern.match("!* UserName10 John Smith 01123 *!\n" +
                "!* UserName10 John Smith 01123 *!");
        assertEquals(6, results.size());
    }

    @Test
    public void TestHttpScrappingPattern(){

    }



}