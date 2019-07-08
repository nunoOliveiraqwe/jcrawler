package com.keengine.pattern;

import org.junit.Test;

import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class ScrappingPatternTest {


    @Test(expected = IllegalStateException.class)
    public void EmptyBuild(){
        ScrappingPatterns.PatternsBuilder patternsBuilder = new ScrappingPatterns.PatternsBuilder();
        patternsBuilder.build();
    }

    @Test
    public void TestHttpScrappingNoFlag(){
        ScrappingPatterns.PatternsBuilder patternsBuilder = new ScrappingPatterns.PatternsBuilder();
        patternsBuilder.
                withPatternEntity(
                        new PatternEntity("^a\u030A$","Random Regex",new int[]{0,1,2}));
        final ScrappingPatterns scrappingPatterns = patternsBuilder.build();
        final List<Match> results = scrappingPatterns.match("\u00E5");
        assertEquals(0, results.size());
    }

    @Test
    public void TestHttpScrappingFlag(){
        ScrappingPatterns.PatternsBuilder patternsBuilder = new ScrappingPatterns.PatternsBuilder();
        patternsBuilder.
                withPatternEntity(
                        new PatternEntity("^a\u030A$","Random Regex",Pattern.CANON_EQ,new int[]{0,1,2}));
        final ScrappingPatterns scrappingPatterns = patternsBuilder.build();
        final List<Match> results = scrappingPatterns.match("\u00E5");
        assertEquals(1, results.size());
    }

    @Test
    public void TestHttpScrappingGroups(){
        ScrappingPatterns.PatternsBuilder patternsBuilder = new ScrappingPatterns.PatternsBuilder();
        patternsBuilder.
                withPatternEntity(
                        new PatternEntity("([a-zA-Z0-9]+)([\\s]+)([a-zA-Z ]+)([\\s]+)([0-9]+)","Random Regex",8,new int[]{0,1,2}));
        final ScrappingPatterns scrappingPatterns = patternsBuilder.build();
        final List<Match> results = scrappingPatterns.match("!* UserName10 John Smith 01123 *!\n");
        assertEquals(2, results.size());
    }

    @Test
    public void TestHttpScrappingPattern(){

    }



}