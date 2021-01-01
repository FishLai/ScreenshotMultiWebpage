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
        String fDir = "src/assists/source/URLs.txt";
        HandleURLs handleURLs = new HandleURLs(fDir);
        ArrayList<String> urls = handleURLs.doRead();
        WebBot webBot = new WebBot();
        String saveTo, fn;
        
        for(int i=0; i<urls.size();i++) {
        	fn = "test" + String.valueOf(i) + ".jpg";
        	saveTo = "src/assists/output/picture/" + fn;
        	System.out.println("capture count : "+i );
        	webBot.setUrl(urls.get(i));
        	webBot.openWebpage();
        	webBot.screenshot();
        	webBot.save(saveTo);
        }
        System.out.println("end");
        webBot.closeDriver();
    }
}
