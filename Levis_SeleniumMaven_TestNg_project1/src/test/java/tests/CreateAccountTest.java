package tests;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import testbase.TestBase;
import utilpages.CreateAccountPage;

public class CreateAccountTest extends TestBase {
	@BeforeTest
	public void beforeTest() throws Throwable{
		initialize();
		
		if(TestBase.isSkip("CreateAccountTest"))
		    // Assume.assumeTrue(false);
			Assert.assertTrue(false);
		
	}
	@Test
	public void registrationTest() throws Throwable{
		driver.get(CONFIG.getProperty("testSiteName"));
		CreateAccountPage.accountCreate();
		Thread.sleep(200);
		CreateAccountPage.logOut_from_Createdaccount();

	}
	
	@AfterTest
	public static void afterTest(){
		driver.quit();
	}
}
