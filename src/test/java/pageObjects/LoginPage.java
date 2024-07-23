package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public WebDriver driver;
	public LoginPage(WebDriver driver){
		super(driver);
	}
	
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtemail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpwd;
	
	
	@FindBy(xpath="//button[normalize-space()='Login']")
	WebElement btnLogin;
	
	public void setEmail(String email) {
		txtemail.sendKeys(email);
	}
	
	public void setPwd(String pwd) {
		txtpwd.sendKeys(pwd);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}


}
