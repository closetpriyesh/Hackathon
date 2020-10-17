package configurations;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import constants.WebConstants;

public class ConfigRemoteDriver {

	public WebDriver getInitializedDriver(Platform platform, String browser, String version)
			throws MalformedURLException {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setPlatform(platform);
		capability.setBrowserName(browser);
		capability.setVersion(version);
		URL browserStackURL = new URL(WebConstants.WEBURL);
		WebDriver driver = new RemoteWebDriver(browserStackURL, capability);
		ConfigDriver.initialize(driver);
		return driver;
	}
}
