package org.nimit.core;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static java.lang.invoke.MethodHandles.lookup;

public class CoreUtils {
    private static final Logger logger = LoggerFactory.getLogger(lookup().lookupClass());

    public static Properties globalProperties = CoreUtils.loadGlobalProperties();

    public static Properties readPropertyFile(String propertiesFileName){
        Properties properties = new Properties();

        try (InputStream inputStream = CoreUtils.class.getClassLoader().getResourceAsStream(propertiesFileName)) {
            if (inputStream == null) {
                logger.error(" Sorry, unable to find "+ propertiesFileName);
                throw new RuntimeException("ERROR : Sorry, unable to find "+ propertiesFileName);
//                System.exit(1);
            }
            // Load the properties from the input stream
            properties.load(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    /**
     * Get the overridden key value, in below-mentioned sequence :
     * 1. VM args
     * 2. System environment variables
     * 3. Property defined in property file
     * 4. Empty string if no Key is found
     * @param key Value from the property file to laod
     * @return corresponding value assigned to key
     */
    public static String getOverriddenProperty(String key) {
        String finalVal = "";
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
        if(StringUtils.isNotBlank(globalProperties.getProperty(key))){
            finalVal = globalProperties.getProperty(key);
        }
//        else{
//            throw new NoPropertyFoundException("No value found for key : "+ key);
//        }
        return finalVal;
    }

    public static String getOverriddenPropertyFromVMandEnv(String key) {
        String finalVal = "";
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

        return finalVal;
    }

    public static Properties loadGlobalProperties(){
        Properties defaultProperties = CoreUtils.readPropertyFile("default.properties");
        Properties envRelatedProperty;
        if(StringUtils.isNotBlank(getOverriddenPropertyFromVMandEnv("env"))){
//        if(System.getProperties().containsKey("env")){
            String envVal = getOverriddenPropertyFromVMandEnv("env");
            envRelatedProperty = CoreUtils.readPropertyFile(envVal + ".properties");
            defaultProperties.putAll(envRelatedProperty);
            return defaultProperties;
        }
      if(defaultProperties.containsKey("env")){
            String envVal = defaultProperties.getProperty("env");
            try {
                envRelatedProperty = CoreUtils.readPropertyFile(envVal + ".properties");
                defaultProperties.putAll(envRelatedProperty);
                return defaultProperties;
            }catch (RuntimeException ex){
                logger.warn(ex.getMessage());
            }

        }
        return defaultProperties;
    }

    /**
     * Load the environment based test data from CSV file
     * NOTE : 'env' column is must in data file
     * @param csvDataFilePath CSV date file path to be loaded
     * @return data map
     */
    public static Map<String, Map<String, String>> loadTestDataIntoMap(String csvDataFilePath) {
        String env = getOverriddenProperty("env");
        if(StringUtils.isBlank(env)){
            throw new RuntimeException(String.format("ERROR : Data CSV can't be loaded as no 'env' is mentioned. DataCsvPath=%s",csvDataFilePath));
        }
        logger.info(String.format("Loading the data from data csv. Env=%s & DataCsvPath=%s",env,csvDataFilePath));
        Map<String, Map<String, String>> dataMap = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvDataFilePath))) {
            List<String[]> lines = reader.readAll();

            if (lines.isEmpty()) {
                return dataMap;
            }

            // Get the headers from the first row
            String[] headers = lines.get(0);

            // Process the remaining rows
            for (int i = 1; i < lines.size(); i++) {
                String[] line = lines.get(i);

                if (line.length != headers.length) {
                    System.out.println("Skipping line due to incorrect number of columns: " + String.join(", ", line));
                    continue;
                }

                String key = line[0];
                Map<String, String> rowMap = new HashMap<>();
                for (int j = 1; j < line.length; j++) {
                    if(StringUtils.equalsIgnoreCase(env,line[1])){
                        rowMap.put(headers[j],line[j]);
                    }
                    else{
                        break;
                    }
                }
                if(rowMap.size()!=0){
                    dataMap.put(key, rowMap);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return dataMap;
    }


}
