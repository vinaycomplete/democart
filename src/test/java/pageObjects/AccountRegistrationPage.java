package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	

@FindBy(xpath="//input[@id='input-firstname']")
WebElement txtFirstname;

@FindBy(xpath="//input[@id='input-lastname']")
WebElement txtLastname ;

@FindBy(xpath="//input[@id='input-email']")
WebElement txtEmail;

@FindBy(xpath="//input[@id='input-password']")
WebElement txtPassword;

@FindBy(xpath="//input[@name='agree']")
WebElement chkPolicy ;

//By chk_policy=By.xpath("//input[@name='agree']");


@FindBy(xpath="//button[normalize-space()='Continue']")
WebElement btnContinue;

@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgConfirmation;

public void setFirstName(String fname) {
	txtFirstname.sendKeys(fname);
}

public void setLastName(String lname) {
	txtLastname.sendKeys(lname);
}

public void setEmail(String email) {
	txtEmail.sendKeys(email);
}

public void setPassword(String pwd) {
	txtPassword.sendKeys(pwd);
}

public void setPrivacyPolicy() {
	/*WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(10));
	WebElement el=w.until(ExpectedConditions.visibilityOfElementLocated(chk_policy));
	el.click();*/
	JavascriptExecutor js=(JavascriptExecutor)driver;
	 js.executeScript("arguments[0].click();", chkPolicy);
}

public void clickContinue() {
	
	JavascriptExecutor js=(JavascriptExecutor)driver;
	 js.executeScript("arguments[0].click();", btnContinue);
	 
	//sol1
	//btnContinue.click();
	/*
	//sol2
	btnContinue.submit();
	
	//sol3
	 Actions act=new Actions(driver);
	 act.moveToElement(btnContinue).click().perform();
	 
	 //sol4
	 JavascriptExecutor js=(JavascriptExecutor)driver;
	 js.executeScript("arguments[0].click();", btnContinue);
	 
	 //sol5
	 btnContinue.sendKeys(Keys.RETURN);
	 
	 //sol6
	 WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
	 mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();	 
	*/
}

public String getConfirmationMsg() {
	try {
		return (msgConfirmation.getText());
	}
	catch(Exception e) {
		return (e.getMessage());
	}

	}	
}
