package mavenMod.walmartProject;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class walMartTest {

	WebDriver driver;
	WebDriverWait wait;
	String[] items = { "xbox one", "battlefield1", "router", "modem", "smart phone", "smart tv"};
	Random randomNumber;
	Select select;
	
	String chromeAddress = "C:\\Users\\daniel.smith\\Documents\\selenium-2.53.0\\selenium-2.53.0\\chromedriver.exe";
	
	Point location = new Point(960, 0);
	Dimension size = new Dimension(960, 1160);
	
	@BeforeTest
	public void openBrowser()
	{
		//Starts up the browser and navigate to the page
		System.setProperty("webdriver.chrome.driver", chromeAddress);
		driver = new ChromeDriver();
		driver.get("http://www.walmart.com");
		
		driver.manage().window().setPosition(location);
		driver.manage().window().setSize(size);
	}
	
	@Test
	public void searchTest() throws InterruptedException
	{
		//create a new random for the selection of the item
		randomNumber = new Random();
		int randomNum = randomNumber.nextInt(6);
		int quantityNum = randomNumber.nextInt(5);
		
		//header search bar
		WebElement searchBar = driver.findElement(By.id("global-search-input"));
		searchBar.click();
		searchBar.sendKeys(items[randomNum]);
		searchBar.sendKeys(Keys.ENTER);
		System.out.println("Searching for: " + items[randomNum].toString());
		
		//selecting the first item from the list
		WebElement firstItem = driver.findElement(By.xpath("//*[@id='tile-container']/div[1]/div/div/h4/a"));
		firstItem.click();
		
		//if it is a preorder a popup may appear
		try
		{
			Thread.sleep(1000);
			driver.findElement(By.xpath("/html/body/div[2]/section/section[4]/div[2]/div[1]/div[5]/div[2]/div/div[2]/div[1]/div/div[2]/div/div[3]/div/div[2]/div/div/div/button/i")).click();
			System.out.println("Preorder popup appearred");
		}
		catch(NoSuchElementException e)
		{
			System.out.println("No popover to remove");
		}
		
		//selecting the quantity 
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("product-quantity-dropdown-wrapper")));
		WebElement quantityDropdown = driver.findElement(By.className("product-quantity-dropdown-wrapper"));
		quantityDropdown.click();
		
		//displaying the random number and random number for the quantity
		System.out.println("random number: " +  randomNum);
		System.out.println("quantity number: " +  quantityNum);
		
		try{
			Thread.sleep(2000);
			
			if(quantityNum == 1)
			{
				driver.findElement(By.xpath("/html/body/div[2]/section/section[4]/div[2]/div[1]/div[5]/div[2]/div/div[2]/div[1]/div/div[2]/div/div[3]/div/div[1]/span/div/div/button[1]")).click();
			}
			else if (quantityNum == 2)
			{
				driver.findElement(By.xpath("/html/body/div[2]/section/section[4]/div[2]/div[1]/div[5]/div[2]/div/div[2]/div[1]/div/div[2]/div/div[3]/div/div[1]/span/div/div/button[2]")).click();
			}
			else if (quantityNum == 3)
			{
				driver.findElement(By.xpath("/html/body/div[2]/section/section[4]/div[2]/div[1]/div[5]/div[2]/div/div[2]/div[1]/div/div[2]/div/div[3]/div/div[1]/span/div/div/button[3]")).click();
			}
			else if (quantityNum == 4)
			{
				driver.findElement(By.xpath("/html/body/div[2]/section/section[4]/div[2]/div[1]/div[5]/div[2]/div/div[2]/div[1]/div/div[2]/div/div[3]/div/div[1]/span/div/div/button[4]")).click();
			}
			else if (quantityNum == 5)
			{
				driver.findElement(By.xpath("/html/body/div[2]/section/section[4]/div[2]/div[1]/div[5]/div[2]/div/div[2]/div[1]/div/div[2]/div/div[3]/div/div[1]/span/div/div/button[5]")).click();
			}
			else if (quantityNum == 0)
			{
				System.out.println("quantity number is 0, submitting 1 as the quantity.");
				driver.findElement(By.xpath("/html/body/div[2]/section/section[4]/div[2]/div[1]/div[5]/div[2]/div/div[2]/div[1]/div/div[2]/div/div[3]/div/div[1]/span/div/div/button[1]")).click();
			}
		}
		catch(WebDriverException e)
		{
			System.out.println("Random number was too high for number of items. " + quantityNum);
		}
		
		//click on the submit button/preorder button
		try{
			driver.findElement(By.id("WMItemAddToCartBtn")).click();
		}
		catch(NoSuchElementException e)
		{
			driver.findElement(By.xpath("/html/body/div[2]/section/section[4]/div[2]/div[1]/div[5]/div[2]/div/div[2]/div[1]/div/div[2]/div/div[3]/div/div[2]/div")).click();
		}
		
		//display the information that appears next
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='spa-layout']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div[4]/div/div[1]/div")));
		WebElement quantityOut = driver.findElement(By.xpath("//*[@id='spa-layout']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div[4]"));
		System.out.println("Summary output: " + quantityOut.getText());
		//getting the total price
		WebElement price = driver.findElement(By.xpath("//*[@id='spa-layout']/div/div/div/div/div[2]/div/div[2]/div/div/div[1]/div[5]/p[2]/span"));
		System.out.println("Total price is: " + price.getText());
		//go into the shopping cart and remove the items
		WebElement cart = driver.findElement(By.linkText("View Cart"));
		cart.click();
		
		try
		{
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CartRemItemBtn")));
			WebElement remove = driver.findElement(By.id("CartRemItemBtn"));
			remove.click();
		}
		catch(NoSuchElementException e)
		{
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div/div[1]/div[1]/div/div[3]/div[1]/div/div[2]/div/div/div/div/div[7]/div/div/button[2]/span")));
			WebElement remove = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[1]/div[1]/div/div[3]/div[1]/div/div[2]/div/div/div/div/div[7]/div/div/button[2]/span"));
			remove.click();
		}
	}
	
	public void closeBrowser()
	{
		driver.close();
	}
}
