package com.keengine.pattern;

import com.keengine.pattern.matcher.MatchingGroupImpl;
import com.keengine.pattern.scrapping.ScrappingPattern;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PatternEntity {


    private static Logger LOGGER = Logger.getLogger("PatternEntity");


    private String regex;
    private String name;
    private int patternFlag;
    private Pattern pattern;


    public PatternEntity(String regex, String name) throws IllegalArgumentException{
        this(regex,name,0);
    }

    public PatternEntity(String regex,String name, int patternFlag) throws IllegalArgumentException{
        this.regex = regex;
        this.name = name;
        this.patternFlag = patternFlag;
        validateAndLog();
    }

    private void validateAndLog() throws IllegalArgumentException{
        try{
            validatePatternEntity();
        }
        catch (IllegalArgumentException ex){
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
    }


    private void validatePatternEntity() throws IllegalArgumentException{
        if (getName() == null) {
            throw new IllegalArgumentException("Pattern name cannot be null");
        }
        if (getName().isEmpty()) {
            throw new IllegalArgumentException("Pattern name cannot be empty");
        }
        if (getRegex() == null) {
            throw new IllegalArgumentException("Pattern regex cannot be null");
        }
        if (getRegex().isEmpty()) {
            throw new IllegalArgumentException("Pattern regex cannot be empty");
        }
        if (!checkPatternFlag()) {
            throw new IllegalArgumentException("Pattern flag must be a power of two up to 256");
        }
        if(!checkRegex()){
            throw new IllegalArgumentException("Invalid Regex "+getRegex());
        }
    }


    public String getRegex() {
        return regex;
    }

    public String getName() {
        return name;
    }

    public int getPatternFlag(){
        return patternFlag;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public ScrappingPattern toScrappingPattern(MatchingGroupImpl group){
        return new ScrappingPattern(name,pattern,group);
    }

    private boolean checkRegex() {
        try {
            Pattern compile = Pattern.compile(getRegex(), getPatternFlag());
            this.pattern = compile;
            return true;
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    private boolean checkPatternFlag() {
        return getPatternFlag() > 0 && getPatternFlag() <= 256 ? (int) (Math.ceil((Math.log(getPatternFlag()) / Math.log(2)))) ==
                (int) (Math.floor(((Math.log(getPatternFlag()) / Math.log(2))))) : getPatternFlag()==0;
    }
}
