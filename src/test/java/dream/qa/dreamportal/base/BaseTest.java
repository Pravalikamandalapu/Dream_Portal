package dream.qa.dreamportal.base;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import dream.qa.dreamportal.factory.DriverFactory;

import dream.qa.dreamportal.pages.DreamsDiaryPage;





public class BaseTest {
	DriverFactory df;
	protected WebDriver driver;
	protected Properties prop;
	protected SoftAssert softAssert;
	
	//dream portal 
	protected DreamsDiaryPage dreamsDiaryPage;
	




	@BeforeTest
	public void setup() {
		df=new DriverFactory();
		prop=df.initProp();
		driver=df.initDriver(prop);
		dreamsDiaryPage=new DreamsDiaryPage(driver);
		
		softAssert=new SoftAssert();
	}
	@AfterTest
	public void tearDown(){
		//driver.quit();
	}
}
