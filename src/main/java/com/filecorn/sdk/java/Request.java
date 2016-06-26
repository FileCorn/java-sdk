package com.filecorn.sdk.java;

/**
 * @author Omid Pourhadi
 *
 */
public abstract class Request
{

    private String url;
    private String postContent;

    public Request(String url)
    {
        this.url = url;
    }

    public Request(String url, String postContent)
    {
        this.url = url;
        this.postContent = postContent;
    }

    public String getUrl()
    {
        return url;
    }

    public String getPostConent()
    {
        return postContent;
    }

    public abstract RequestDecorator get();

    public abstract RequestDecorator post();

    public abstract RequestDecorator delete();

    public abstract RequestDecorator select();

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
