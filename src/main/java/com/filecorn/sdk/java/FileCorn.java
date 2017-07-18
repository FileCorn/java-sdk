package com.filecorn.sdk.java;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author omidp
 *
 */
public class FileCorn extends Request implements FileCornApi
{

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

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

    public FolderResponse folderList(String folderName)
    {
        if (folderName.endsWith("/") == false) folderName += "/";
        setPathParameter(folderName);
        try
        {
            RequestDecorator rd = requestBuilder.get();
            String content = rd.getContentAsString();
            java.util.List<Item> items = new ArrayList<Item>();
            if (content != null)
            {
                JSONObject json = new JSONObject(content);
                //
                JSONArray jsonArray = json.getJSONArray("items");
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonItem = jsonArray.getJSONObject(i);
                    Item item = new Item();
                    String createdAt = jsonItem.getString("created_at");
                    item.setCreatedAt(sdf.parse(createdAt));
                    item.setMimeType(jsonItem.getString("mime_type"));
                    item.setName(jsonItem.getString("name"));
                    item.setSize(jsonItem.getString("size"));
                    int isf = jsonItem.getInt("is_folder");
                    if (isf != 0) item.setFolder(true);
                    item.setUrl(jsonItem.getString("url"));
                    items.add(item);
                }

                return new FolderResponse(items, json.getLong("items_count"));
            }
            return new FolderResponse(items, 0L);
        }
        catch (ClientProtocolException e)
        {
            throw new FileCornException(0);
        }
        catch (IOException e)
        {
            throw new FileCornException(0);
        }
        catch (ParseException e)
        {
            // DONOTHING
            return null;
        }
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

    public Response deleteFolder(String folderName)
    {
        if (folderName.endsWith("/") == false) folderName += "/";
        setPathParameter(folderName);       
        try
        {
            RequestDecorator upload = requestBuilder.delete();
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

    public Response deleteFile(String fileName)
    {
        if (fileName.endsWith("/")) 
            throw new FileCornException(20, "file can not end with /");
        setPathParameter(fileName);       
        try
        {
            RequestDecorator upload = requestBuilder.delete();
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
