package pages;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;
/**
 *
 *         Website.
 */
public class HomePage {
	WebDriver driver;
	@FindBy(xpath=".//div[text()='Active']//parent::div//div")
	WebElement sortIcon;

	@FindBys(@FindBy(xpath = ".//div[@class='state-name fadeInUp']"))
	List<WebElement> statesList;

	@FindBys(@FindBy(xpath = ".//div[@class='delta is-confirmed']//following-sibling::div[@class='total']"))
	List<WebElement> confirmedList;

	@FindBys(@FindBy(xpath = "//*[contains(@class,'statistic')][2]"))
	List<WebElement> activeList;

	@FindBys(@FindBy(xpath = ".//div[@class='cell statistic']//div[@class='total']"))
	List<WebElement> info;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public List<String> getTop3ActiveStates(){
		Utils.waitForMintime();
		List<String> topStates=new ArrayList<>();
		for(int index=0;index<3;index++) {
			topStates.add(statesList.get(index).getText());
		}
		return topStates;
	}

	public List<WebElement> getActiveList() {
		return activeList;
	}

	public List<WebElement> getConfirmedList() {
		return confirmedList;
	}

}
