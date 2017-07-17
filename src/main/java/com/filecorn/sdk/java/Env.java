package com.filecorn.sdk.java;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

/**
 * Immutable Environment Configuration
 * 
 * @author omid pourhadi : omidpourhadi AT gmail DOT com
 * @version 1.0
 */
public final class Env
{

    public static final String NL = System.getProperty("line.separator");
    public static final String FILE_SEPARATOR = File.separator;
    public static final String OS = System.getProperty("os.name");
    public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
    public static final String USER_HOME = System.getProperty("user.home");

    private static Properties prop = new Properties();

    private Env()
    {

    }

    static
    {
        reload();
    }

    /**
     * <b>Must</b> be caled from Seam Synchronized component
     */
    public static synchronized void reload()
    {
        InputStream stream = null;
        try
        {
            stream = Env.class.getClassLoader().getResourceAsStream("/conf.properties");
            if(stream == null)
                stream = Env.class.getResourceAsStream("/conf.properties");
            if(stream == null)
                stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("/conf.properties");
            prop.load(stream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            IOUtils.closeQuietly(stream);
        }
    }

    public static String getUserHome()
    {
        return System.getenv("user.home");
    }

    public static String getApiKey()
    {
        return prop.getProperty("API_KEY");
    }

    public static String getCubeUrl()
    {
        return prop.getProperty("CUBE_URL");
    }

}
