package testbase;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import xlsuite.Xls_Reader;

public class TestBase {
	//initalizing the property file reading
	public static Properties CONFIG=null;
	public static Properties OR=null;
	public static Properties DATA=null;
	public static WebDriver dr=null;
	public static EventFiringWebDriver driver=null;
	public static boolean isLoggedIn=false;
	public static Xls_Reader datatable=null;
	
	public void initialize() throws Throwable{
	if(driver == null){	
		CONFIG = new Properties();
		FileInputStream fn = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//config/config.properties");
		CONFIG.load(fn);
		
		OR = new Properties();
		fn = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//objectrepository/OR.properties");
		OR.load(fn);
		
		DATA = new Properties();
		fn = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//testdata/DATA.properties");
		DATA.load(fn);
		
		//initalize the webdriver
		ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles.getProfile("default");
		if(CONFIG.getProperty("browser").equals("Firefox")){
			dr = new FirefoxDriver(profile);
		}else if(CONFIG.getProperty("browser").equals("IE")){
			System.setProperty("webdriver.ie.driver", "E:\\Selenium_Updated\\IE and Chrome driver\\IEDriverServer.exe");
			dr = new InternetExplorerDriver();
		}else if(CONFIG.getProperty("browser").equals("Chrome")){
			System.setProperty("webdriver.chrome.driver", "E:\\Selenium_Updated\\IE and Chrome driver\\chromedriver.exe");
			dr = new ChromeDriver();
		}
		//load the suite1.xlsx sheet 
		datatable = new Xls_Reader(System.getProperty("user.dir")+"//src//test//resources//xlsuite_Excel//Suite1.xlsx");
		
		driver = new EventFiringWebDriver(dr);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		//driver.navigate().refresh();
	}
  }
	public static WebElement getObject(String xpathKey){
		try{
		return driver.findElement(By.xpath(OR.getProperty(xpathKey)));
	}catch(Throwable T){
		//report error
		takeScreenshot(xpathKey);
		//Assert.assertTrue(T.getMessage(), false);
		Assert.assertTrue(false, T.getMessage());
	        return null;    	
	     }
	}
	
	// get the skip condition
	//true - N
	//false - Y
    public static boolean isSkip(String testCase){
          for(int rowNum=2; rowNum<=datatable.getRowCount("TestCases"); rowNum++){
	          if(testCase.equals(datatable.getCellData("TestCases", "TCID", rowNum))){
		               if(datatable.getCellData("TestCases", "Runmode", rowNum).equals("Y"))
			      return false;
		      else
			     return true;
	          }
          }
     return false;
   }
    
    public static void takeScreenshot(String filename){
    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\screenshots\\"+filename+".jpg"));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
