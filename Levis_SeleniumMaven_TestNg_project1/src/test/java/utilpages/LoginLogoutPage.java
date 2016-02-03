package utilpages;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import testbase.TestBase;

public class LoginLogoutPage extends TestBase {
	
	public static void doLogin() throws Throwable{
		
		if(isLoggedIn){
			//return;
			logout();
		}
		
		getObject("global_country").click();
	    //driver.navigate().refresh();
		getObject("signin_link").click();
		getObject("signin_username").clear();
		//----Login from DATA.property file username ----------
		getObject("signin_username").sendKeys(DATA.getProperty("userid"));
		
		//----Login from RandomEmail username ----------
		/*final String randomEmail = CreateAccountPage.randomEmail();
		getObject("signin_username").sendKeys(randomEmail);*/
		
		getObject("signin_password").clear();
		getObject("signin_password").sendKeys(DATA.getProperty("password"));
		
		getObject("signin_button").click();
		Thread.sleep(5000L);
		try{
			//String displayedusername = 	getObject("username_top_link").getText();
		    String displayedusername = driver.findElement(By.xpath(OR.getProperty("username_top_link"))).getText().toLowerCase();
		    if(displayedusername.contains(DATA.getProperty("userid"))){
			isLoggedIn=true;
			System.out.println("User Loggedin");
		   }else{
			isLoggedIn=false ;
			System.out.println("User Not Loggedin");
		       }
		
		   }catch(Throwable t){
			isLoggedIn=false;
		       }
	}
	public static void logout(){
		if(isLoggedIn){
			Actions act = new Actions(driver);
			act.clickAndHold(getObject("signout_dropdown")).build().perform();
			getObject("signout_link").click();
			System.out.println("User Logged out");
			
		}
	}

}
