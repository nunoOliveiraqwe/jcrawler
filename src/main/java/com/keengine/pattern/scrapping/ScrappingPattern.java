package com.keengine.pattern.scrapping;

import com.keengine.pattern.matcher.Match;
import com.keengine.pattern.matcher.MatchingGroupImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScrappingPattern implements ScrappingInterface {

    private String name;
    private Pattern regex;
    private MatchingGroupImpl matchingGroup;


    public ScrappingPattern(String name, Pattern regex, MatchingGroupImpl matchingGroup){
        this.name = name;
        this.regex = regex;
        this.matchingGroup = matchingGroup;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Match> match(String txt){
        List<Match> results = new ArrayList<>();
        final Matcher matcher = regex.matcher(txt);
        while (matcher.find()){
            this.matchingGroup.match(this,matcher,results);
        }
        return results;
    }
}

