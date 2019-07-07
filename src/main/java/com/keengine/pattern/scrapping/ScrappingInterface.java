package com.keengine.pattern.scrapping;

import com.keengine.pattern.matcher.Match;

import java.util.List;

public interface ScrappingInterface {



    String getName();

    List<Match> match(String txt);

}
