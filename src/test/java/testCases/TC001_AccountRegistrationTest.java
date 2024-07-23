package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	
	@Test(groups={"Regression","Master"})
	void verify_account_registration() {
		
		try {
		logger.info("******Starting TC001_AccountRegistrationTest ********");
		HomePage hm=new HomePage(driver);
		
		hm.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		
		hm.clickRegister();
		logger.info("Clicked on MyAccount Link");
		AccountRegistrationPage ar=new AccountRegistrationPage(driver);
		
		logger.info("Providing Customer Details...");
		ar.setFirstName(randomeString().toUpperCase());
		ar.setLastName(randomeString().toUpperCase());
		ar.setEmail(randomeString()+"@gmail.com"); //randomly generated the email
		ar.setPassword(randomeAlphaNumeric());
		
		ar.setPrivacyPolicy();
		ar.clickContinue();
		
		logger.info("Validating Expected Message...");
		String conmsg=ar.getConfirmationMsg();
		if(conmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test Failed...");
			logger.debug("Debug Logs...");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(conmsg, "Your Account Has Been Created!!");
		} catch(Exception e) {		
		
			Assert.fail();
		}
		logger.info("****End of TC001_AccountRegistrationTest ****** ");
		
		}
	
}
