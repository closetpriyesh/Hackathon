package service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Action {
	public static void clickOn(WebDriver driver, WebElement element) {
		new Actions(driver).click(element).build().perform();
	}

	public static void dragAndDropRight(WebDriver driver, WebElement element) {
		new Actions(driver).dragAndDropBy(element, 60, 0).build().perform();
	}

	public static void dragAndDropLeft(WebDriver driver, WebElement element) {
		new Actions(driver).dragAndDropBy(element, -60, 0).build().perform();
	}

	public static void clickWithRespectToWebElement(WebDriver driver, WebElement element) {

		new Actions(driver).moveToElement(element, -30, 0).click().build().perform();
	}

}