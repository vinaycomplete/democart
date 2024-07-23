package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")
	public void verify_loginDDT(String email,String pwd,String exp) {
		
		
		System.out.println("Email:"+email+"Password:"+pwd+"Expected:"+exp);


		logger.info("**** Starting TC_003_LoginDDT ****");
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
		lp.setEmail(email);
		
		lp.setPwd(pwd);
		lp.clickLogin();
		logger.info("Clicked on Login Button..");
		
		//MyAccount
		MyAccount mact=new MyAccount(driver);
		boolean targetPage=mact.isMyAccountPageExists();
		logger.info("Verifying the MyAccount Page..");
		
		/*
		 Data is valid - login success - test pass -logout
		 				 login failed - test fail
		 				 
		 Data is invalid - login success -test fail - logout
		 					login failed - test pass
		 				 
		 */
		
		if(exp.equalsIgnoreCase("Valid")) {
			if(targetPage==true) {
				Assert.assertTrue(true);
				mact.clickMyAccount();
				mact.clickLogout();
				logger.info("********Clicked on Logout *******");
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		else if(exp.equalsIgnoreCase("Invalid")) {
			if(targetPage==true) {
				mact.clickMyAccount();
				mact.clickLogout();
				logger.info("********Clicked on Logout *******");
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);	
			}
				
		}
		
		}catch(Exception e) {
			
			Assert.fail();
		}
		
		logger.info("********Finished TC003_LoginDDT *******");
		
		
	}

}
