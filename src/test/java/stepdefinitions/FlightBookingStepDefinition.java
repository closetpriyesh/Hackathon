package stepdefinitions;

import basestepdefinition.BaseStepDefinition;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import exceptions.FlightNotFoundException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.FlightBooking;

import java.util.List;
import java.util.Set;

public class FlightBookingStepDefinition extends BaseStepDefinition {

	FlightBooking bookFlight;

	@Given("^user has reached homepage$")
	public void user_is_already_on_login_page3() {
		driver.get(BASEURL);
		bookFlight = PageFactory.initElements(driver, pages.FlightBooking.class);
	}

	@When("^description is MakeMT$")
	public void title_of_home_page_is_MakeMyTrip3() {
		String title = driver.getTitle();
		Assert.assertEquals("MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights &amp; Holiday", title);
	}

	@Then("^initialize flight factory$")
	public void initialize_flight_factory() throws Throwable {

	}

	@Then("^user clicks on more and goes to deals page$")
	public void user_clicks_on_more_and_goes_to_deals_page() throws FlightNotFoundException {
		bookFlight.navigateToDeals(driver);
		base = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		driver.switchTo().window(windows.toArray(new String[0])[1]);

	}

	@Then("^user fills the travelling details$")
	public void user_fills_the_travelling_details() throws InterruptedException {
		bookFlight.fillDealCities("Durgapur", "Khajuraho");
		bookFlight.selectDealJourneyDate("Thu Sep 10 2020");
	}

	@Then("^user inputs the \"(.*?)\" , \"(.*?)\"  and \"(.*?)\"$")
	public void user_inputs_the_source_destination_and_doj(String source, String destination, String doj)
			throws InterruptedException, FlightNotFoundException {
		bookFlight.sendDataSourceCity(source);
		bookFlight.sendDataDestinationCity(destination);
		bookFlight.selectJourneyDate(doj);

	}

	@Then("^user search for flights$")
	public void user_search_for_flights() {
		bookFlight.clickSearch();
	}

	@Then("^user search for deal flights$")
	public void user_search_for_deal_flights() {
		bookFlight.searchDealFlights();
	}

	@Then("^user applies various filters$")
	public void apply_various_filters() {
		bookFlight.ApplyStopFilter();
		bookFlight.clickMoreFilters();
		// bookFlight.dragPriceLeftSlider();
		bookFlight.dragPriceRightSlider();
		bookFlight.applyMoreFilters();
	}

	@Then("^store initial price list$")
	public void store_initial_price_list() throws FlightNotFoundException {
		bookFlight.storeInitialPrice();
	}

	@Then("^sort the flights based on price in descending order$")
	public void sort_the_flights_based_on_price_in_descending_order() {

		bookFlight.sortByPriceDec();
	}

	@Then("^check if the price is sorted correctly$")
	public void check_if_the_price_is_sorted_correctly() {
		List<Integer> initialPrice = bookFlight.getInitalPriceInDec();
		int listSize = initialPrice.size();
		List<Integer> finalPrice = bookFlight.getFinalPriceInDec();
		Assert.assertEquals(listSize, initialPrice.size());
		for (int index = 0; index < listSize; index++) {
			Assert.assertEquals(initialPrice.get(index), finalPrice.get(index));
		}
	}

	@Then("^user selects one flight and click book now$")
	public void user_selects_one_flight_and_click_book_now() throws Throwable {
		bookFlight.setJourneyTimings();
		bookFlight.clickBookNow();
	}

	@Then("^user reviews the booking$")
	public void user_reviews_the_booking() throws Throwable {

		base = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		driver.switchTo().window(windows.toArray(new String[0])[1]);

		String deptTime = bookFlight.getDepartureTime();
		String displayedDeptTime = bookFlight.getDisplayedDepartureTime();

		String arrivalTime = bookFlight.getArrivalTime();
		String displayedArrivalTime = bookFlight.getDisplayedArrivalTime();
		Assert.assertEquals(deptTime, displayedDeptTime);
		Assert.assertEquals(arrivalTime, displayedArrivalTime);
	}

	@Then("^user confirms the review$")
	public void user_confirms_the_review() {
		int reviewPageNo = Integer.parseInt(bookFlight.getReviewPageNumber());

		System.out.println(reviewPageNo);
		Assert.assertEquals(2, reviewPageNo);
		bookFlight.uncheckDonation();
		bookFlight.continueReview();

		int travellerDetailsPageNumber = Integer.parseInt(bookFlight.getTravelDetailsPageNumber());
		Assert.assertEquals(3, travellerDetailsPageNumber);

		driver.close();
		driver.switchTo().window(base);
	}

	@Then("^close the browser$")
	public void close_the_browser() {
		driver.quit();
	}

}
