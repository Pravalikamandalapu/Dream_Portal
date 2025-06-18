package dream.qa.dreamportal.factory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;


public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	

	public WebDriver initDriver(Properties prop) {
		optionsManager= new OptionsManager(prop);
		highlight=prop.getProperty("highlight").trim();
		String browserName=prop.getProperty("browser").toLowerCase().trim();
		
		System.out.println("browser name is "+ browserName);
		System.out.println(driver);
		if(browserName.equalsIgnoreCase("chrome")) {
			//driver =new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("safari")) {
			//driver =new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			//driver =new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			//driver =new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		}
		else {
			System.out.println("plz pass the right browser"+browserName);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		System.out.println(getDriver());
		return getDriver();
		 
	}
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");
		System.out.println("Running test on env: " + envName);

		try {
			if (envName == null) {   //null
				System.out.println("No env is passed, running tests on QA env");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;

				default:
					//	                	

				}
			}

			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
			// Log the error message
			System.err.println("Error loading configuration: " + e.getMessage());
		} finally {
			// Close the FileInputStream
			if (ip != null) {
				try {
					ip.close();
				} catch (IOException e) {
					e.printStackTrace();
					// Log the error message
					System.err.println("Error closing FileInputStream: " + e.getMessage());
				}
			}
		}

		return prop;
	}




	public static String getScreenshot() {
		File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination=new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}


	
}
