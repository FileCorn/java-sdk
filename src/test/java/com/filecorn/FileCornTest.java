package com.filecorn;

import java.io.File;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.filecorn.sdk.java.FileCorn;
import com.filecorn.sdk.java.FileCornApi;
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
    public void testUpload()
    {
        Response f = fc.upload(new File(getClass().getResource("/golestan_header.jpg").getFile()));
        System.out.println(f.getResult());
        System.out.println(f.getMessage());

    }

}
