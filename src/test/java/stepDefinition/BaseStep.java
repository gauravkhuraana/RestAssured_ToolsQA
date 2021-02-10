package stepDefinition;

import apiEng.Endpoints;
import cucumber.ScenarioContext;
import cucumber.testcontext;

public class BaseStep {

    private static final String BASE_URL="https://bookstore.toolsqa.com";
    private Endpoints end;
    private ScenarioContext scContext;

    public BaseStep(testcontext tc)
    {
        end=tc.getEndpoints();
        scContext=tc.getSccontext();

    }
    public Endpoints getEndPoints()
    {
        return end;
    }
    public ScenarioContext getScContext()
    {
        return scContext;
    }

}
