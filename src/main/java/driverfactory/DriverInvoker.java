package driverfactory;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import utils.Utils;

/**
 * 
 */
public class DriverInvoker {

	Properties properties = Utils.getProperties();
	WebDriver driver;

	public WebDriver getDriver(String browser) {
		browser = "chrome";

		switch (browser) {

		case "chrome":
			System.setProperty("webdriver.chrome.driver", properties.getProperty("path.chrome"));
			driver = new ChromeDriver();
			return driver;

		case "firefox":
			System.setProperty("webdriver.chrome.driver", properties.getProperty("path.firefox"));
			driver = new FirefoxDriver();
			return driver;

		case "ie":
			System.setProperty("webdriver.chrome.driver", properties.getProperty("path.ie"));
			driver = new InternetExplorerDriver();
			return driver;

		default:
			System.setProperty("webdriver.chrome.driver", properties.getProperty("path.chrome"));
			driver = new ChromeDriver();
			return driver;
		}
	}
}
