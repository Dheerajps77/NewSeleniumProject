package com.investaSolutions.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class SSLCertificateHandling {
	
	/*
	 * In Selenium, SSL (Secure Sockets Layer) occurs implicitly during interactions
	 * with secure websites that use HTTPS protocol. When Selenium WebDriver
	 * navigates to a secure website (URL starting with "https://"), it establishes
	 * a secure connection with the server using SSL/TLS encryption.
	 */
	public static void main(String[] args) {
        WebDriver chromeDriver = getChromeDriver();
        handleSSLCertificate(chromeDriver, "https://example.com");

        WebDriver firefoxDriver = getFirefoxDriver();
        handleSSLCertificate(firefoxDriver, "https://example.com");

        WebDriver edgeDriver = getEdgeDriver();
        handleSSLCertificate(edgeDriver, "https://example.com");
    }

    private static WebDriver getChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.setCapability("acceptSslCerts", true);
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("user-agent=Custom User Agent");
        return new ChromeDriver(chromeOptions);
    }

    private static WebDriver getFirefoxDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setAcceptInsecureCerts(true);
        return new FirefoxDriver(firefoxOptions);
    }

    private static WebDriver getEdgeDriver() {
    	EdgeOptions edgeOptions = new EdgeOptions();
        // No specific method for accepting insecure certs in EdgeOptions
        return new EdgeDriver(edgeOptions);
    }

    private static void handleSSLCertificate(WebDriver driver, String url) {
        driver.get(url);

        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            // No alert present
        }
        // Your Selenium code here

        driver.quit();
    }
}
