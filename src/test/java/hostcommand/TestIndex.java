package hostcommand;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestIndex {
	
	@Test
	public void testHostCommand() {
		System.out.println("Greetings from Manila!");
		//System.setProperty("webdriver.chrome.driver", "/Volumes/MyBackup/sportoco/javaproject/chromedriver");
		//System.setProperty("webdriver.gecko.driver", "/usr/local/Cellar/geckodriver/0.29.0");
		WebDriver driver = new ChromeDriver();
		driver.get("https://gmail.com");		
	}
	
}
