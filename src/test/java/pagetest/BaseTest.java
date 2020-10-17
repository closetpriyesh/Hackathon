package pagetest;

import java.io.File;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import utils.Utils;
/**
 * 
 *
 * This is the Base Test which all the Tests in our Project needs to extend.
 */
public class BaseTest {

	WebDriver driver;
	Logger logger = Utils.getLogger();
	Properties properties = Utils.getProperties();
	String browser = "chrome";
	String reportName = "Result.html";
	static ExtentReports extentReport;
	static ExtentTest extentLogger;
	static ThreadLocal<ExtentTest> reportLoggerThread;

	@BeforeSuite
	public void initReports() {
		reportLoggerThread = new ThreadLocal<>();
		extentReport = new ExtentReports(System.getProperty("user.dir") + "/test-output/" + reportName, true);
		extentReport.addSystemInfo("Host Name", "Epam").addSystemInfo("Environment", "Automation Testing")
				.addSystemInfo("User Name", "Team E");
		extentReport.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
		browser = properties.getProperty("browser.name");
	}

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		extentLogger = extentReport.startTest(context.getName());
		reportLoggerThread.set(extentLogger);
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			reportLoggerThread.get().log(LogStatus.FAIL, result.getName() + " failed.");
			reportLoggerThread.get().log(LogStatus.FAIL, result.getThrowable().getMessage());
			String screenshotPath = Utils.getScreenshot(driver, result.getName());
			reportLoggerThread.get().log(LogStatus.FAIL, extentLogger.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			reportLoggerThread.get().log(LogStatus.SKIP, result.getName() + " skipped.");
		} else {
			reportLoggerThread.get().log(LogStatus.PASS, result.getName() + " passed.");
		}
		extentReport.endTest(reportLoggerThread.get());
		driver.quit();
	}

	@AfterSuite
	public void close() {
		extentReport.flush();
		extentReport.close();
	}

}
