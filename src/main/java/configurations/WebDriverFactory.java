package configurations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import constants.LocationConstants;
import constants.WebConstants;

public class WebDriverFactory {
	private static WebDriver driver;
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

	public static WebDriver getWebDriver() {
		System.setProperty(WebConstants.CHROMEDRIVER, LocationConstants.CHROMEDRIVERLOCATION);
		driver = new ChromeDriver();
		tdriver.set(driver);
		return driver;
	}

	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}
}
