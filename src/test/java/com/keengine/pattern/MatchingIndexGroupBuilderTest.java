package com.keengine.pattern;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class MatchingIndexGroupBuilderTest {

    @Test
    public void TestMatchingGroupBuilder() {
        final MatchingIndexGroupBuilder matchingIndexGroupBuilder = new MatchingIndexGroupBuilder().
                withGroupIndex(0).withNext().withGroupIndex(1).withNext().withGroupIndex(2);
        final MatchingAbstractGroupImpl matchingGroup = matchingIndexGroupBuilder.build();
        final Pattern compile = Pattern.compile("([a-zA-Z0-9]+)([\\s]+)([a-zA-Z ]+)([\\s]+)([0-9]+)");
        List<String> results = new ArrayList<>();
        Matcher matcher = compile.matcher("!* UserName10 John Smith 01123 *!");
        matcher.matches();
        matcher.find();
        matchingGroup.match(matcher,results);
        assertEquals(3, results.size());

    }
}