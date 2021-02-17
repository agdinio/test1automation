package hostcommand;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestIndex {
	
	@Test
	public void testHostCommand() throws InterruptedException {
		System.out.println("OPERATING SYSTEM: " + System.getProperty("os.name"));
		System.out.println("H-COMMAND URL: " + System.getProperty("url"));
		System.out.println("GAME ID: " + System.getProperty("gameId"));
		
		String chromedriverPath = "uiautomation_lib/chromedriver";			
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			chromedriverPath = "uiautomation_lib\\chromedriver.exe";			
		} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			chromedriverPath = "uiautomation_lib/chromedriver";			
		}
				
		System.setProperty("webdriver.chrome.driver", chromedriverPath);
		
		
		
		//System.setProperty("webdriver.gecko.driver", "/usr/local/Cellar/geckodriver/0.29.0");
		WebDriver driver = new ChromeDriver();
		driver.get(System.getProperty("url"));		
		Thread.sleep(10000);
		driver.quit();
	}
	
}
