package com.keengine.pattern.scrapping;

import com.keengine.pattern.matcher.Match;

import java.util.ArrayList;
import java.util.List;

public class ScrappingPatterns {

    private List<ScrappingInterface> matchers;



    public ScrappingPatterns(){}

    void addScrapingMatcher(ScrappingInterface scrappingInterface){
        this.matchers.add(scrappingInterface);
    }


    public List<Match> match(String txt){
        List<Match> matches = new ArrayList<>();
        for (ScrappingInterface matcher: matchers) {
            matches.addAll(matcher.match(txt));
        }
        return matches;
    }


}
