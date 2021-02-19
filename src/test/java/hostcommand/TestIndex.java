package hostcommand;

import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import hostcommand.DbConnection;
//import hostcommand.RecordedPlay;

public class TestIndex {
	
	private ArrayList<RecordedPlay> list;
	private WebDriver driver;
	
	@Test
	public void testHostCommand() throws InterruptedException {
		System.out.println("OPERATING SYSTEM: " + System.getProperty("os.name"));
		System.out.println("H-COMMAND URL: " + System.getProperty("url"));
		System.out.println("GAME ID: " + System.getProperty("gameId"));
		
		DbConnection conn = new DbConnection();
		conn.open();
		list = conn.getPlays();
		
				
		String chromedriverPath = "uiautomation_lib/chromedriver";			
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			chromedriverPath = "uiautomation_lib\\chromedriver.exe";			
		} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			chromedriverPath = "uiautomation_lib/chromedriver";			
		}
				
		System.setProperty("webdriver.chrome.driver", chromedriverPath);
		
		
		
		//System.setProperty("webdriver.gecko.driver", "/usr/local/Cellar/geckodriver/0.29.0");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(System.getProperty("url"));
		//driver.get("http://127.0.0.1:1030/?info=396bb3047679849b1085138c5621f6b8476367d7e33142a885bce1860e2c457a2451ae09077af6cd31b61d4bd116710126c4a3853a5f2a2b0481155ed632feeda3acc29973bc1d0941b73c462c2123a0bb805d13d928f575fb00d655904af606cf15d43feee3fcca21df4c58688aeb73dd8eb81e0242c7f2ee5ef6f8736c2f4bc1f89308563c50cebafa1a57dc20faba");
		
		Thread.sleep(2000);
		
		this.playIterator(0);

		Thread.sleep(5000);
		driver.quit();
	}
	
	private void playIterator(int i) throws InterruptedException {
		if (i < this.list.size()) {
			RecordedPlay play = this.list.get(i); 
			if (play.getRefId() != null) {
				if (play.getIsPreviousPlayEnded()) {
					Thread.sleep(new Long((long) (play.getWait() * 1000)));
				} else {

					if (play.getEvent().equalsIgnoreCase("click")) {
						
						try {
							Thread.sleep(new Long((long) (play.getWait() * 1000)));
							WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
							WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(play.getRefId())));
							el.click();
						} catch(TimeoutException e) {
							System.out.println("Failed to load click event " + play.getRefId());
						}
						
					} else if (play.getEvent().equalsIgnoreCase("select")) {
						
						try {
							Thread.sleep(new Long((long) (play.getWait() * 1000)));
							WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
							WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(play.getRefId())));
							el.click();
							Thread.sleep(1000);
							Select select = new Select(driver.findElement(By.id(play.getRefId())));
							select.selectByValue(play.getEventSelectValue());
						} catch(TimeoutException e) {
							System.out.println("Failed to load select event " + play.getRefId());
						}
						
					} else if (play.getEvent().equalsIgnoreCase("input")) {
						
						try {
							Thread.sleep(new Long((long) (play.getWait() * 1000)));
//							if (play.getRefId().equalsIgnoreCase("editor-1-Announce")) {
//								WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//								WebElement announceEl = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("editor-1-Announce")));							
//								WebElement el = announceEl.findElement(By.className("ql-blank"));
//								el.sendKeys(play.getEventSelectValue());
//								
//							} else if (play.getRefId().equalsIgnoreCase("editor-2-Announce")) {
//								WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//								WebElement announceEl = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("editor-2-Announce")));							
//								WebElement el = announceEl.findElement(By.className("ql-blank"));
//								el.sendKeys(play.getEventSelectValue());
//							} else if (play.getRefId().equalsIgnoreCase("editor-3-Announce")) {
//								WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//								WebElement announceEl = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("editor-3-Announce")));							
//								WebElement el = announceEl.findElement(By.className("ql-blank"));
//								el.sendKeys(play.getEventSelectValue());
//							} else {
//								WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//								WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(play.getRefId())));
//								el.sendKeys(play.getEventSelectValue());
//							}
							
							if (play.getRefId().contains("editor-1-Announce") || play.getRefId().contains("editor-2-Announce") || play.getRefId().contains("editor-3-Announce")) {
								WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
								WebElement announceEl = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(play.getRefId())));							
								WebElement el = announceEl.findElement(By.className("ql-blank"));
								el.sendKeys(play.getEventSelectValue());								
							} else {
								WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
								WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(play.getRefId())));
								el.sendKeys(play.getEventSelectValue());								
							}
							
						} catch(TimeoutException e) {
							System.out.println("Failed to load input event " + play.getRefId());
							
						}
						
					}
				}
				
			}
			
			this.playIterator(i + 1);
		}
	}
}
