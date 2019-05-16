package com.keengine.pattern;



public class MatchingIndexGroupBuilder {

    private int groupIndex;
    private MatchingIndexGroupBuilder previous;

    public MatchingIndexGroupBuilder(){

    }

    public MatchingIndexGroupBuilder withGroupIndex(int index){
        this.groupIndex = index;
        return this;
    }



    public MatchingIndexGroupBuilder withNext(){
        MatchingIndexGroupBuilder next = new MatchingIndexGroupBuilder();
        next.previous=this;
        return next;
    }

    public MatchingAbstractGroupImpl build() throws IllegalArgumentException{
        if(this.groupIndex<0){
            throw new IllegalArgumentException("Group Index must be an unsigned integer");
        }
        return new MatchingAbstractGroupImpl(this.groupIndex,this.previous!=null?this.previous.build():null);
    }


}
