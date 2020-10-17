package stepdefinitions;

import basestepdefinition.BaseStepDefinition;
import configurations.ConfigDriver;
import configurations.WebDriverFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.HotelBooking;

public class HotelStepDefinition extends BaseStepDefinition {
	HotelBooking bookHotel;

	@Given("^user arrived on homepage$")
	public void user_is_already_on_login_page() {
		driver = WebDriverFactory.getWebDriver();
		ConfigDriver.initialize(driver);
		driver.get(BASEURL);
		bookHotel = PageFactory.initElements(driver, pages.HotelBooking.class);
	}

	@When("^heading is MakeMyTrip$")
	public void title_of_home_page_is_MakeMyTrip2() {
		String title = driver.getTitle();
		Assert.assertEquals("MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights &amp; Holiday", title);
	}

	@Then("^user clicks on hotels$")
	public void user_clicks_on_hotels() throws Throwable {
		bookHotel.clickHotels();
	}

	@Then("^user fills the destination city$")
	public void user_fills_the_destination_city() throws Throwable {
		bookHotel.fillDestinationCity();
	}

	@Then("^user enters checkin and checkout date$")
	public void user_enters_checkin_and_checkout_date() throws Throwable {

		bookHotel.selectCheckInDate("Thu Aug 27 2020");
		bookHotel.selectCheckOutDate("Thu Sep 03 2020");
	}

	@Then("^user clicks on search button$")
	public void user_clicks_on_search_button() throws Throwable {
		bookHotel.clickSearch();
	}

	@Then("^user closes the location suggestion box$")
	public void user_closes_the_location_suggestion_box() throws Throwable {
		bookHotel.closeLocationSuggestion();
	}

	@Then("^user filters output$")
	public void user_filters_output() throws Throwable {
		bookHotel.applyFilters();
	}

	@Then("^user sort the hotels based on price from low to high$")
	public void user_sort_the_hotels_based_on_price_from_low_to_high() throws Throwable {
		bookHotel.sortByPriceLowToHigh();
	}

	@Then("^user clears all the applied filters$")
	public void user_clears_all_the_applied_filters() throws Throwable {
		bookHotel.clearAllFilters();
	}

	@Then("^close hotel window$")
	public void close_the_browser2() {
		driver.quit();
	}
}
