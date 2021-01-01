package cc.fishlab1.multi_url_screenshot;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class WebBot {
	private String url;
	private WebDriver driver;
	private Screenshot shot;
	
	public WebBot() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--window-size=1980,1080");
		
		WebDriver driver = new ChromeDriver(options);
		//driver.manage().window().maximize();
		this.driver = driver;
	}
	
	public void openWebpage() {
		WebDriver driver = this.driver;
		String url = this.url;
		driver.get(url);
		waitForPageLoaded(driver);
		this.driver = driver;
	}
	
	public void screenshot() {
		WebDriver driver = this.driver;
		Screenshot takeshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		this.shot = takeshot;
		waitForPageLoaded(driver);
		this.driver = driver;
	}
	
	public void save(String fn) throws IOException {
		Screenshot shot = this.shot;
		ImageIO.write(shot.getImage(), "jpg", new File(fn));
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	private void waitForPageLoaded(WebDriver driver) {
		ExpectedCondition<Boolean> expectation = new 
				ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};
		
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.print("Timeout waiting for Page Load Request to complete");
		}
	}
}
