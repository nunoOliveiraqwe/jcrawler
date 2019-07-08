package com.keengine.http.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author nuno
 * /
 * @project crawler
 * @date 08-07-2019
 **/
public class HttpProperties {


    private final Properties applicationProperties = new Properties();
    private static final String PROPERTIES_RESOURCE = "http.properties";
    private final static String REPOSITORY_FACTORY_KEY = "http.impl";


    private HttpProperties(){
        loadProperties();
    }

    private void loadProperties() {
        InputStream propertiesStream = null;
        try {
            propertiesStream = HttpProperties.class.getClassLoader().getResourceAsStream(PROPERTIES_RESOURCE);
            if (propertiesStream != null) {
                this.applicationProperties.load(propertiesStream);
            } else {
                throw new FileNotFoundException(
                        "property file '" + PROPERTIES_RESOURCE + "' not found in the classpath");
            }
        } catch (final IOException exio) {
            Logger.getLogger(HttpProperties.class.getName()).log(Level.SEVERE, exio.getMessage(), exio);
        } finally {
            if (propertiesStream != null) {
                try {
                    propertiesStream.close();
                } catch (final IOException ex) {
                    Logger.getLogger(HttpProperties.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public String getRepositoryFactory() {
        return this.applicationProperties.getProperty(REPOSITORY_FACTORY_KEY);
    }

    public static HttpProperties instance(){
        return LazyInitialization.INSTANCE;
    }


    private static class LazyInitialization{
        public static final HttpProperties INSTANCE = new HttpProperties();
    }


}
