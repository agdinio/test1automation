package hostcommand;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Index {

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println("Greetings from SPORTOCO!" + System.getProperty("os.name"));
		
		String chromedriverPath = "uiautomation_lib/chromedriver";			
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			chromedriverPath = "uiautomation_lib\\chromedriver.exe";			
		} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			chromedriverPath = "uiautomation_lib/chromedriver";			
		}
				
		System.setProperty("webdriver.chrome.driver", chromedriverPath);
		
		
		
		//System.setProperty("webdriver.gecko.driver", "/usr/local/Cellar/geckodriver/0.29.0");
		WebDriver driver = new ChromeDriver();
		driver.get("https://gmail.com");		
	}

}
