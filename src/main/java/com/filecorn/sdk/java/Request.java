package com.filecorn.sdk.java;

import java.io.File;

/**
 * @author Omid Pourhadi
 *
 */
public class Request
{

    private String url;
    private String postContent;
    private String pathParameter;
    private byte[] uploadFile;

    public Request(String url)
    {
        this.url = url;
    }

    public byte[] getUploadFile()
    {
        return uploadFile;
    }

    public void setUploadFile(byte[] uploadFile)
    {
        this.uploadFile = uploadFile;
    }

    public String getUrl()
    {
        return url;
    }

    public String getPostContent()
    {
        return postContent;
    }

    public void setPostContent(String postContent)
    {
        this.postContent = postContent;
    }

    public String getPathParameter()
    {
        return pathParameter;
    }

    public void setPathParameter(String pathParameter)
    {
        this.pathParameter = pathParameter;
    }

    public String getPostConent()
    {
        return postContent;
    }

    public static class RequestDecorator
    {

        public RequestDecorator(byte[] content)
        {
            this.content = content;
        }

        private byte[] content;

        public byte[] getContent()
        {
            return content;
        }

        public String getContentAsString()
        {
            if (content == null || content.length == 0)
                return "";
            return new String(content);
        }

    }

}
