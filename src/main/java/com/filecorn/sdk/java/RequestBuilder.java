package com.filecorn.sdk.java;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.filecorn.sdk.java.Request.RequestDecorator;

/**
 * @author Omid Pourhadi
 *
 *         omidpourhadi [AT] gmail [DOT] com
 */
public class RequestBuilder
{
    
    private static final Logger logger = LoggerFactory.getLogger(RequestBuilder.class);


    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    public Request request;

    public RequestBuilder(Request request)
    {
        this.request = request;
    }

    public RequestDecorator get() throws ClientProtocolException, IOException
    {
        
        StringBuilder uri = new StringBuilder().append(request.getUrl());
        if(request.getPathParameter() != null)
        {
            if(request.getPathParameter().startsWith("/") == false)
                uri.append("/");
            uri.append(request.getPathParameter());
        }
        HttpGet get = new HttpGet(uri.toString());
        get.setHeader("Accept", "application/json");
        get.setHeader("User-Agent", "Mozilla/5.0");
        get.setHeader("Authorization", String.format("FC %s", Env.getApiKey()));
        CloseableHttpResponse response = httpclient.execute(get);
        InputStream content = null;
        try
        {
            int statusCode = response.getStatusLine().getStatusCode();
            content = response.getEntity().getContent();
            byte[] byteArray = IOUtils.toByteArray(content);
            return new RequestDecorator(byteArray);
        }
        catch (Exception e)
        {
        }
        finally
        {
            IOUtils.closeQuietly(response);
            IOUtils.closeQuietly(content);
        }
        return new RequestDecorator("".getBytes());
    }
    
    public RequestDecorator post() throws ClientProtocolException, IOException
    {
        HttpPost post = new HttpPost(request.getUrl());
        post.setHeader("Authorization", String.format("FC %s", Env.getApiKey()));
        post.setEntity(new StringEntity(request.getPostConent(), "UTF-8"));
        CloseableHttpResponse response = httpclient.execute(post);
        InputStream content = null;
        try
        {
            int statusCode = response.getStatusLine().getStatusCode();
            content = response.getEntity().getContent();
            byte[] byteArray = IOUtils.toByteArray(content);
            return new RequestDecorator(byteArray);
        }
        catch (Exception e)
        {
        }
        finally
        {
            IOUtils.closeQuietly(response);
            IOUtils.closeQuietly(content);
        }
        return new RequestDecorator("".getBytes());
    }

    
    public RequestDecorator put() throws ClientProtocolException, IOException
    {
        StringBuilder uri = new StringBuilder().append(request.getUrl());
        if(request.getPathParameter() != null)
        {
            if(request.getPathParameter().startsWith("/") == false)
                uri.append("/");
            uri.append(request.getPathParameter());
        }
        HttpPut put = new HttpPut(uri.toString());
        if(request.getPostConent() != null)
            put.setEntity(new StringEntity(request.getPostConent(), "UTF-8"));
        put.setHeader("Authorization", String.format("FC %s", Env.getApiKey()));
        CloseableHttpResponse response = httpclient.execute(put);
        InputStream content = null;
        try
        {
            int statusCode = response.getStatusLine().getStatusCode();
            content = response.getEntity().getContent();
            byte[] byteArray = IOUtils.toByteArray(content);
            return new RequestDecorator(byteArray);
        }
        catch (Exception e)
        {
            logger.info(e.getMessage());
        }
        finally
        {
            IOUtils.closeQuietly(response);
            IOUtils.closeQuietly(content);
        }
        return new RequestDecorator("".getBytes());
    }
    
    public RequestDecorator upload() throws ClientProtocolException, IOException
    {
        StringBuilder uri = new StringBuilder().append(request.getUrl());
        if(request.getPathParameter() != null)
        {
            if(request.getPathParameter().startsWith("/") == false)
                uri.append("/");
            uri.append(request.getPathParameter());
        }
        HttpPut post = new HttpPut(uri.toString());        
        post.setHeader("Authorization", String.format("FC %s", Env.getApiKey()));
        post.setHeader("Accept", "*/*");
//        ByteArrayEntity bae = new ByteArrayEntity(request.getUploadFile());
//        FileInputStream fs = new FileInputStream(request.getUploadFile());
        ByteArrayInputStream bais = new ByteArrayInputStream(request.getUploadFile());
        InputStreamEntity reqEntity = new InputStreamEntity(
                bais, -1, ContentType.APPLICATION_OCTET_STREAM);
        reqEntity.setChunked(true);
        post.setEntity(reqEntity);
        
        
        CloseableHttpResponse response = httpclient.execute(post);
        InputStream content = null;
        try
        {
            int statusCode = response.getStatusLine().getStatusCode();
            content = response.getEntity().getContent();
            byte[] byteArray = IOUtils.toByteArray(content);
            return new RequestDecorator(byteArray);
        }
        catch (Exception e)
        {
            logger.info(e.getMessage());
        }
        finally
        {
            IOUtils.closeQuietly(response);
            IOUtils.closeQuietly(content);
        }
        IOUtils.closeQuietly(bais);
        return new RequestDecorator("".getBytes());
    }
    
    public RequestDecorator delete() throws ClientProtocolException, IOException
    {
        StringBuilder uri = new StringBuilder().append(request.getUrl());
        if(request.getPathParameter() != null)
        {
            if(request.getPathParameter().startsWith("/") == false)
                uri.append("/");
            uri.append(request.getPathParameter());
        }
        HttpDelete delete = new HttpDelete(uri.toString());
        delete.setHeader("Authorization", String.format("FC %s", Env.getApiKey()));
        delete.setHeader("X-FC-RECURSIVE", "true");
        CloseableHttpResponse response = httpclient.execute(delete);
        InputStream content = null;
        try
        {
            int statusCode = response.getStatusLine().getStatusCode();
            content = response.getEntity().getContent();
            byte[] byteArray = IOUtils.toByteArray(content);
            return new RequestDecorator(byteArray);
        }
        catch (Exception e)
        {
            logger.info(e.getMessage());
        }
        finally
        {
            IOUtils.closeQuietly(response);
            IOUtils.closeQuietly(content);
        }
        return new RequestDecorator("".getBytes());
    }
    
    public InputStream download()
    {
        StringBuilder uri = new StringBuilder().append(request.getUrl());
        if(request.getPathParameter() != null)
        {
            if(request.getPathParameter().startsWith("/") == false)
                uri.append("/");
            uri.append(request.getPathParameter());
        }
        return new JdkHttpConnection().sendGetRequest(uri.toString());
    }

    
}
