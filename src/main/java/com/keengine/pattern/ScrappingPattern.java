package com.keengine.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author nuno
 * /
 * @project crawler
 * @date 08-07-2019
 **/
class ScrappingPattern implements ScrappingInterface {

    private String name;
    private Pattern regex;
    private MatchingGroup matchingGroup;


    protected ScrappingPattern(String name, Pattern regex, MatchingGroup matchingGroup){
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

