package stepdefinitions;

import basestepdefinition.BaseStepDefinition;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.HomePage;
import service.Hold;

public class HomePageStepDefinition extends BaseStepDefinition {

	HomePage homepage;

	@Given("^user is already on homepage$")
	public void user_is_already_on_login_page1() {
		driver.get(BASEURL);

	}

	@When("^title is MMT$")
	public void title_of_home_page_is_MakeMyTrip1() {
		String title = driver.getTitle();
		Assert.assertEquals("MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights &amp; Holiday", title);
	}

	@Then("^initialize homepage factory$")
	public void initialize_homepage_factory() {
		homepage = PageFactory.initElements(driver, pages.HomePage.class);
	}

	@Then("^user clicks on flights and checks if its link is working properly$")
	public void user_clicks_on_flights_and_checks_if_its_link_is_working_properly() {
		homepage.clickFlights();
		String flightUrl = driver.getCurrentUrl();
		Assert.assertEquals(flightUrl, BASEURL + "flights/");

	}

	@Then("^user clicks on hotels and checks if its link is working properly$")
	public void user_clicks_on_hotels_and_checks_if_its_link_is_working_properly() {
		homepage.clickHotels();

		String hotelUrl = driver.getCurrentUrl();
		Assert.assertEquals(hotelUrl, BASEURL + "hotels/");
	}

	@Then("^user clicks on villas&apts and checks if its link is working properly$")
	public void user_clicks_on_villas_apts_and_checks_if_its_link_is_working_properly() {
		homepage.clickVillas();

		String villaUrl = driver.getCurrentUrl();
		Assert.assertEquals(villaUrl, BASEURL + "homestays/");

	}

	@Then("^user clicks on holidays and checks if its link is working properly$")
	public void user_clicks_on_holidays_and_checks_if_its_link_is_working_properly() {

		homepage.clickHolidays();

		String holidayUrl = driver.getCurrentUrl();
		Assert.assertEquals(holidayUrl, BASEURL + "holidays-india/");
	}

	@Then("^user clicks on trains and checks if its link is working properly$")
	public void user_clicks_on_trains_and_checks_if_its_link_is_working_properly() {
		homepage.clickTrains();
		String trainUrl = driver.getCurrentUrl();
		Assert.assertEquals(trainUrl, BASEURL + "railways/");
	}

	@Then("^user clicks on buses and checks if its link is working properly$")
	public void user_clicks_on_buses_and_checks_if_its_link_is_working_properly() {
		homepage.clickBuses();
		String busUrl = driver.getCurrentUrl();
		Assert.assertEquals(busUrl, BASEURL + "bus-tickets/");
	}

	@Then("^user clicks on cabs and checks if its link is working properly$")
	public void user_clicks_on_cabs_and_checks_if_its_link_is_working_properly() {

		homepage.clickCabs();

		String cabUrl = driver.getCurrentUrl();
		Assert.assertEquals(cabUrl, BASEURL + "cabs/");

	}

	@Then("^user clicks on visa and checks if its link is working properly$")
	public void user_clicks_on_visa_and_checks_if_its_link_is_working_properly() {
		homepage.clickVisa();

		String visaUrl = driver.getCurrentUrl();
		Assert.assertEquals(visaUrl, BASEURL + "visa/");
	}

	@Then("^user clicks on giftcards and checks if its link is working properly$")
	public void user_clicks_on_giftcards_and_checks_if_its_link_is_working_properly() {

		homepage.clickGiftCards();
		String giftCardUrl = driver.getCurrentUrl();
		Assert.assertEquals(giftCardUrl, BASEURL + "gift-cards/?intid=Header_ch_giftcard");
		driver.navigate().back();
	}

	@Then("^user goes to deals page$")
	public void user_clicks_on_more_and_goes_to_deals_page() {
		homepage.clickMore();
		base = driver.getWindowHandle();
		windows = driver.getWindowHandles();
		driver.switchTo().window(windows.toArray(new String[0])[1]);
		Hold.hold(driver);
	}

	@Then("^user verifies if the deal page is working properly$")
	public void user_verifies_if_the_deal_page_is_working_properly() {
		String moreUrl = driver.getCurrentUrl();
		Assert.assertEquals(moreUrl, BASEURL + "daily-deals/");
	}

	@Then("^user closes the deals window$")
	public void user_closes_the_deals_window() {
		driver.close();
		driver.switchTo().window(base);
		Hold.hold(driver);
	}

	@Then("^user gets the footer links$")
	public void user_gets_the_footer_links() {
		urls = homepage.getUrls();

	}

	@Then("^user displays the broken links$")
	public void user_displays_the_broken_links() {
		for (String url : urls) {
			try {
				RequestSpecification httpRequest = RestAssured.given();
				Response response = httpRequest.request(Method.GET, url);
				int statusCode = response.getStatusCode();
				System.out.println(statusCode);
				Assert.assertEquals(statusCode, 200);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Then("^close home window$")
	public void close_home_window() {
		driver.quit();
	}
}
