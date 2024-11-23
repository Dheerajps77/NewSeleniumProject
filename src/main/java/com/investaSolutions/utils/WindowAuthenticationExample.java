package com.investaSolutions.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WindowAuthenticationExample {

	public static void main(String[] args) {
		// Handle window authentication for Chrome
		WebDriver chromeDriver = getChromeDriver();
		handleWindowAuthentication(chromeDriver, "admin", "admin", "https://yourwebsite.com");

		// Handle window authentication for Firefox
		WebDriver firefoxDriver = getFirefoxDriver();
		handleWindowAuthentication(firefoxDriver, "admin", "admin", "the-internet.herokuapp.com/basic_auth");

		// Handle window authentication for Edge
		WebDriver edgeDriver = getEdgeDriver();
		handleWindowAuthentication(edgeDriver, "admin", "admin", "the-internet.herokuapp.com/basic_auth");
	}

	// Method to instantiate Chrome WebDriver
	public static WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
		return new ChromeDriver();
	}

	// Method to instantiate Firefox WebDriver
	public static WebDriver getFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver");
		return new FirefoxDriver();
	}

	// Method to instantiate Edge WebDriver
	public static WebDriver getEdgeDriver() {
		System.setProperty("webdriver.edge.driver", "/path/to/msedgedriver");
		return new EdgeDriver();
	}

	// http://the-internet.herokuapp.com/basic_auth
	
	// Method to handle window authentication
	public static void handleWindowAuthentication(WebDriver driver, String username, String password, String url) {
		String urlWithAuth = "http://" + username + ":" + password + "@" + url;
		driver.get(urlWithAuth);
		// Perform Selenium actions here
		// Example: driver.findElement(By.id("elementId")).click();
		driver.quit();
	}
}
