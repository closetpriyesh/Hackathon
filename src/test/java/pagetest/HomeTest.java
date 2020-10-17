package pagetest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.State;
import org.openqa.selenium.WebElement;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import driverfactory.DriverInvoker;
import pages.HomePage;
import utils.Utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 *
 * This Test checks if there are any Broken Links.
 */
public class HomeTest extends BaseTest {
	HomePage homeObject;
	DriverInvoker invoker = new DriverInvoker();

	@BeforeMethod
	public void openBrowser() {
		driver = invoker.getDriver(browser);
		driver.manage().window().maximize();
		driver.get(properties.getProperty("url"));
		Utils.waitForMintime();
		homeObject = new HomePage(driver);
	}

	@Test
	public void testBrokenLinks() {
		RestAssured.baseURI = "https://api.covid19india.org/data.json";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get();
		LinkedHashMap<String, Object> jsonResponse =  response.getBody().jsonPath().get("$");
		List<String> topThreeStates = homeObject.getTop3ActiveStates();
		List<State> statesData = response.getBody().jsonPath().getList("statewise", State.class);
		List<State> finalStatesData = statesData.stream().filter(st -> topThreeStates.contains(st.state)).collect(Collectors.toList());
		List<WebElement> activeList = homeObject.getActiveList();
		List<WebElement> confirmedList = homeObject.getConfirmedList();
		SoftAssertions softAssertions = new SoftAssertions();

		IntStream.range(0, finalStatesData.size()).forEach(idx -> {
			softAssertions.assertThat(finalStatesData.get(idx).confirmed)
					.overridingErrorMessage("Data discrepancy")
					.isEqualTo(confirmedList.get(idx).getText().replace(",", ""));
		});

		IntStream.range(0, finalStatesData.size()).forEach(idx -> {
			softAssertions.assertThat(finalStatesData.get(idx).active)
					.overridingErrorMessage("Data discrepancy")
					.isEqualTo(activeList.get(idx).getText().replace(",",""));
		});
		softAssertions.assertAll();
	}
}
