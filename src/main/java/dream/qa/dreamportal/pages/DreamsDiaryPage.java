package dream.qa.dreamportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import dream.qa.dreamportal.utils.ElementUtil;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
public  class DreamsDiaryPage {

	private WebDriver driver;	
	private static ElementUtil eleUtil; //for Utils add this line
	//1. Private By Locators

	private By myDreams = By.id("dreamButton");

	//3. Create a page actions

	public DreamsDiaryPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver); //for Utils add this line
	}



	@Step("...clicking on Dreams button...")
	public  void clickOnMyDreamsButton() {
		eleUtil.waitForElementVisible(myDreams,20);
		// Create Actions instance
		Actions actions = new Actions(driver);

		// Perform click
		actions.moveToElement(driver.findElement(myDreams)).click().perform();
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println(windowHandles);
		List<String> tabs = new ArrayList<>(windowHandles);
		driver.switchTo().window(tabs.get(2)); // Index 1 is the new tab
	}

	@Step("...counting the dreams...")
	public  void dreamsCount() {
		try {
			List<WebElement> dreamscount = driver.findElements(By.xpath("//table//tr/td[3]"));

			int goodCount = 0;
			int badCount = 0;

			for (WebElement type : dreamscount) {
				String text = type.getText().trim();
				if (text.equalsIgnoreCase("Good")) {
					goodCount++;
				} else if (text.equalsIgnoreCase("Bad")) {
					badCount++;
				}
			}

			int totalCount = goodCount + badCount;

			System.out.println("Good Dreams: " + goodCount);
			System.out.println("Bad Dreams: " + badCount);
			System.out.println("Total Dreams: " + totalCount);
		} finally {

		}
	}
}