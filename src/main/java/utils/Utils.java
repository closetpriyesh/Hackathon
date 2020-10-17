package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 *         instance of Logger and Properties to be used across the TestScripts.
 */
public final class Utils {
	private static Logger logger;
	static Properties properties;
	static InputStream input;
	private static final long WAIT_TIME = 2000;

	public static void waitForMintime() {
		try {
			Thread.sleep(WAIT_TIME);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	public static void waitForMintime(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	public static boolean isPresent(List<WebElement> list) {
		if (list.size() > 0)
			return true;
		return false;
	}

	public static boolean isPresent(WebElement element) {
		if (element == null)
			return false;
		return true;
	}

	public static Logger getLogger() {
//		if (logger == null) {
//			logger = LogManager.getRootLogger();
//		}
		return logger;
	}

	public static Properties getProperties() {
		if (properties == null) {
			try {
				input = new FileInputStream("src\\main\\resources\\config.properties");
				properties = new Properties();
				properties.load(input);
			} catch (FileNotFoundException e) {
				logger.info("File Not Found Exception");
			} catch (IOException e) {
				logger.info("IO Exception");
			}
		}
		return properties;
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(src, finalDestination);
		return destination;
	}
}
