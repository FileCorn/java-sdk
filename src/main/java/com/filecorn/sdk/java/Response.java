package com.filecorn.sdk.java;

/**
 * @author omidp
 *
 */
public class Response
{

    private final String result;
    private final String message;

    public Response(String result, String message)
    {
        this.result = result;
        this.message = message;
    }

    public String getResult()
    {
        return result;
    }

    public String getMessage()
    {
        return message;
    }

}
