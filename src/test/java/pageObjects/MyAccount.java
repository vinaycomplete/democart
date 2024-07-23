package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends BasePage {
	
	WebDriver driver;
	
	public MyAccount(WebDriver driver){

		super(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']") //MyAccount Page Heading
	WebElement msgHeading;
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(xpath="//a[@class='dropdown-item'][normalize-space()='Logout']")
	WebElement lnkLogout;
	
	public boolean isMyAccountPageExists() {
		try {
		return msgHeading.isDisplayed();
		} catch(Exception e) {
			
			return false;
		}
	}
	
	public void clickMyAccount() {
		lnkMyAccount.click();
	}
	
	
	public void clickLogout() {
		lnkLogout.click();
	}

}
