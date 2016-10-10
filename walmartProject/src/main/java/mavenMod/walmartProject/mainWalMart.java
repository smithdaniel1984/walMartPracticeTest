package mavenMod.walmartProject;

public class mainWalMart {
	
	public static walMartTest test = new walMartTest();
	static headerPicsTest headerPics = new headerPicsTest();

	public static void main(String[] args) throws InterruptedException {
		
		int count = 2; 
		
		for(int i = 1; i <= count; i++)
		{
			test.openBrowser();
			test.searchTest();
			test.closeBrowser();
			System.out.println("Current count: " + i);
			
			if(i == count)
			{
				System.out.println("Search test completed successfully.");
			}
		}
		
		headerPics.headerTest();
		System.out.println("Header Images test has completed successfully");
		//headerPics.closeBro();
		
		headerPics.homePageTest();
	}

}
