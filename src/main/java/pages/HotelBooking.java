package pages;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import service.Action;
import service.CalendarMonth;
import service.Hold;

public class HotelBooking {
	private WebDriver driver;
	private int checkInMonth;

	@FindBy(linkText = "Hotels")
	private WebElement hotels;

	@FindBy(xpath = "//input[@id ='city']")
	WebElement destination;

	@FindBy(xpath = "//input[@placeholder='Enter city/ Hotel/ Area/ Building']")
	WebElement destinationField;

	@FindBy(xpath = "//li[@data-suggestion-index='0']")
	WebElement exactDestination;

	@FindBy(xpath = "//span[@aria-label='Next Month']")
	WebElement nextMonth;

	@FindBy(xpath = "//label[@for='checkin']")
	WebElement checkInCalendar;

	@FindBy(xpath = "//label[@for='checkout']")
	WebElement checkOutCalendar;

	@FindBy(xpath = "//button[@id='hsw_search_button']")
	WebElement search;

	@FindBy(xpath = "//span[@class='mmClose']")
	WebElement locSuggestionCloseButton;

	@FindBy(xpath = "//input[@placeholder = 'Search by hotel name']")
	WebElement hotelSearch;

	@FindBy(xpath = "//div[@class='filterRow'][2]//li[2]//label")
	WebElement popularFilters;

	@FindBy(xpath = "//div[@class='filterRow'][4]//li[1]//label")
	WebElement locality;

	@FindBy(xpath = "//div[@class='filterRow'][5]//li[3]//label")
	WebElement starCategory;

	@FindBy(xpath = "//div[@class='filterRow'][7]//span[@id='hlistpg_proptypes_show_more']")
	WebElement showMoreProperty;

	@FindBy(xpath = "//div[@class='filterRow'][7]//li[4]//label")
	WebElement guestHouse;

	@FindBy(xpath = "//div[@class='appliedFiltersContainer']/div//a[1]")
	WebElement clearFilters;

	@FindBy(xpath = "//div[@class='customSelect']")
	WebElement features;

	@FindBy(xpath = "//div[@class='customSelect']//li[2]")
	WebElement priceLowToHigh;

	static Logger logger = Logger.getLogger(HotelBooking.class.getName());

	public HotelBooking(WebDriver driver) {
		this.driver = driver;
	}

	public void clickHotels() {
		hotels.click();
	}

	public void fillDestinationCity() {
		destination.click();
		Hold.hold(driver);
		destinationField.sendKeys("Goa");
		Hold.hold(driver);
		exactDestination.click();
	}

	public void selectCheckInDate(String dayDate) throws InterruptedException {
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		int bookingMonth = CalendarMonth.getMonthMap().get(dayDate.substring(4, 7));
		checkInMonth = bookingMonth;
		logger.info(dayDate.substring(4, 7));
		int loopCounter = (bookingMonth - currentMonth + 12) % 12 - 1;
		Action.clickOn(driver, checkInCalendar);
		Hold.hold(driver);

		for (int counter = 0; counter < loopCounter; counter++) {
			Action.clickOn(driver, nextMonth);
		}

		driver.findElement(By.xpath("//div[@aria-label='" + dayDate + "']")).click();

	}

	public void selectCheckOutDate(String dayDate) throws InterruptedException {
		int currentMonth = checkInMonth;
		int bookingMonth = CalendarMonth.getMonthMap().get(dayDate.substring(4, 7));
		logger.info(dayDate.substring(4, 7));
		int loopCounter = (bookingMonth - currentMonth + 12) % 12 - 1;
		Action.clickOn(driver, checkOutCalendar);
		Hold.hold(driver);

		for (int counter = 0; counter < loopCounter; counter++) {
			Action.clickOn(driver, nextMonth);
		}

		driver.findElement(By.xpath("//div[@aria-label='" + dayDate + "']")).click();

	}

	public void clickSearch() {
		Action.clickOn(driver, search);
	}

	public void closeLocationSuggestion() {
		try {
			Action.clickOn(driver, locSuggestionCloseButton);
			Action.clickWithRespectToWebElement(driver, hotelSearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void applyFilters() {

		Hold.hold(driver);
		Action.clickOn(driver, popularFilters);
		Hold.hold(driver);
		Action.clickOn(driver, locality);
		Hold.hold(driver);
		Action.clickOn(driver, starCategory);
		Hold.hold(driver);
		Action.clickOn(driver, showMoreProperty);
		Hold.hold(driver);
		Action.clickOn(driver, guestHouse);
	}

	public void sortByPriceLowToHigh() {
		Action.clickOn(driver, features);
		Action.clickOn(driver, priceLowToHigh);
	}

	public void clearAllFilters() {
		Hold.hold(driver);
		Action.clickOn(driver, clearFilters);
	}

}
