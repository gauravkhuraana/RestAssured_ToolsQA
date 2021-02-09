package apiEng;


import io.restassured.response.Response;

public class RestResponse<T> implements IRestResponse<T> {

private T data;
private Response res;
private Exception e;

public RestResponse(Class<T> t,Response res)
{
    this.res=res;
    try
    {
        this.data = t.newInstance();
    }
    catch (Exception e)
    {
        throw new RuntimeException("There should be a default constructor in the response POJO ");
    }
}

public String getContent()
{
    return res.getBody().asString();
}

public int getStatusCode()
{
    return res.getStatusCode();
}

public String getStatusDescription()
{
    return res.getStatusLine();
}
public Response getResponse()
{
    return res;
}

public boolean isSuccessful()
{
    int code=res.getStatusCode();
    if(code == 200 || code ==201 || code ==202|| code ==203|| code ==204|| code ==205)
    {
        return true;
    }
    return false;
}

public T getBody()
{
    try
    {
        data =(T) res.getBody().as(data.getClass());
    }
    catch(Exception e)
    {
        this.e =e;
    }
    return data;
}

public Exception getException()
{
    return e;
}
}
