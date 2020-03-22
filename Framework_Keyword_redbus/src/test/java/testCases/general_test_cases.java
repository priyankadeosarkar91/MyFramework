package testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.keywordframework.browser.browser_keyword;
import com.keywordframework.browser.constants;

public class general_test_cases {
	@Test
	public void tc_001() {
		browser_keyword.OpenBrowser("Chrome");
		System.out.println("Chrome browser is opened");
	}
	
	@Test
	public void tc_002() {
		browser_keyword.OpenURL("https://www.redbus.in");
		System.out.println("URL-'https://www.redbus.in' is successfully launched in chrome browser.");	
	}
	
	@Test
	public void tc_003() {
		browser_keyword.getTitleofPage();
		}
	
	@Test
	public void tc_004() {
		browser_keyword.getElementfromRedBus("CSS","[class='redbus-logo home-redirect']");
		System.out.println("redBus logo is displayed.");
	}
	
	@Test(description = "VerifyMenuItems1")
	public void tc_005() {
		System.out.println("Actual Menu Items :  " + browser_keyword.verifyMenuItems("#page_main_header>nav>ul>li>a"));
		//constants.driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
		//First Approach
		for(String items:constants.expectedSubNAvItem1)
		{
			Assert.assertTrue(browser_keyword.verifyMenuItems("#page_main_header>nav>ul>li>a").contains(items),"Items in Menu are not Verified");
		}
	}
	
	@Test(description = "VerifyMenuItems2")
	public void tc_006() {
		browser_keyword.getElementfromRedBus("XPATH", "//div[@class='icon-down icon ich dib mbh']").click();
		System.out.println("Actual Menu Items :  " + browser_keyword.verifyMenuItems("#hmb>div>ul>li"));
		
		//Second Approach
		boolean isequal=browser_keyword.verifyMenuItems("#hmb>div>ul>li").containsAll(Arrays.asList(constants.expectedSubNavItems2));
		Assert.assertTrue(isequal,"Items in Menu are not Verified");
	}
	
	@Test(description = "VerifyMenuItems3")
	public void tc_007() {
		browser_keyword.getElementfromRedBus("CSS", "#sign-in-icon-down").click();
		System.out.println("Actual Menu Items :  " + browser_keyword.verifyMenuItems("#sign-in-icon-down>div>ul>li"));
		
		//Second Approach
		boolean isequal=browser_keyword.verifyMenuItems("#sign-in-icon-down>div>ul>li").containsAll(Arrays.asList(constants.expectedSubNavItems3));
		Assert.assertTrue(isequal,"Items in Menu are not Verified");
	}
	
	@Test
	public void tc_008(){
		browser_keyword.enterText("CSS", "input#src", "Shivaji Nagar, Pune");
		//browser_keyword.clickOnElementPerformOperationOnCurrentWindow("CSS", "li.selected");
		browser_keyword.enterText("CSS", "input#dest", "Nanded");
		
		/*browser_keyword.clickOnElementPerformOperationOnCurrentWindow("CSS", "#onward_cal.db");
		browser_keyword.clickOnElementPerformOperationOnCurrentWindow("CSS","#rb-calendar_onward_cal>table>tbody>tr>td.next");
		List<WebElement> date=constants.driver.findElements(By.cssSelector("td"));
		for(WebElement pickDate:date) {
		if(pickDate.getText().equals("1")) {
			pickDate.click();
			break;
			}
		}
		//browser_keyword.clickOnElementPerformOperationOnCurrentWindow("CSS", "#return_cal");
		browser_keyword.clickOnElementPerformOperationOnCurrentWindow("CSS", "button#search_btn");	
	*/
	}
	
	@Test
	public void tc_009() {
		WebElement menuTab=browser_keyword.mouseHoverOnElement("CSS","a#redBus");
		System.out.println("The tooltip/infotip on mouse hover is verified.");
		System.out.println("MenuTab text is    :   "+menuTab.getText() + "  MenuTab atrritube is: "+ menuTab.getAttribute("title"));
		System.out.println("On mouse hover, the tooltip/infotip is displayed the information (**Info/Hover Box)about tab/element.");
	}
	
	
	@Test
	public void tc_010() {
		browser_keyword.clickOnElementAndBackToParentWindow("CSS","a#redBus");
		System.out.println("Click event is successfully executed.");
	}
	
	@Test
	public void tc_011() {
		browser_keyword.handleWindowInNewTab("XPATH", "//a[@href='/info/redcare']");
		browser_keyword.clickOnElementPerformOperationOnCurrentWindow("CSS", "div.modalCloseSmall>i");
		constants.driver.close();
		constants.driver.switchTo().window(constants.parentWindowHandle);
		System.out.println(constants.driver.getTitle());
	}
}
