package com.keengine.http;

import com.keengine.http.simple.SimpleHttpGet;
import com.keengine.http.tor.TorHttpGet;
import com.keengine.http.unit.HtmlUnitHttpGet;
import com.keengine.http.unit.HtmlUnitHttpProxyGet;

import java.util.ArrayList;
import java.util.List;

public final class AvailableInterfaces{

    static List<AvailableInterface> interfaceList;

    static {
        interfaceList = new ArrayList<>();
        interfaceList.add(new AvailableInterface("SIMPLE", SimpleHttpGet.class));
        interfaceList.add(new AvailableInterface("TOR", TorHttpGet.class));
        interfaceList.add(new AvailableInterface("HTML_UNIT", HtmlUnitHttpGet.class));
        interfaceList.add(new AvailableInterface("HTML_UNIT_PROXY", HtmlUnitHttpProxyGet.class));
    }



    public static AvailableInterface SIMPLE(){
        return interfaceList.stream().findFirst().filter(x -> x.getIdentifier().equals("SIMPLE")).get();
    }

    public static AvailableInterface TOR(){
        return interfaceList.stream().findFirst().filter(x -> x.getIdentifier().equals("TOR")).get();
    }

    public static AvailableInterface HTML_UNIT(){
        return interfaceList.stream().findFirst().filter(x -> x.getIdentifier().equals("HTML_UNIT")).get();
    }

    public static AvailableInterface HTML_UNIT_PROXY(){
        return interfaceList.stream().findFirst().filter(x -> x.getIdentifier().equals("HTML_UNIT_PROXY")).get();
    }

    final static class AvailableInterface{

        private String identifier;
        private Class clazz;


        public AvailableInterface(String identifier, Class clazz) {
            this.identifier = identifier;
            this.clazz = clazz;
        }


        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public Class getClazz() {
            return clazz;
        }

        public void setClazz(Class clazz) {
            this.clazz = clazz;
        }
    }

}



