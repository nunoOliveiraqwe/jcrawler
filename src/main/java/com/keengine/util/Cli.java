package com.keengine.util;

import org.apache.commons.cli.Options;
import org.apache.log4j.Logger;

/**
 * @author nuno
 * /
 * @project crawler
 * @date 08-07-2019
 **/
public class Cli {


    private static final Logger LOGGER = Logger.getLogger(Cli.class.getSimpleName());
    private String[] args;
    private Options options;


    public Cli(String... args){
        this.args = args;
        this.options = new Options();
        buildOptions();
    }




    private void buildOptions(){

    }

}
