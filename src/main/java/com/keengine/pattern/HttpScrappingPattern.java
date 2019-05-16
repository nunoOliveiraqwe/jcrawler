package com.keengine.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpScrappingPattern implements HttpScrappingMatcher {

    private String name;
    private Pattern regex;
    private MatchingGroupImpl matchingGroup;


    protected HttpScrappingPattern(String name,Pattern regex, MatchingGroupImpl matchingGroup){
        this.name = name;
        this.regex = regex;
        this.matchingGroup = matchingGroup;
    }

    @Override
    public List<String> match(String txt){
        List<String> results = new ArrayList<>();
        final Matcher matcher = regex.matcher(txt);
        while (matcher.find()){
            this.matchingGroup.match(matcher,results);
        }
        return results;
    }
}

