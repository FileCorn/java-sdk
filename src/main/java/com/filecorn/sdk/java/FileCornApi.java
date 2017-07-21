package com.filecorn.sdk.java;

import java.io.File;
import java.io.InputStream;

public interface FileCornApi
{

    
    public Response createFolder(String folderName);
    
    public Response upload(File file);
    
    public Response upload(byte[] file, String fileName);
    
    public FolderResponse folderList(String folderName);
    
    public Response deleteFolder(String folderName);
    
    public Response deleteFile(String fileName);
    
    public InputStream downloadFile(String fileName);
    
    public InputStream downloadFileByUri(String uri);
    
}
