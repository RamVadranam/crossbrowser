package crossbrowser;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


public class SearchTest {
	
	WebDriver driver;
	
	
	@Parameters("browser")
	@BeforeTest
	public void setUp(String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
			
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			
			System.setProperty("webdriver.gecko.driver","geckodriver");
			
			driver = new FirefoxDriver();
		}
		
		driver.get("http://webdriver.io/");
		
		
	}
	
	@Test
	public void searchItem() {
		
		driver.findElement(By.cssSelector("body > nav > ul > li:nth-child(4)")).click();
		
		driver.findElement(By.cssSelector("input[name=\"search\"]")).sendKeys("element");
	
		driver.findElement(By.cssSelector(".commands protocol active:nth-child(11)")).click();
	}
	
	//@Test
	
	public void tabsSelection() throws InterruptedException {
		
	WebElement menu =	driver.findElement(By.cssSelector("img[title='webdriver.io']"));
		
		
	Actions action = new Actions(driver);
	
	action.moveToElement(menu).perform();
	
	
	WebElement element =  driver.findElement(By.linkText("TestRunner"));
	
	element.click();
	
	Thread.sleep(18000);
	
	menu =	driver.findElement(By.cssSelector("img[title='webdriver.io']"));
	
	
	
	action.moveToElement(menu).perform();
	Thread.sleep(5000);
	element = driver.findElement(By.linkText("element"));
	Assert.assertEquals(element.isDisplayed(), true);
	element.click();
	
	
			
		
	}
	
	//@Test
	public void tabSelector() throws InterruptedException {
		
	   WebDriverWait wait = new WebDriverWait(driver,30);
	   
	   for(int i=1;i<7;i++)
	   {
		   
		   WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#curtainLeft > ul > li:nth-child("+i+") > a > span.large-title")));
		   element.click();
	   }
		
		
	}
	
	//@Test 
	public void scorll() throws InterruptedException {
		
       Dimension d = new Dimension(360,640);
		
		driver.manage().window().setSize(d);
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		WebElement element = driver.findElement(By.cssSelector("#menuBtn"));
		
		element.click();
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#curtainLeft > ul > li:nth-child(1) > a > span.large-title")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.className("close")).click();
		Thread.sleep(2000);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
     
		 element = driver.findElement(By.cssSelector("a[href=\"/en-gb/explore/playstation-plus/this-month-on-ps-plus\"]"));
		
		jse.executeScript("arguments[0].scrollIntoView();", element);

		Thread.sleep(2000);
		
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();


		Thread.sleep(8000);
	    element = driver.findElement(By.xpath("//div[text()='Register now']"));
		
		element.click();
	}
	
	
	@AfterTest
	public void tearDown() {
		
		driver.quit();
		
	}
	

}
