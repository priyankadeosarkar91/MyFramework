package com.keywordframework.browser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sound.midi.SysexMessage;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;

import io.github.bonigarcia.wdm.WebDriverManager;

public class browser_keyword {

/** 
 * This method is used to open browser in either of following "CHROME", "Firefox", Internet browser "IE", 
 * "Opera","HTMLUnit", "Safari".
 * @param browserName {@code String}
 * @author Priyanka
 *
 */
	public static void OpenBrowser(String browserName) {
		//ChromeOptions options = new ChromeOptions();
		//options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		
		switch (browserName) {

		case "Chrome":
			WebDriverManager.chromedriver().setup();
			constants.driver = new ChromeDriver();
			break;

		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			constants.driver = new FirefoxDriver();
			break;

		case "IE":
			WebDriverManager.iedriver().setup();
			constants.driver = new InternetExplorerDriver();
			break;

		case "Opera":
			WebDriverManager.operadriver().setup();
			constants.driver = new OperaDriver();
			break;

		case "HTMLUnit":
			constants.driver = new HtmlUnitDriver();
			break;

		case "Safari":
			break;

		default:
			System.out.println("Invalid browser :  " + browserName);
			break;
		}
		// Manage Open browser with maximized mode
		constants.driver.manage().window().maximize();
	}
	
	/**This Method is used to open Url as specified in String.
	 * @param url{@code String}
	 * @author Priyanka
	 */
	public static void OpenURL(String url) {
		constants.driver.get(url);
	}
	
	/**This Method presented title of the webpage.
	 * @author Priyanka
	 * @return 
	 */
	public static String getTitleofPage() {
		String title=constants.driver.getTitle();
		return title;
	}
	
	/**
	 * This method is used to enter text if text-box is available.
	 * {@code SendKeys} for send text in text-box
	 * @param locatorType
	 * @param locatorval
	 * @param textval
	 */
	public static void enterText(String locatorType,String locatorval, String textval) {
		WebElement textbox=getElementfromRedBus(locatorType, locatorval);
		textbox.sendKeys(textval);
	}
	
	/** This method is used to get specific web element as required with parameter locatorType {@code String}, 
	 * locatorval {@code String}. This webelement to perform different action.
	 *
	 * @param locatorType {@code String}
	 * @param locatorval {@code String}
	 * @return {@link WebElement}
	 * @author Priyanka
	 */
	public static WebElement getElementfromRedBus(String locatorType,String locatorval) {
		WebElement element = null;
		switch (locatorType) {
		case "CLASSNAME":
			element=constants.driver.findElement(By.className(locatorval));
			break;
		case "CSS":
			element=constants.driver.findElement(By.cssSelector(locatorval));
			break;
		case "ID":
			element=constants.driver.findElement(By.id(locatorval));
			break;
		case "LINK_TEXT":
			element=constants.driver.findElement(By.linkText(locatorval));
			break;
		case "NAME":
			element=constants.driver.findElement(By.name(locatorval));
			break;
		case "PARTIAL_LINK_TEXT":
			element=constants.driver.findElement(By.partialLinkText(locatorval));
			break;
		case "TAG_NAME":
			element=constants.driver.findElement(By.tagName(locatorval));
			break;
		case "XPATH":
			element=constants.driver.findElement(By.xpath(locatorval));
			break;
			
		default:
			System.err.println("Invalid Locator Type to be selected : " + locatorType);
			break;
		}
		return element;
	}
	

	/** This method is used to get specific web element as required with parameter elementName {@code String}, 
	 *  This webelement is used to search with contain text method perform different action.
	 * 
	 * @param elementName {@code String}
	 * @return {@link WebElement}
	 * @author Priyanka
	 */
	public static WebElement getElementfromRedBus(String elementName) {
		WebElement elemnt=constants.driver.findElement(By.xpath("//*[contains[text(),'"+elementName+"']"));
		return elemnt;
	}
	
	
	/**This method presents click on element action and perform action, stay on current window which is clicked 
	 * as required with parameter locatorType {@code String}, locatorval {@code String}. 
	 * 
	 * @param locatorType
	 * @param locatorval
	 * 
	 * @author Priyanka
	 */
	public static void clickOnElementPerformOperationOnCurrentWindow(String locatorType, String locatorval) {
		WebElement element=getElementfromRedBus(locatorType, locatorval);
		element.click();
	}
	

	/**This method presents click on element action and perform action, back to parent window 
	 * as required with parameter locatorType {@code String}, locatorval {@code String}. 
	 * 
	 * @param locatorType
	 * @param locatorval
	 *
	 * @author Priyanka
	 */
	public static void clickOnElementAndBackToParentWindow(String locatorType, String locatorval) {
		constants.parentWindowHandle=constants.driver.getWindowHandle();
		System.out.println("Parent Window handle : " + constants.parentWindowHandle);
		
		clickOnElementPerformOperationOnCurrentWindow(locatorType, locatorval);
		
		if(constants.parentWindowHandle.equalsIgnoreCase(constants.driver.getWindowHandle())){
			constants.driver.navigate().back();
		}
	}
	

	/**This method presents multiple window handle when click on element action 
	 * as required with parameter locatorType {@code String}, 
	 * locatorval {@code String}. 
	 * 
	 * @param locatorType
	 * @param locatorval
	 * @author Priyanka
	 */
	public static void handleWindowInNewTab(String locatorType, String locatorval) {
		constants.parentWindowHandle=constants.driver.getWindowHandle();
		System.out.println("Parent Window handle : " + constants.parentWindowHandle);
		WebElement element=getElementfromRedBus(locatorType, locatorval);
		element.click();
		
		Set <String> childWindow=constants.driver.getWindowHandles();
		System.out.println("child Window handles : " + childWindow);
		
		for(String handle:childWindow) {
			if(!constants.parentWindowHandle.equalsIgnoreCase(handle)) {
				constants.driver.switchTo().window(handle);
			}
		}
	}

	/** This Method is used to verify menu items on the Homepage. actual menu items stored in {@code List}.
	 * {@code ArrayList} used to get element of list in the form of array to compare with expected menu items.
	 * 
	 * @param subNavItems {@code List}
	 * @param actualSubNavItems {@code ArrayList}
	 * @param itr {@code Iterator}
	 * @return {@link ArrayList} actualSubNavItems
	 * @author Priyanka
	 */
	public static ArrayList<String> verifyMenuItems(String locatorval) {
		List<WebElement> subNavItems=constants.driver.findElements(By.cssSelector(locatorval));
		//List<WebElement> subNavItems=constants.driver.findElements(By.cssSelector("#page_main_header>nav>ul>li>a")); //List of menu item1>>CSS path 
		
		ArrayList<String> actualSubNavItems=new ArrayList<String>();
		Iterator<WebElement> itr=subNavItems.iterator();
		while(itr.hasNext()) {
			actualSubNavItems.add(itr.next().getText());
		}
		return actualSubNavItems;
	}
	
		/**This method perform action of mouse Hover On Element as required with parameter locatorType {@code String}, 
	 * locatorval {@code String}. 
	 * 
	 * @param locatorType
	 * @param locatorval
	 * {@link Actions}
	 * @return 
	 */
	public static WebElement mouseHoverOnElement(String locatorType, String locatorval) {
		Actions action=new Actions(constants.driver);
		WebElement tab=getElementfromRedBus(locatorType, locatorval);
		action.moveToElement(tab);
		//action.perform();
		return tab;
	}
}

