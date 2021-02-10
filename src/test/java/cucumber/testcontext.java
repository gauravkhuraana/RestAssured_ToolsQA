package cucumber;

import apiEng.Endpoints;
import dataProvider.ConfigFileReader;
import enums.Context;

public class testcontext {

/*    private String BASE_URL="https://bookstore.toolsqa.com";
    private String USER_ID="effb7fbb-c4e4-4e4d-9f24-53ff5a0215f7";*/
    private Endpoints endpoints;
    private ScenarioContext sccontext;

    public testcontext()
    {
        endpoints = new Endpoints(ConfigFileReader.getInstance().getBaseUrl());
        sccontext = new ScenarioContext();
        sccontext.setContext(Context.USER_ID,ConfigFileReader.getInstance().getUserID());
    }
    public Endpoints getEndpoints()
    {
        return endpoints;
    }
    public ScenarioContext getSccontext()
    {
        return sccontext;
    }
}
