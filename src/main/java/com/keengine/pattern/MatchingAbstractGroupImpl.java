package com.keengine.pattern;



import java.util.List;
import java.util.regex.Matcher;

public class MatchingAbstractGroupImpl extends AbstractMatchingGroup {


    private MatchingAbstractGroupImpl next;
    private int groupIndex;

    protected MatchingAbstractGroupImpl(int groupIndex, MatchingAbstractGroupImpl matchingGroup) {
        this.groupIndex = groupIndex;
        this.next = matchingGroup;
    }


    @Override
    protected void match(Matcher matcher, List<String> matchList) {
        if (this.groupIndex>=matcher.start()&&this.groupIndex < matcher.end()) {
            matchList.add(matcher.group(this.groupIndex));
        }
        if(this.next != null)
            this.next.match(matcher,matchList);
    }

}


