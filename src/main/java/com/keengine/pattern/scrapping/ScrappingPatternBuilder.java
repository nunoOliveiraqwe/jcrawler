package com.keengine.pattern.scrapping;

import com.keengine.pattern.PatternEntity;
import com.keengine.pattern.matcher.MatchingGroupBuilder;
import com.keengine.pattern.matcher.MatchingGroupImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public final class ScrappingPatternBuilder {

    static Logger LOGGER = Logger.getLogger("ScrappingPatternBuilder");


    private List<PatternEntity> patternEntities;
    private MatchingGroupBuilder matchingGroupBuilder;

    public ScrappingPatternBuilder() {
        this.patternEntities = new ArrayList<>();
    }

    public ScrappingPatternBuilder withPatternEntity(PatternEntity entity){
        this.patternEntities.add(entity);
        return this;
    }

    public ScrappingPatternBuilder withMatchingGroupBuilder(MatchingGroupBuilder matchingGroupBuilder) {
        this.matchingGroupBuilder = matchingGroupBuilder;
        return this;
    }

    public ScrappingPatterns build() throws IllegalArgumentException {
        MatchingGroupImpl matchingGroupImpl = this.matchingGroupBuilder.build();
        ScrappingPatterns patterns = new ScrappingPatterns();
        patternEntities.forEach((pattern)->{
            patterns.addScrapingMatcher(pattern.toScrappingPattern(matchingGroupImpl));
        });
        return patterns;
    }




}
