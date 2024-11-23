package com.investaSolutions.base;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.investaSolutions.utils.ExcelUtils;
import com.investaSolutions.utils.ExtentReportManager;
import com.investaSolutions.utils.GenericUtils;
import com.investaSolutions.utils.PropertiesManager;
import com.investaSolutions.utils.SeleniumUtils;
import com.investaSolutions.utils.SendEmailUtils;

public class TestBase {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Capabilities cap;
	public static Logger log;
	//private static String environment = System.getProperty("env", "UAT"); // Default to DEV

	// Declare PropertiesManager at the global level
	public static PropertiesManager properties;
	public static int WAIT_SECONDS;
	

	public static ExtentReportManager extentReportManager = new ExtentReportManager();
	GenericUtils genericUtil = new GenericUtils();

	// Instance variables for configuration properties
	private String hostName;
	private String portID;
	private String senderEmail;
	private String senderPassword;
	private String receiverEmail;
	private String ccEmail;
	private String[] receiverEmailArray;
	private String[] ccEmailArray;
	static String reportPath;
	protected Map<String, String> testData;
    public String baseUrl; // Stores the URL based on the environment
    private static String environment;

	// ThreadLocal for test data
	private static ThreadLocal<Map<String, String>> threadLocalTestData = new ThreadLocal<>();

	static {
		properties = PropertiesManager.getInstance();
		environment=properties.getEnv("env");
		WAIT_SECONDS = Integer.valueOf(properties.getConfig("GLOBAL_WAIT"));
		System.out.println(WAIT_SECONDS);
	}

	@BeforeSuite(alwaysRun = true)
	@Parameters("browser")
	public void beforeSuite(String browser) throws MalformedURLException {
		try {
			System.out.println(WAIT_SECONDS);
			log = LogManager.getLogger("Logger");
			cleanUpFolders();
			ExtentReportManager.createInstance("Test Report", "Test Execution Report");
			
            // baseUrl = properties.getPagesURL(environment.toUpperCase() + "_URL"); --> This will use in real time projects where have different environment
			// baseUrl=properties.getPagesURL("URL"); //actual URL Which is common throughout project
            
			log.info("Initializing WebDriver for browser: " + browser);
            // The base URL will be set in the individual test classes now
            driver = SingletonWebDriver.getDriver(browser);
            cap = ((RemoteWebDriver) driver).getCapabilities();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_SECONDS));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(WAIT_SECONDS));
            //driver.get(baseUrl); ==> This can be use where have common url in Automation
        } catch (Exception e) {
            log.error("Error initializing WebDriver: ", e);
        }
	}
	
	 // Method to set up the base URL
    protected void setupURL(String urlKey) {
        baseUrl = properties.getPagesURL(urlKey);
        if (baseUrl == null) {
            throw new RuntimeException("Base URL not defined for key: " + urlKey);
        }
        // Use SingletonWebDriver to navigate to the base URL after initialization
        driver.get(baseUrl); // This can also be done in the getDriver method
        log.info("Navigated to base URL: " + baseUrl);
    }

	@BeforeClass(alwaysRun = true)
	public void initProperties() {
		// Access properties after initialization
		hostName = properties.getConfig("HOST_NAME");
		portID = properties.getConfig("PORT_ID");
		senderEmail = properties.getConfig("SENDER_EMAIL_ID");
		senderPassword = properties.getConfig("SENDER_EMAIL_PASSWORD");
		receiverEmail = properties.getConfig("RECEIVER_EMAIL_ID");
		ccEmail = properties.getConfig("CC_EMAIL_ID");
		receiverEmailArray = receiverEmail.split(";");
		ccEmailArray = ccEmail.split(";");

		// Initialize reportPath after properties are available
		reportPath = System.getProperty("user.dir") + properties.getConfig("HTMLREPORT_PATH")
				+ properties.getConfig("HTMLREPORT_NAME");

		// Log the properties to ensure they are loaded
		log.info("Loaded HOST_NAME: " + hostName);
		log.info("Loaded PORT_ID: " + portID);
		log.info("Loaded SENDER_EMAIL_ID: " + senderEmail);
		log.info("Loaded SENDER_EMAIL_PASSWORD: " + senderPassword);
		log.info("Loaded RECEIVER_EMAIL_ID: " + receiverEmail);
		log.info("Loaded CC_EMAIL_ID: " + ccEmail);
		log.info("Loaded HTMLREPORT: " + reportPath);

	}

	@AfterMethod(alwaysRun = true)
	public synchronized void afterMethod(ITestResult result) {
		String testName = result.getName();

		if (result.getStatus() == ITestResult.FAILURE) {
			log.error("Test {} has failed.", testName);
			ExtentReportManager.logFailureDetails(testName + ": Test Case Failed");

			// Log the exception that caused the failure
			Throwable throwable = result.getThrowable();
			if (throwable != null) {
				ExtentReportManager.logExceptionDetails(testName + ": Exception occurred during test execution", throwable);
				log.error("Exception occurred during test execution: ", throwable); // log full stack trace
			}

			// Capture screenshot on failure
			try {
				String screenshotPath = SeleniumUtils.getScreenshot(driver, null, testName); // Pass null if no element
				ExtentReportManager.addScreenshotToReport(screenshotPath);
			} catch (Exception e) {
				log.error("Failed to capture screenshot for {}: {}", testName, e);
			}

		} else if (result.getStatus() == ITestResult.SKIP) {
			log.warn("Test {} was skipped.", testName);
			ExtentReportManager.logWarningDetails(testName + ": Test Case Skipped");
		} else {
			log.info("Test {} passed.", testName);
			ExtentReportManager.logSuccessDetails(testName + ": Test Case Passed");
		}
		// Clean up thread-local data after each test
		threadLocalTestData.remove();
	}

	@AfterSuite(alwaysRun = true)
	public void generateReport() {
		String pathofExtentReport;
	    if (cap != null) {
	        ExtentReportManager.setSystemInfo("Browser", cap.getBrowserName());
	        ExtentReportManager.setSystemInfo("Browser Version", cap.getBrowserVersion());
	    }

	    // Ensure reports are flushed and saved before sending email
	    try {
	        ExtentReportManager.flushReports();
	        System.out.println("Reports flushed successfully.");
	        pathofExtentReport=ExtentReportManager.reportPath;
	        log.info("Reports flushed successfully.");
	        log.info("Reports generated at this place : " + reportPath);
	        System.out.println("Reports generated at this place : " + reportPath);
	    } catch (Exception e) {
	        System.err.println("Error flushing reports: " + e.getMessage());
	        log.error(("Error flushing reports: " + e.getMessage()));
	        System.err.println("Reports generated failed at this place: " +  reportPath + e.getMessage());
	        log.error(("Reports generated failed at this place: " +  reportPath +  e.getMessage()));
	        return; // Exit the method if report flushing fails
	    }

	    // Quit the driver
	    try {
	        if (driver != null) {
	            driver.quit();
	            System.out.println("WebDriver quit successfully.");
	            log.info("WebDriver quit successfully.");
	        }
	    } catch (Exception e) {
	        System.err.println("Error quitting WebDriver: " + e.getMessage());
	        log.error(("Error quitting WebDriver: " + e.getMessage()));
	    }

	    // Send email with the report
	    try {
	        SendEmailUtils.sendEmailNow(hostName, portID, senderEmail, senderPassword, receiverEmailArray, ccEmailArray, pathofExtentReport);
	    } catch (Exception e) {
	        System.err.println("Error sending email: " + e.getMessage());
	        log.error(("Error sending email: " + e.getMessage()));
	    }
	}

	public static void cleanUpFolders() throws IOException {
		GenericUtils genericUtil = new GenericUtils();
		String failedScreenshotsPath = System.getProperty("user.dir")
				+ properties.getConfig("FAILEDTEST_SCREENSHOTS_PATH");
		String htmlReportPath = System.getProperty("user.dir") + properties.getConfig("HTMLREPORT_PATH");

		genericUtil.cleanUpFolder(failedScreenshotsPath);
		genericUtil.cleanUpFolder(htmlReportPath);
	}

	// New method to set up test data
	protected void setupTestData(String testCaseId) throws Throwable {
		threadLocalTestData.set(ExcelUtils.getTestCaseData(
				System.getProperty("user.dir") + properties.getConfig("TESTDATA_EXCELFILE_PATH"),
				properties.getConfig("ADMINPORTAL_DATA_SHEETNAME"), testCaseId));
	}

	// Method to retrieve the test data
	protected Map<String, String> getTestData() {
		return threadLocalTestData.get();
	}
}
	
	
	// We can use above testdata in our @Test like below
	/*
	@Test
	public void testMethod1() throws Throwable {
	    setupTestData("TC001"); // Setup test data for TC001
	    Map<String, String> testData = getTestData(); // Fetch the test data
	    
	    // Now you can access the test data
	    String usrname = testData.get("username");
	    String password = testData.get("password");
	    // Continue with the test logic
	}
	*/
