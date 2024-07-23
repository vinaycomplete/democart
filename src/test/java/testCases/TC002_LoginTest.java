package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	
	@Test(groups={"Sanity","Master"})
	public void verify_login() {
		
		
		logger.info("**** Starting TC_002_LoginTest ****");
		
		try {
		//Home Page
		HomePage hp=new HomePage(driver);	
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount..");
		hp.clickLogin();
		logger.info("Clicked on Login...");
		
		//Login Page
		LoginPage lp=new LoginPage(driver);
		logger.info("Providing Login Details..");
		lp.setEmail(p.getProperty("email"));
		
		lp.setPwd(p.getProperty("password"));
		lp.clickLogin();
		logger.info("Clicked on Login Button..");
		
		//MyAccount
		MyAccount mact=new MyAccount(driver);
		boolean targetPage=mact.isMyAccountPageExists();
		logger.info("Verifying the MyAccount Page..");
		Assert.assertTrue(targetPage);
		}
		
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("********Finished TC002_LoginTest *******");
		
	}
}
