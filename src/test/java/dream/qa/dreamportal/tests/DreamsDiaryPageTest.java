package dream.qa.dreamportal.tests;

import org.testng.annotations.Test;

import dream.qa.dreamportal.base.BaseTest;


public class DreamsDiaryPageTest extends BaseTest{

	@Test (priority = 1) 
	public void dreamsDiaryPageTest() throws InterruptedException{
		dreamsDiaryPage.clickOnMyDreamsButton();
		dreamsDiaryPage.dreamsCount();
	}
}

