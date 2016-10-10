package mavenMod.walmartProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import mavenMod.walmartProject.walMartTest;

public class headerPicsTest {
	
	static walMartTest test = new walMartTest();
	Actions action;
	
	public void headerTest() throws InterruptedException
	{
		
		//open the browser from the previous test
		test.openBrowser();
		
		//click on the next button to go through the images 4 times
		for(int i = 1; i <= 5; i++)
		{	
			Thread.sleep(1000);
			//declare the action for mouse movement
			action = new Actions(test.driver);
			
			WebElement hover = test.driver.findElement(By.xpath("/html/body/div/div/div/div/div[1]/div/div/div[1]/span[1]/div"));
			action.moveToElement(hover).build().perform();
			//click the next pics button
			test.driver.findElement(By.xpath("/html/body/div/div/div/div/div[1]/div/div/div[1]/span[1]/div/div/div[3]/button")).click();	
		
			System.out.println("count " + i);
		}
		//clicking the previous button
		for(int i = 1; i <= 5; i++)
		{	
			Thread.sleep(1000);
			//declare the action for mouse movement
			action = new Actions(test.driver);
			
			WebElement hover = test.driver.findElement(By.xpath("/html/body/div/div/div/div/div[1]/div/div/div[1]/span[1]/div"));
			action.moveToElement(hover).build().perform();
			//click the previous button
			test.driver.findElement(By.xpath("/html/body/div/div/div/div/div[1]/div/div/div[1]/span[1]/div/div/div[2]/button")).click();
		
			System.out.println("Reverse count " + i);
		}
		//clicking on each of the dots representing the slides available
		for(int i = 1; i <= 5; i++)
		{
			Thread.sleep(1000);
			WebElement title = test.driver.findElement(By.xpath("/html/body/div/div/div/div/div[1]/div/div/div[1]/span[1]/div/div/div[4]/ul/li["+ i +"]"));
			title.click();
			System.out.println("Clicking on link: " + i );
		}
	}
	
	public void homePageTest()
	{
		//click to return to the home page
		test.driver.findElement(By.xpath("/html/body/div/div/div/div/header/div/div[2]/div/div/div/div/div/div[3]/a")).click();
		
		WebElement section1 = test.driver.findElement(By.xpath("/html/body/div/div/div/div/div[1]/div/div/div[1]/span[2]/div/div[1]/div/h5"));
		System.out.println("Section one header: " + section1.getText());
	}
	
	public void closeBro()
	{
		test.closeBrowser();
	}
}
