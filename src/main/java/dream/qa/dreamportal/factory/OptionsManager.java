package dream.qa.dreamportal.factory;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;
public class OptionsManager {
	private ChromeOptions co;
	private FirefoxOptions fo;
	private SafariOptions so;
	private EdgeOptions eo;
	private Properties prop;


	public OptionsManager(Properties prop){
		this.prop=prop;
	}



	public ChromeOptions getChromeOptions() {
		co=new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			co.addArguments("--incognito");
		}
		return co;
	}
	public FirefoxOptions getFirefoxOptions() {
		fo=new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			fo.addArguments("--incognito");
		}
		return fo;
	}
	public EdgeOptions getEdgeOptions() {
		eo=new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			eo.addArguments("--incognito");
		}
		return eo;
	}
	public SafariOptions getSafariOptions() {
		so=new SafariOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			co.addArguments("--incognito");
		}
		return so;
	}

}


