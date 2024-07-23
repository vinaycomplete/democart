package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;
import java.util.Date;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j

public class BaseClass {

	static public WebDriver driver;
	public Logger logger; //log4j
	public Properties p;
	//String url="http://localhost/opencart/upload/index.php";
	
	
	@BeforeClass(groups= {"Sanity","Regression","Master","DataDriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		
		//Loading config.properties files
		
		FileReader file=new FileReader(".//src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		logger = LogManager.getLogger(this.getClass());
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities =new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No Matching OS");
				return;
			}
			
			//browser
			switch(br.toLowerCase()) {
			case "chrome" : 
					WebDriverManager.chromedriver().setup();
					capabilities.setBrowserName("chrome");break;
			case "edge" : 
				WebDriverManager.edgedriver().setup();
				capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox" : 
				WebDriverManager.firefoxdriver().setup();
				capabilities.setBrowserName("firefox");break;
				
			default : System.out.println("No Matching Browser");return;
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
			
		}
		
		else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
		
		switch(br.toLowerCase()) {
		case "chrome": 
				WebDriverManager.chromedriver().setup();
				driver= new ChromeDriver();
				break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		default :System.out.println("Invalid browser name"); return;
		}
		
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURl1"));
		driver.manage().window().maximize();
	}
	
	
	@AfterClass(groups= {"Sanity","Regression","Master","DataDriven"})
	public void tearDown() {
	
		driver.quit();
	}
	

	public  String randomeString() {
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public  String randomeNumber() {
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public  String randomeAlphaNumeric() {
		String generatedAlphaNumber=RandomStringUtils.randomAlphanumeric(6);
		return generatedAlphaNumber;
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
	


}
