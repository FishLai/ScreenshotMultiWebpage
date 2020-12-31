package cc.fishlab1.multi_url_screenshot;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
		System.setProperty("webdriver.chrome.driver", "src/assists/webdriver/chromedriver.exe");
        System.out.println( "Hello World!" );
        String fDir = "C:\\Users\\Nestor.LAI\\Downloads\\test-multi-urls.xlsx";
        HandleURLs handleURLs = new HandleURLs(fDir);
        ArrayList<String> urls = handleURLs.doHandleXlsx();
        
    }
}
