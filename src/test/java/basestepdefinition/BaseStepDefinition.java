package basestepdefinition;

import configurations.ConfigDriver;
import configurations.WebDriverFactory;
import constants.WebConstants;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Set;

public class BaseStepDefinition {
	protected WebDriver driver;
	protected final String BASEURL = WebConstants.BASEURL;
	protected Set<String> windows;
	protected String base;
	protected List<String> urls;

	public BaseStepDefinition() {
		driver = WebDriverFactory.getWebDriver();
		ConfigDriver.initialize(driver);
	}

}
