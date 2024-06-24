package org.nimit.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.invoke.MethodHandles.lookup;

public class CoreUtils {
    private static final Logger logger = LoggerFactory.getLogger(lookup().lookupClass());

    public static Properties globalProperties = CoreUtils.loadGlobalProperties();

    public static Properties readPropertyFile(String propertiesFileName){
        Properties properties = new Properties();

        try (InputStream inputStream = CoreUtils.class.getClassLoader().getResourceAsStream(propertiesFileName)) {
            if (inputStream == null) {
                throw new RuntimeException("ERROR : Sorry, unable to find "+ propertiesFileName);
            }
            // Load the properties from the input stream
            properties.load(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    public static String getOverriddenProperty(String key) {
        // Check command-line arguments
        if(System.getProperties().containsKey(key)){
            logger.info(String.format("Property overridden using VM args, %s : %s",key,System.getProperty(key)));
            return System.getProperty(key);
        }

        // Check environment variables
        String envValue = System.getenv(key);
        if (envValue != null) {
            logger.info(String.format("Property overridden using env variables, %s : %s",key,envValue));
            return envValue;
        }

        // Check properties file
        logger.info(String.format("Property loaded from property file, %s : %s",key,globalProperties.getProperty(key)));
        return globalProperties.getProperty(key);
    }

    public static Properties loadGlobalProperties(){
        Properties defaultProperties = CoreUtils.readPropertyFile("default.properties");
        Properties envRelatedProperty;
        if(System.getProperties().containsKey("env")){
            String envVal = System.getProperty("env");
            envRelatedProperty = CoreUtils.readPropertyFile(envVal + ".properties");
            defaultProperties.putAll(envRelatedProperty);
        }
        return defaultProperties;
    }

}
