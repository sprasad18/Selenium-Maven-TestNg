package tests;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import testbase.TestBase;
import utilpages.LoginLogoutPage;

public class LoginLogoutTest extends TestBase {
	
	@BeforeTest
	public void beforeTest() throws Throwable{
		initialize();
		
		if(TestBase.isSkip("LoginLogoutTest"))
		     //Assume.assumeTrue(false);
			Assert.assertTrue(false);
	}
	
	@Test
	public void loginTest() throws Throwable{
		driver.navigate().to(CONFIG.getProperty("testSiteName"));
		LoginLogoutPage.doLogin();
		if(!isLoggedIn){
			//report error
			TestBase.takeScreenshot("loginerror_1");
			System.out.println("Invalid username password");
		}
		Thread.sleep(5000);
	}
	
	@Test
	public void logoutTest(){
		LoginLogoutPage.logout();
	}
	
	@AfterTest
	public static void afterTest(){
		driver.quit();
	}
}
