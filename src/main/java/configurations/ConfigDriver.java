package configurations;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class ConfigDriver {
	public static void initialize(WebDriver driver) {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	}
}
