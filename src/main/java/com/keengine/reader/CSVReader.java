package com.keengine.reader;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CSVReader implements Reader {


    static {
        Registry.register("csv",new CSVReader());
    }



    @Override
    public void read(String path) {
      throw  new NotImplementedException();
    }


}
