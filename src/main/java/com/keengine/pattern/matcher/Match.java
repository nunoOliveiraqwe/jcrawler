package com.keengine.pattern.matcher;

import com.keengine.framework.dto.DTO;
import com.keengine.framework.dto.DTOable;

import java.util.HashMap;
import java.util.Map;

public class Match implements DTOable {


    private String match;
    private int groupIndex;
    private String patternName;

    public Match(String match, int groupIndex, String patternName) {
        this.match = match;
        this.groupIndex = groupIndex;
        this.patternName = patternName;
    }

    @Override
    public DTO toDTO() {
        return new MatchDto(match,patternName,groupIndex);
    }

    private class MatchDto implements DTO{

        private final Map<String,Object> objectMap;

        MatchDto(String match,String patternName,int groupIndex){
            this.objectMap = new HashMap<>();
            this.objectMap.put(patternName,match);
            this.objectMap.put("groupIndex",groupIndex);
        }

//        MatchDto(String match,String pattern){
//
//        }
//
//        MatchDto(String match,int groupIndex){
//
//        }
//
//        MatchDto(String match){
//
//        }



        @Override
        public Map<String, Object> values() {
            return objectMap;
        }
    }
}
