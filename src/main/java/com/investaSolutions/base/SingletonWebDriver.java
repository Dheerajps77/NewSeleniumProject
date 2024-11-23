package com.investaSolutions.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SingletonWebDriver {

	private static WebDriver driver;
	private static String browser;
	private static ChromeOptions chromeOptions;
	private static FirefoxOptions firefoxOptions;
	private static EdgeOptions edgeOptions;
	private static InternetExplorerOptions internetExplorerOptions;

	// Private constructor to prevent instantiation
	private SingletonWebDriver() {
	}

	// Method to get the single instance of WebDriver
	public static WebDriver getDriver(String browserName) {
		if (driver == null || !browserName.equalsIgnoreCase(browser)) {
			browser = browserName;
            initializeDriver(browserName);
		}
		return driver;
	}

	// Driver initialization logic
	private static void initializeDriver(String browserName) {
		switch (browserName.toLowerCase()) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			firefoxOptions = new FirefoxOptions();
			firefoxOptions.setAcceptInsecureCerts(true); // Accept insecure SSL certs
			firefoxOptions.addArguments("--private"); // Incognito mode (Firefox calls it private mode)
			firefoxOptions.addPreference("dom.webnotifications.enabled", false); // Disable notifications
			firefoxOptions.addPreference("general.useragent.override", "Custom User Agent"); // Custom user agent
			driver = new FirefoxDriver(firefoxOptions);
			break;

		case "chrome":
			// Use WebDriverManager to manage the ChromeDriver and set the correct version
			WebDriverManager.chromedriver().clearDriverCache();
			WebDriverManager.chromedriver().setup();
			chromeOptions = new ChromeOptions();
			// Adding Chrome specific options
			chromeOptions.setAcceptInsecureCerts(true);
			// Removed the deprecated 'acceptSslCerts' capability
			chromeOptions.addArguments("--disable-extensions");
			chromeOptions.addArguments("window-size=1200x600");
			chromeOptions.addArguments("--incognito");
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("user-agent=Custom User Agent");
			chromeOptions.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(chromeOptions);
			break;

		case "edge":
			// Use WebDriverManager to manage the EdgeDriver
			WebDriverManager.edgedriver().setup();
			edgeOptions = new EdgeOptions();
			// Adding Edge specific options
			edgeOptions.setAcceptInsecureCerts(true); // Handle SSL certificates
			// Removed the deprecated 'acceptSslCerts' capability
			edgeOptions.addArguments("--inprivate"); // Incognito mode for Edge
			edgeOptions.addArguments("--disable-extensions"); // Disable extensions in Edge
			edgeOptions.addArguments("--disable-notifications"); // Disable notifications
			edgeOptions.addArguments("user-agent=Custom User Agent"); // Custom user agent
			edgeOptions.addArguments("--remote-allow-origins=*");
			driver = new EdgeDriver(edgeOptions);
			break;

		case "ie":
            WebDriverManager.iedriver().setup();
            internetExplorerOptions = new InternetExplorerOptions();
            internetExplorerOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            internetExplorerOptions.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
            internetExplorerOptions.setCapability("acceptSslCerts", true); // Handling SSL certs
            driver = new InternetExplorerDriver(internetExplorerOptions);
            break;
            
		default:
			throw new IllegalArgumentException("Browser not supported: " + browserName);
		}
	}

	// Close the browser and nullify the instance
	public static void closeDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}