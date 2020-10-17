package pages;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import exceptions.FlightNotFoundException;
import io.qameta.allure.Step;
import service.Action;
import service.CalendarMonth;
import service.Hold;

public class FlightBooking {

	@FindBy(xpath = "//input[@id ='fromCity']")
	WebElement source;

	@FindBy(xpath = "//input[@id ='toCity']")
	WebElement destination;

	@FindBy(xpath = "//input[@placeholder='From']")
	WebElement sourceField;

	@FindBy(xpath = "//input[@placeholder='To']")
	WebElement destinationField;

	@FindBy(xpath = "//span[@aria-label='Next Month']")
	WebElement nextMonth;

	@FindBy(xpath = "//label[@for='departure']/span")
	WebElement calendar;

	@FindBy(xpath = "//div[@class='react-autosuggest__section-container react-autosuggest__section-container--first']//ul[@class='react-autosuggest__suggestions-list']//li[1]")
	WebElement exactSource;

	@FindBy(xpath = "//div[@class='react-autosuggest__section-container react-autosuggest__section-container--first']//ul[@class='react-autosuggest__suggestions-list']//li[1]")
	WebElement exactDestination;

	@FindBy(linkText = "SEARCH")
	WebElement search;

	@FindBy(xpath = "//span[@class='checkbox-group  append_bottom5 fli-filter-items '][1]//span[@class='labeltext']")
	WebElement non_stop;

	@FindBy(linkText = "More Filters")
	WebElement more_filters;

	@FindBy(xpath = "//div[@role='slider'][1]")
	WebElement left_slider;

	@FindBy(xpath = "//div[@role='slider'][2]")
	WebElement right_slider;

	@FindBy(linkText = "Apply")
	WebElement apply_filters;

	@FindBy(xpath = "//div[@id ='sorting-togglers']/div[5]/span")
	WebElement priceTag;

	@FindBy(xpath = "//span[@class='actual-price']")
	List<WebElement> prices;

	@FindBy(xpath = "//span[@class='actual-price']")
	List<WebElement> sortedpricesindec;

	@FindBy(xpath = "//div[@class='fli-intl-lhs pull-left']//div[2]//button")
	WebElement bookNow;

	@FindBy(xpath = "//div[@class='fli-intl-container clearfix']//div[3]/div/div[2]//div[@class='dept-time']")
	WebElement dept_time;

	@FindBy(xpath = "//div[@class='fli-intl-container clearfix']//div[3]/div/div[2]//p[@class='reaching-time append_bottom3']")
	WebElement arrival_time;

	@FindBy(xpath = "//div[@class='dept-time append_bottom3 make_relative']")
	WebElement displayedDepartureTime;

	@FindBy(xpath = "//p[@class='reaching-time append_bottom3']")
	WebElement displayedArrivalTime;

	@FindBy(xpath = "//span[@class='check']")
	WebElement donateBox;

	@FindBy(xpath = "//button[@id='review-continue']")
	WebElement continueReviewButton;

	@FindBy(xpath = "//span[@class='numbering onpage']")
	WebElement reviewPageNumber;

	@FindBy(xpath = "//span[@class='numbering onpage']")
	WebElement travelDetailsPageNumber;

	// Deals Section

	@FindBy(linkText = "More")
	private WebElement more;

	@FindBy(linkText = "Deals")
	private WebElement deals;

	@FindBy(xpath = "//section//div[@class='inputM inputHlp inputFilter'][1]")
	private WebElement sourceSection;

	@FindBy(xpath = "//section//div[@class='inputM inputHlp inputFilter'][2]")
	private WebElement destinationSection;

	@FindBy(xpath = "//input[@id='hp-widget__sfrom']")
	private WebElement sourceDealField;

	@FindBy(xpath = "//ul[@id='ui-id-1']/li[2]")
	private WebElement selectSource;

	@FindBy(xpath = "//input[@id='hp-widget__sTo']")
	private WebElement destinationDealField;

	@FindBy(xpath = "//ul[@id='ui-id-2']/li[2]")
	private WebElement selectDestination;

	@FindBy(xpath = "//div[@class='close']")
	private WebElement ad;

	@FindBy(xpath = "//iframe[@data-notification-layout-name='banner']")
	private WebElement iframe;

	@FindBy(xpath = "//div[@class='dateFilter hasDatepicker']//a[@title='Next']")
	WebElement dealNextMonth;

	@FindBy(id = "searchBtn")
	WebElement searchButton;

	@FindBy(xpath = "//a[@class='close']")
	WebElement closeAd;

	@FindBy(xpath = "//iframe[@data-notification-layout-name='modal']")
	WebElement adFrame;

	JavascriptExecutor jse;

	private String arrivalTime;
	private String departureTime;
	private List<Integer> initialPrice;
	private List<Integer> finalPrice;
	static Logger logger = Logger.getLogger(FlightBooking.class.getName());
	WebDriver driver;
	Wait<WebDriver> wait;

	public FlightBooking(WebDriver driver) {
		this.driver = driver;
		jse = (JavascriptExecutor) driver;
		wait = Hold.wait(driver);
	}

	@Step("close the ad")
	public void closead() {
		driver.switchTo().frame(adFrame);

		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return closeAd;
			}
		});
		Action.clickOn(driver, closeAd);

		driver.switchTo().defaultContent();
	}

	@Step("set the source")
	public void sendDataSourceCity() {

		Hold.hold(driver);
		source.click();

		Hold.hold(driver);
		sourceField.sendKeys("Delhi");

		Hold.hold(driver);
		exactSource.click();
	}

	@Step("set the destination")
	public void sendDataDestinationCity() {
		Hold.hold(driver);
		Action.clickOn(driver, destination);
		Hold.hold(driver);
		destinationField.sendKeys("Ranchi");
		Hold.hold(driver);
		exactDestination.click();
	}

	@Step("select the date: {0}")
	public void selectJourneyDate(String dayDate) throws InterruptedException {
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		int bookingMonth = CalendarMonth.getMonthMap().get(dayDate.substring(4, 7));
		logger.info(dayDate.substring(4, 7));
		int loopCounter = (bookingMonth - currentMonth + 12) % 12 - 1;
		Action.clickOn(driver, calendar);
		Hold.hold(driver);

		for (int counter = 0; counter < loopCounter; counter++) {
			Action.clickOn(driver, nextMonth);
		}

		driver.findElement(By.xpath("//div[@aria-label='" + dayDate + "']")).click();

	}

	@Step("apply non-stop filter")
	public void ApplyStopFilter() {
		Action.clickOn(driver, non_stop);
	}

	@Step("click on more filters")
	public void clickMoreFilters() {
		Action.clickOn(driver, more_filters);
	}

	@Step("set the left slider of price")
	public void dragPriceLeftSlider() {
		Action.dragAndDropRight(driver, left_slider);
	}

	@Step("set the right slider of price")
	public void dragPriceRightSlider() {
		Action.dragAndDropLeft(driver, right_slider);
	}

	@Step("apply more filters")
	public void applyMoreFilters() {
		Action.clickOn(driver, apply_filters);
	}

	@Step("Store the price displayed before sorting")
	public void storeInitialPrice() throws FlightNotFoundException {

		jse.executeScript("window.scrollBy(0,1000)");
		Hold.hold(driver);

		try {
			if (prices.size() == 0)
				throw new FlightNotFoundException("No flights found");
		} catch (FlightNotFoundException ex) {
			logger.info(ex.getMessage());
		}

		initialPrice = new ArrayList();

		for (WebElement price : prices) {
			String rupees = price.getText().substring(2);
			rupees = rupees.replaceAll(",", "");
			initialPrice.add(Integer.parseInt(rupees));
			logger.info(rupees);
		}

	}

	@Step("sort price in descending order")
	public void sortByPriceDec() {

		jse.executeScript("window.scrollBy(0,-1000)");
		Action.clickOn(driver, priceTag);
		Hold.hold(driver);

		logger.info("Final");

		finalPrice = new ArrayList();

		for (WebElement price : sortedpricesindec) {
			String rupees = price.getText().substring(2);
			rupees = rupees.replaceAll(",", "");
			finalPrice.add(Integer.parseInt(rupees));
			logger.info(rupees);
		}
	}

	@Step("return the price list after sorting")
	public List<Integer> getFinalPriceInDec() {
		return finalPrice;
	}

	@Step("return the price list before sorting")
	public List<Integer> getInitalPriceInDec() {
		Collections.sort(initialPrice, Collections.reverseOrder());
		return initialPrice;
	}

	@Step("set journey timings")
	public void setJourneyTimings() {
		arrivalTime = arrival_time.getText();
		departureTime = dept_time.getText();
	}

	@Step("click book button on the flights page")
	public void clickBookNow() {
		Action.clickOn(driver, bookNow);
	}

	@Step("return dept time")
	public String getDepartureTime() {
		return departureTime;
	}

	@Step("return arrival time")
	public String getArrivalTime() {
		return arrivalTime;
	}

	@Step("return displayed dept time on review page")
	public String getDisplayedDepartureTime() {
		return displayedDepartureTime.getText();
	}

	@Step("return displayed arrival time on review page")
	public String getDisplayedArrivalTime() {
		return displayedArrivalTime.getText();
	}

	@Step("uncheck donation button")
	public void uncheckDonation() {
		jse.executeScript("window.scrollBy(0,1000)");
		logger.info("donate");
		Action.clickOn(driver, donateBox);
	}

	@Step("confirm the review")
	public void continueReview() {
		Hold.hold(driver);
		Action.clickOn(driver, continueReviewButton);
	}

	@Step("return the review page number")
	public String getReviewPageNumber() {
		return reviewPageNumber.getText();
	}

	@Step("return travel details page number")
	public String getTravelDetailsPageNumber() {
		Hold.hold(driver);
		return travelDetailsPageNumber.getText();
	}

	@Step("click on search button for flights")
	public void clickSearch() {
		Action.clickOn(driver, search);
	}

	// Deals Section

	@Step("navigate to deals page")
	public void navigateToDeals(WebDriver driver) {
		more.click();
		Hold.hold(driver);
		deals.click();
	}

	@Step("set source: {0} and destination: {1}")
	public void fillDealCities(String source, String destination) {

		Hold.hold(driver);
		driver.switchTo().frame(iframe);
		Hold.hold(driver);
		Action.clickOn(driver, ad);

		driver.switchTo().defaultContent();

		Action.clickOn(driver, sourceSection);

		Hold.hold(driver);
		sourceDealField.sendKeys(source);
		Hold.hold(driver);

		Action.clickOn(driver, selectSource);
		Hold.hold(driver);

		Action.clickOn(driver, destinationSection);
		Hold.hold(driver);
		destinationDealField.sendKeys(destination);
		Hold.hold(driver);
		Action.clickOn(driver, selectDestination);
	}

	@Step("set dayDate: {0}")
	public void selectDealJourneyDate(String dayDate) throws InterruptedException {
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		int bookingMonth = CalendarMonth.getMonthMap().get(dayDate.substring(4, 7));
		logger.info(dayDate.substring(4, 7));
		int loopCounter = (bookingMonth - currentMonth + 12) % 12 - 1;
		Hold.hold(driver);
		for (int counter = 0; counter < loopCounter; counter++) {
			Action.clickOn(driver, dealNextMonth);
		}
		int index = 1;
		if (bookingMonth == currentMonth)
			index = 0;
		driver.findElements(By.linkText(dayDate.substring(8, 10))).get(index).click();
	}

	@Step("search flights through deals")
	public void searchDealFlights() {
		Action.clickOn(driver, searchButton);
	}

	public void sendDataSourceCity(String source2) {
		Hold.hold(driver);
		source.click();

		Hold.hold(driver);
		sourceField.sendKeys(source2);

		Hold.hold(driver);
		exactSource.click();

	}

	public void sendDataDestinationCity(String destination2) {
		Hold.hold(driver);
		Action.clickOn(driver, destination);
		Hold.hold(driver);
		destinationField.sendKeys(destination2);
		Hold.hold(driver);
		exactDestination.click();
	}

}
