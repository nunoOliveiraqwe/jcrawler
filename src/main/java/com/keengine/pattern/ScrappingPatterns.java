package com.keengine.pattern;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nuno
 * /
 * @project crawler
 * @date 08-07-2019
 **/
public final class ScrappingPatterns {


    private static final Logger LOGGER = Logger.getLogger(ScrappingPatterns.class.getSimpleName());

    private Map<String,ScrappingInterface> matchers;


    public ScrappingPatterns(){
        this.matchers = new HashMap<>();
    }

    void addScrapingMatcher(ScrappingInterface scrappingInterface){
        this.matchers.put(scrappingInterface.getName(),scrappingInterface);
    }

    public List<Match> match(String txt){
        List<Match> matches = new ArrayList<>();
        matchers.forEach((K,V)->{
            matches.addAll(V.match(txt));
        });
        return matches;
    }


    public static class PatternsBuilder{
        private List<PatternEntity> patternEntities;

        public PatternsBuilder() {
            this.patternEntities = new ArrayList<>();
        }

        public PatternsBuilder withPatternEntity(PatternEntity entity){
            this.patternEntities.add(entity);
            return this;
        }

        public ScrappingPatterns build() throws IllegalStateException {
            if(patternEntities.size()==0){
                LOGGER.fatal("build method called without any PatternEntities associated");
                throw new IllegalStateException("build called without any PattenEntity");
            }
            ScrappingPatterns patterns = new ScrappingPatterns();
            patternEntities.forEach((pattern)->{
                patterns.addScrapingMatcher(pattern.toScrappingPattern());
            });
            return patterns;
        }

    }


}
