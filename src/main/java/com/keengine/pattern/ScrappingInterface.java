package com.keengine.pattern;


import java.util.List;

/**
 * @author nuno
 * /
 * @project crawler
 * @date 08-07-2019
 **/
interface ScrappingInterface {



    String getName();

    List<Match> match(String txt);

}
