package com.keengine.pattern.matcher;



import com.keengine.pattern.scrapping.ScrappingInterface;

import java.util.List;
import java.util.regex.Matcher;

public class MatchingGroupImpl {

    private MatchingGroupImpl next;
    private int groupIndex;

    protected MatchingGroupImpl(int groupIndex, MatchingGroupImpl matchingGroup) {
        this.groupIndex = groupIndex;
        this.next = matchingGroup;
    }


    public void match(ScrappingInterface scrapper, Matcher matcher, List<Match> matchList) {
        if (this.groupIndex>= 0 &&this.groupIndex < matcher.end()) {
            String match = matcher.group(this.groupIndex);
            matchList.add(new Match(match,this.groupIndex,scrapper.getName()));
        }
        if(this.next != null)
            this.next.match(scrapper,matcher,matchList);
    }

}


