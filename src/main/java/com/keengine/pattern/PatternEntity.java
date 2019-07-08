package com.keengine.pattern;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author nuno
 * /
 * @project crawler
 * @date 08-07-2019
 **/
public class PatternEntity {


    private static final Logger LOGGER = Logger.getLogger(PatternEntity.class.getSimpleName());

    private static final int DEFAULT_MATCH_GROUP = 1;

    private String regex;
    private int[] matchingGroups;
    private String name;
    private int patternFlag;

    /*
    Generated Instance Fields
     */
    private Pattern pattern;
    private MatchingGroup matchingGroup;



    public PatternEntity(String regex, String name) throws IllegalArgumentException{
        this(regex,name,0,new int[]{DEFAULT_MATCH_GROUP});
    }

    public PatternEntity(String regex, String name,int patternFlag) throws IllegalArgumentException{
        this(regex,name,patternFlag,new int[]{DEFAULT_MATCH_GROUP});
    }
    public PatternEntity(String regex, String name,@NotNull int[] matchingGroups) throws IllegalArgumentException{
        this(regex,name,0,matchingGroups);
    }


    public PatternEntity(String regex,String name, int patternFlag, @NotNull int[] matchingGroups) throws IllegalArgumentException{
        this.regex = regex;
        this.name = name;
        this.patternFlag = patternFlag;
        this.matchingGroups = matchingGroups;
        validateAndLog();
        this.matchingGroup = new MatchingGroup
                .MatchingGroupBuilder()
                .withGroupIndex(matchingGroups)
                .build();
    }

    private void validateAndLog() throws IllegalArgumentException{
        try{
            validatePatternEntity();
        }
        catch (IllegalArgumentException ex){
            LOGGER.error( ex.getMessage(), ex);
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

    public MatchingGroup getMatchingGroup() {
        return matchingGroup;
    }


    public ScrappingPattern toScrappingPattern(){
        return new ScrappingPattern(getName(),getPattern(),getMatchingGroup());
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatternEntity)) return false; //Violation of Liskov Substitution Principle
        PatternEntity that = (PatternEntity) o;
        return patternFlag == that.patternFlag &&
                regex.equals(that.regex) &&
                Arrays.equals(matchingGroups, that.matchingGroups) &&
                name.equals(that.name) &&
                pattern.equals(that.pattern);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(regex, name, patternFlag, pattern);
        result = 31 * result + Arrays.hashCode(matchingGroups);
        return result;
    }
}
