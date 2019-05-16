package com.keengine.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpScrappingPattern implements HttpScrappingMatcher {

    private String name;
    private Pattern regex;
    private AbstractMatchingGroup matchingGroup;


    protected HttpScrappingPattern(String name,Pattern regex){
        this.name = name;
        this.regex = regex;
    }

    @Override
    public List<String> match(String txt){
        List<String> results = new ArrayList<>();
        final Matcher matcher = regex.matcher(txt);
        matcher.matches();
        if(matcher.find()){
            this.matchingGroup.match(matcher,results);
        }
        return results;
    }
}

