package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties prop;

    private static ConfigFileReader confRead;

    private ConfigFileReader()
    {
        BufferedReader reader;
        String propFilePath = "configs//configuration.properties";
        try
        {
            reader = new BufferedReader(new FileReader(propFilePath));
            prop= new Properties();
            try
            {
                prop.load(reader);
                reader.close();
            } catch(IOException e)
            {
                e.printStackTrace();
            }
        }catch(FileNotFoundException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found");
        }
    }

    public static ConfigFileReader getInstance()
    {
        if(confRead == null)
        {
            confRead = new ConfigFileReader();

        }
        return confRead;
    }

    public String getBaseUrl() {
        String baseUrl = prop.getProperty("base_Url");
        if (baseUrl != null)
            return baseUrl;
        else
            throw new RuntimeException("base_Url not specified in configuration.properties file ");
    }

    public String getUserID() {
        String baseUrl = prop.getProperty("user_Id");
        if (baseUrl != null)
            return baseUrl;
        else
            throw new RuntimeException("base_Url not specified in configuration.properties file ");
    }
    }


