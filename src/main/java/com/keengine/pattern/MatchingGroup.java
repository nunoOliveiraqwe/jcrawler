package com.keengine.pattern;


import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Matcher;

class MatchingGroup {

    private MatchingGroup next;
    private int groupIndex;

    private MatchingGroup(int groupIndex, MatchingGroup matchingGroup) {
        this.groupIndex = groupIndex;
        this.next = matchingGroup;
    }


    public void match(ScrappingInterface scrapper, Matcher matcher, List<Match> matchList) {
        if (this.groupIndex >= 0 && this.groupIndex < matcher.end()) {
            String match = matcher.group(this.groupIndex);
            if (!match.trim().isEmpty())
                matchList.add(new Match(match, this.groupIndex, scrapper.getName()));
        }
        if (this.next != null)
            this.next.match(scrapper, matcher, matchList);
    }

    protected static class MatchingGroupBuilder {
        private int groupIndex;
        private MatchingGroupBuilder previous;

        public MatchingGroupBuilder() {

        }

        public MatchingGroupBuilder withGroupIndex(int index) {
            MatchingGroupBuilder next = new MatchingGroupBuilder();
            this.groupIndex = index;
            next.previous = this;
            return next;
        }

        private MatchingGroupBuilder withGroupIndex(int index, MatchingGroupBuilder builder) {
            MatchingGroupBuilder next = new MatchingGroupBuilder();
            builder.groupIndex = index;
            next.previous = builder;
            return next;
        }

        public MatchingGroupBuilder withGroupIndex(@NotNull int[] index) {
            MatchingGroupBuilder next = this;
            for (int gIndex : index) {
                next = withGroupIndex(gIndex, next);
            }
            return next.previous;
        }


        public MatchingGroup build() throws IllegalArgumentException {
            if (this.groupIndex < 0) {
                throw new IllegalArgumentException("Group Index must be an unsigned integer");
            }
            return new MatchingGroup(this.groupIndex, this.previous != null ? this.previous.build() : null);
        }

    }
}


