package com.keengine.pattern;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public final class HttpScrappingPatternBuilder {

    private String name;
    private String regex;
    private int patternFlag = 0;
    private MatchingGroupBuilder matchingGroupBuilder;

    public HttpScrappingPatternBuilder() {

    }


    public HttpScrappingPatternBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public HttpScrappingPatternBuilder withPatternFlag(int patternFlag) {
        this.patternFlag = patternFlag;
        return this;
    }

    public HttpScrappingPatternBuilder withRegex(String regex) {
        this.regex = regex;
        return this;
    }

    public HttpScrappingPatternBuilder withMatchingGroupBuilder(MatchingGroupBuilder matchingGroupBuilder) {
        this.matchingGroupBuilder = matchingGroupBuilder;
        return this;
    }

    public HttpScrappingPattern build() throws IllegalArgumentException {
        if (this.name == null) {
            throw new IllegalArgumentException("Pattern name cannot be null");
        }
        if (this.name.isEmpty()) {
            throw new IllegalArgumentException("Pattern name cannot be empty");
        }
        if (this.regex == null) {
            throw new IllegalArgumentException("Pattern regex cannot be null");
        }
        if (this.regex.isEmpty()) {
            throw new IllegalArgumentException("Pattern regex cannot be empty");
        }
        if (this.matchingGroupBuilder == null) {
            throw new IllegalArgumentException("Matching group builder cannot be null");
        }
        if (!checkPatternFlag()) {
            throw new IllegalArgumentException("Pattern flag must be a power of two up until");
        }
        MatchingGroupImpl matchingGroupImpl = this.matchingGroupBuilder.build();
        Pattern pattern = checkRegex(regex);
        if (pattern == null) {
            throw new IllegalArgumentException("Invalid Regex");
        }
        return new HttpScrappingPattern(this.name, pattern, matchingGroupImpl);
    }


    private Pattern checkRegex(String regex) {
        try {
            return Pattern.compile(regex,this.patternFlag);
        } catch (PatternSyntaxException e) {
            Logger.getLogger(HttpScrappingPatternBuilder.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    private boolean checkPatternFlag() {
        return this.patternFlag > 0 && this.patternFlag <= 256 ? (int) (Math.ceil((Math.log(this.patternFlag) / Math.log(2)))) ==
                (int) (Math.floor(((Math.log(this.patternFlag) / Math.log(2))))) : this.patternFlag==0;
    }

}
