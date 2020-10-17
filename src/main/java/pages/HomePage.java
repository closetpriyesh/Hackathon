package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import service.Action;
import service.Hold;

public class HomePage {
	private WebDriver driver;

	@FindBy(linkText = "Flights")
	private WebElement flights;
	@FindBy(linkText = "Hotels")
	private WebElement hotels;
	@FindBy(linkText = "Villas & Apts")
	private WebElement villas;
	@FindBy(linkText = "Holidays")
	private WebElement holidays;
	@FindBy(linkText = "Trains")
	private WebElement trains;
	@FindBy(linkText = "Buses")
	private WebElement buses;
	@FindBy(linkText = "Cabs")
	private WebElement cabs;
	@FindBy(linkText = "Visa")
	private WebElement visa;
	@FindBy(linkText = "Giftcards")
	private WebElement giftCards;
	@FindBy(linkText = "More")
	private WebElement more;
	@FindBys({ @FindBy(xpath = "//div[@class='makeFlex appendBottom40 footerLinks']//a") })
	private List<WebElement> footerLinks;

	@FindBy(xpath = "//li[@data-cy='account']")
	private WebElement accountTab;

	@FindBy(xpath = "//a[@class='close']")
	WebElement closeAd;

	@FindBy(xpath = "//iframe[@data-notification-layout-name='modal']")
	WebElement adFrame;

	@FindBy(xpath = "//input[@data-cy='userName']")
	private WebElement inputEmailOrPhone;

	@FindBy(xpath = "//input[@data-cy='password']")
	private WebElement inputPassword;

	@FindBy(xpath = "//button[@data-cy='continueBtn']")
	private WebElement continueButton;

	@FindBy(xpath = "//button[@data-cy='login']")
	private WebElement loginButton;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickFlights() {
		flights.click();
	}

	public void clickHotels() {
		hotels.click();
	}

	public void clickVillas() {
		villas.click();
	}

	public void clickHolidays() {
		holidays.click();
	}

	public void clickTrains() {
		trains.click();
	}

	public void clickBuses() {
		buses.click();
	}

	public void clickCabs() {
		cabs.click();
	}

	public void clickVisa() {
		visa.click();
	}

	public void clickGiftCards() {
		giftCards.click();
	}

	public void clickMore() {
		more.click();
		Hold.hold(driver);
		clickDeals();
	}

	@FindBy(linkText = "Deals")
	private WebElement deals;

	public void clickDeals() {
		deals.click();
	}

	public List<String> getUrls() {

		List<String> urls = new ArrayList();
		for (WebElement footerLink : footerLinks)
			urls.add(footerLink.getAttribute("href"));

		return urls;

	}

	public void createAccount() {
		Action.clickOn(driver, accountTab);
		Hold.hold(driver);

		inputEmailOrPhone.sendKeys("yohibo4382@itymail.com");
		Hold.hold(driver);
		closead();
		Hold.hold(driver);
		continueButton.submit();
		Hold.hold(driver);
		inputPassword.sendKeys("selenium@123");
		Hold.hold(driver);
		loginButton.submit();

	}

	public void closead() {
		driver.switchTo().frame(adFrame);
		Action.clickOn(driver, closeAd);
		Hold.hold(driver);
		driver.switchTo().defaultContent();
	}

}
