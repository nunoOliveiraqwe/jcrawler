package com.keengine.pattern;



import java.util.List;
import java.util.regex.Matcher;

public class MatchingGroupImpl {


    private MatchingGroupImpl next;
    private int groupIndex;

    protected MatchingGroupImpl(int groupIndex, MatchingGroupImpl matchingGroup) {
        this.groupIndex = groupIndex;
        this.next = matchingGroup;
    }


    protected void match(Matcher matcher, List<String> matchList) {
        if (this.groupIndex>= 0 &&this.groupIndex < matcher.end()) {
            matchList.add(matcher.group(this.groupIndex));
        }
        if(this.next != null)
            this.next.match(matcher,matchList);
    }

}


