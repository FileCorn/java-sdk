package com.filecorn.sdk.java;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.filecorn.sdk.java.Request.RequestDecorator;

/**
 * @author Omid Pourhadi
 *
 *         omidpourhadi [AT] gmail [DOT] com
 */
public class RequestBuilder
{

    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    public Request request;

    public RequestBuilder(Request request)
    {
        this.request = request;
    }

    public RequestDecorator Get() throws ClientProtocolException, IOException
    {
        RequestDecorator requestDecorator = request.get();
        HttpGet get = new HttpGet(request.getUrl());
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
        return requestDecorator;
    }
    
    public RequestDecorator Post() throws ClientProtocolException, IOException
    {
        RequestDecorator requestDecorator = request.get();
        HttpPost post = new HttpPost(request.getUrl());
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
        return requestDecorator;
    }

}
