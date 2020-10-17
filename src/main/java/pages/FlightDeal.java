package pages;

import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import service.Action;
import service.CalendarMonth;
import service.Hold;

public class FlightDeal {

	WebDriver driver;
	@FindBy(linkText = "More")
	private WebElement more;

	@FindBy(linkText = "Deals")
	private WebElement deals;

	@FindBy(xpath = "//section//div[@class='inputM inputHlp inputFilter'][1]")
	private WebElement sourceSection;

	@FindBy(xpath = "//section//div[@class='inputM inputHlp inputFilter'][2]")
	private WebElement destinationSection;

	@FindBy(xpath = "//input[@id='hp-widget__sfrom']")
	private WebElement sourceField;

	@FindBy(xpath = "//ul[@id='ui-id-1']/li[2]")
	private WebElement selectSource;

	@FindBy(xpath = "//input[@id='hp-widget__sTo']")
	private WebElement destinationField;

	@FindBy(xpath = "//ul[@id='ui-id-2']/li[2]")
	private WebElement selectDestination;

	@FindBy(xpath = "//div[@class='close']")
	private WebElement ad;

	@FindBy(xpath = "//iframe[@data-notification-layout-name='banner']")
	private WebElement iframe;

	@FindBy(xpath = "//div[@class='dateFilter hasDatepicker']//a[@title='Next']")
	WebElement nextMonth;

	@FindBy(id = "searchBtn")
	WebElement searchButton;

	public FlightDeal(WebDriver driver) {
		this.driver = driver;
	}

	public void navigateToDeals(WebDriver driver) {
		more.click();
		Hold.hold(driver);
		deals.click();
	}

	public void fillCities(String source, String destination) {

		Hold.hold(driver);
		driver.switchTo().frame(iframe);
		Hold.hold(driver);
		Action.clickOn(driver, ad);

		driver.switchTo().defaultContent();

		Action.clickOn(driver, sourceSection);

		Hold.hold(driver);
		sourceField.sendKeys(source);
		Hold.hold(driver);

		Action.clickOn(driver, selectSource);
		Hold.hold(driver);

		Action.clickOn(driver, destinationSection);
		Hold.hold(driver);
		destinationField.sendKeys(destination);
		Hold.hold(driver);
		Action.clickOn(driver, selectDestination);
	}

	public void selectJourneyDate(String dayDate) throws InterruptedException {
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		int bookingMonth = CalendarMonth.getMonthMap().get(dayDate.substring(4, 7));
		int loopCounter = (bookingMonth - currentMonth + 12) % 12 - 1;
		Hold.hold(driver);
		for (int counter = 0; counter < loopCounter; counter++) {
			Action.clickOn(driver, nextMonth);
		}
		int index = 1;
		if (bookingMonth == currentMonth)
			index = 0;
		driver.findElements(By.linkText(dayDate.substring(8, 10))).get(index).click();
	}

	public void searchFlights() {
		Action.clickOn(driver, searchButton);
	}

}
