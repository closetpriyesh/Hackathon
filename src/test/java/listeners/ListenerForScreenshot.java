package listeners;

import configurations.WebDriverFactory;
import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerForScreenshot implements ITestListener {

	static Logger logger = Logger.getLogger(ListenerForScreenshot.class.getName());

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	@Attachment(value = "{0}", type = "text/html")
	public static String attachHtml(String html) {
		return html;
	}

	public void onStart(ITestContext iTestContext) {
		logger.info("I am in onStart method " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", WebDriverFactory.getDriver());
	}

	public void onFinish(ITestContext iTestContext) {
		logger.info("I am in onFinish method " + iTestContext.getName());
	}

	public void onTestStart(ITestResult iTestResult) {
		logger.info("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
	}

	public void onTestSuccess(ITestResult iTestResult) {
		logger.info("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
	}

	public void onTestFailure(ITestResult iTestResult) {
		logger.info("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
		WebDriver driver = WebDriverFactory.getDriver();
		if (driver instanceof WebDriver) {
			logger.info("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveScreenshotPNG(driver);
		}
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
	}

	public void onTestSkipped(ITestResult iTestResult) {
		logger.info("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		logger.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

}
