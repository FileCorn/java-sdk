package com.filecorn.sdk.java;

import java.io.File;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;

/**
 * @author omidp
 *
 */
public class FileCorn extends Request implements FileCornApi
{

    RequestBuilder requestBuilder;

    public FileCorn()
    {
        super(Env.getCubeUrl());
        requestBuilder = new RequestBuilder(this);
    }

    public Response createFolder(String folderName)
    {
        try
        {
            if (folderName.endsWith("/") == false) folderName += "/";
            setPathParameter(folderName);
            RequestDecorator put = requestBuilder.put();
            String content = put.getContentAsString();
            if (content != null)
            {
                JSONObject json = new JSONObject(content);
                return new Response(json.getString("result"), json.getString("message"));
            }
            return new Response("failed", "unable to complete request");
        }
        catch (ClientProtocolException e)
        {
            throw new FileCornException(0);
        }
        catch (IOException e)
        {
            throw new FileCornException(0);
        }
    }

    public void folderList(String folderName)
    {
        throw new UnsupportedOperationException();
    }

    public Response upload(File file)
    {
        setPathParameter(file.getName());
        setUploadFile(file);
        try
        {
            RequestDecorator upload = requestBuilder.upload();
            String content = upload.getContentAsString();
            if (content != null)
            {
                JSONObject json = new JSONObject(content);
                return new Response(json.getString("result"), json.getString("message"));
            }
            return new Response("failed", "unable to complete request");
        }
        catch (ClientProtocolException e)
        {
            throw new FileCornException(0);
        }
        catch (IOException e)
        {
            throw new FileCornException(0);
        }
    }

}
