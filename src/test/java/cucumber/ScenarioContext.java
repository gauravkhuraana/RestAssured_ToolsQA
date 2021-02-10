package cucumber;

import enums.Context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private Map<String,Object> scContext;

    public ScenarioContext()
    {
        scContext= new HashMap<String,Object>();
    }

    public void setContext(Context key,Object value)
    {
        scContext.put(key.toString(),value);
    }
    public Object getContext(Context key)
    {
        return scContext.get(key.toString());
    }
    public Boolean isContains(Context key)
    {
        return scContext.containsKey(key.toString());
    }
}
