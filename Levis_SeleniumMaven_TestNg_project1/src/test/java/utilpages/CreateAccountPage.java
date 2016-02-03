package utilpages;

import java.util.UUID;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import testbase.TestBase;

public class CreateAccountPage extends TestBase {
	
	public static void accountCreate() throws Throwable{
		
		getObject("global_country").click();
		getObject("signin_link").click();
		getObject("email_newaccount_creation").clear();
		//getObject("email_newaccount_creation").sendKeys(DATA.getProperty("signup_userid"));
		//
		final String randomEmail = randomEmail();
		getObject("email_newaccount_creation").sendKeys(randomEmail);
		getObject("password_newaccount_creation").clear();
		getObject("password_newaccount_creation").sendKeys(DATA.getProperty("signup_password"));
		getObject("createaccount_button").click();
		Thread.sleep(100);
		String success_msg_expected = DATA.getProperty("success_txt_expected");
		String success_msg_actual = getObject("registration_success_text").getText();
		//assertTrue(success_msg_actual.contains(success_msg_expected));
		Assert.assertEquals(success_msg_actual, success_msg_expected);
		System.out.println("Account creation and Registration successfull.");
		Thread.sleep(3000);
	}
	public static void logOut_from_Createdaccount() throws Throwable{
		Actions act = new Actions(driver);
		act.clickAndHold(getObject("signout_dropdown")).build().perform();
		
		//moveToElement(getObject("signout_link")).click();
		
		getObject("signout_link").click();
		System.out.println("User Logged out");
		Thread.sleep(5000);
	}
	
	public static String randomEmail() {
	    return "ab" + UUID.randomUUID().toString() + "@ex.com";

   }
	
}	
