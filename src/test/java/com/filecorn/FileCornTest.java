package com.filecorn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.filecorn.sdk.java.Env;
import com.filecorn.sdk.java.FileCorn;
import com.filecorn.sdk.java.FileCornApi;
import com.filecorn.sdk.java.FolderResponse;
import com.filecorn.sdk.java.Item;
import com.filecorn.sdk.java.Response;

/**
 * @author omidp
 *
 */
public class FileCornTest
{

    FileCornApi fc;

    @Before
    public void setUp()
    {
        fc = new FileCorn();
    }

    @Test
    @Ignore
    public void testCreateFolder()
    {
        Response f = fc.createFolder("omidbiz");
        System.out.println(f.getResult());
        System.out.println(f.getMessage());
        Response f2 = fc.createFolder("jedlab");
        System.out.println(f2.getResult());
        System.out.println(f2.getMessage());
    }

    @Test
    @Ignore
    public void testUpload()
    {
        Response f = fc.upload(new File(getClass().getResource("/golestan_header.jpg").getFile()));
//        Response f = fc.upload(new File(getClass().getResource("/test.txt").getFile()));
        System.out.println(f.getResult());
        System.out.println(f.getMessage());

    }

    @Test
    @Ignore
    public void testFolderList() throws ParseException
    {

        FolderResponse f = fc.folderList("jedlab");
        List<Item> items = f.getItems();
        for (Item item : items)
        {
            System.out.println(item.getName());
        }
        System.out.println(f.getItemCount());

    }
    
    @Test
    @Ignore
    public void testDeleteFolder() throws ParseException
    {

        Response f = fc.deleteFolder("jedlab");
        System.out.println(f.getResult());
        System.out.println(f.getMessage());

    }
    
    @Test
    @Ignore
    public void testDeleteFile() throws ParseException
    {

        Response f = fc.deleteFile("golestan.jpg");
        System.out.println(f.getResult());
        System.out.println(f.getMessage());

    }
    
    @Test
    @Ignore
    public void testDownloadFile() throws ParseException, IOException
    {
        InputStream downloadFile = fc.downloadFile("/omid/sd/280_1.mp3");
        File file = new File(Env.USER_HOME + "/tt.mp3");
        if(file.exists())
            file.delete();
        file.createNewFile();
        OutputStream os = new FileOutputStream(file);
        IOUtils.copy(downloadFile, os);
    }

}
