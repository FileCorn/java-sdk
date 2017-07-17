package com.filecorn.sdk.java;

/**
 * @author omidp
 *
 */
public class FileCornException extends RuntimeException
{

    private int code;

    public FileCornException(int code)
    {
        this.code = code;
    }

    public FileCornException(int code, String message)
    {
        super(message);
        this.code = code;
    }

}
