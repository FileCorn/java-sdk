package com.filecorn.sdk.java;

import java.io.File;

public interface FileCornApi
{

    
    public Response createFolder(String folderName);
    
    public Response upload(File file);
    
    public Response upload(byte[] file, String fileName);
    
    public FolderResponse folderList(String folderName);
    
    public Response deleteFolder(String folderName);
    
    public Response deleteFile(String fileName);
    
}
