package com.keengine.pattern.matcher;



public class MatchingGroupBuilder {

    private int groupIndex;
    private MatchingGroupBuilder previous;

    public MatchingGroupBuilder(){

    }

    public MatchingGroupBuilder withGroupIndex(int index){
        this.groupIndex = index;
        return this;
    }

    public MatchingGroupBuilder withNext(){
        MatchingGroupBuilder next = new MatchingGroupBuilder();
        next.previous=this;
        return next;
    }

    public MatchingGroupImpl build() throws IllegalArgumentException{
        if(this.groupIndex<0){
            throw new IllegalArgumentException("Group Index must be an unsigned integer");
        }
        return new MatchingGroupImpl(this.groupIndex,this.previous!=null?this.previous.build():null);
    }


}
