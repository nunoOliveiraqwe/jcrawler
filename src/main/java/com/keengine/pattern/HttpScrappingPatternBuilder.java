package com.keengine.pattern;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public final class HttpScrappingPatternBuilder {

    private String name;
    private String regex;

    public HttpScrappingPatternBuilder() {

    }


    public HttpScrappingPatternBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public HttpScrappingPatternBuilder withRegex(String regex) {
        this.regex = regex;
        return this;
    }

    public HttpScrappingPattern build() throws IllegalArgumentException {
        if (this.name == null) {
            throw new IllegalArgumentException("Pattern Name Cannot Be null");
        }
        if (this.name.isEmpty()) {
            throw new IllegalArgumentException("Pattern Name Cannot Be empty");
        }
        if (this.regex == null) {
            throw new IllegalArgumentException("Pattern Regex Cannot Be null");
        }
        if (this.regex.isEmpty()) {
            throw new IllegalArgumentException("Pattern Regex Cannot Be empty");
        }
        Pattern pattern = checkRegex(regex);
        if(pattern==null){
            throw new IllegalArgumentException("Invalid Regex");
        }
        return new HttpScrappingPattern(this.name,pattern);
    }


    private Pattern checkRegex(String regex) {
        try {
            return Pattern.compile(regex);
        } catch (PatternSyntaxException e) {
            Logger.getLogger(HttpScrappingPatternBuilder.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

}
