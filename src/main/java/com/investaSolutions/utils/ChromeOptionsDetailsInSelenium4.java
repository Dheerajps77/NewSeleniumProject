package com.investaSolutions.utils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeOptionsDetailsInSelenium4 {

	public static void main(String[] args) {
        // Set up WebDriverManager to manage ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Create ChromeOptions instance
        ChromeOptions options = new ChromeOptions();

        // Set binary
        options.setBinary("/path/to/other/chrome");

        // Add arguments
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--user-data-dir=/path/to/custom/profile");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-software-rasterizer");
        options.addArguments("--disable-logging");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--lang=en");

        // Set preferences
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", "/path/to/download");
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.managed_default_content_settings.images", 2); // Disable images
        prefs.put("profile.default_content_setting_values.notifications", 2); // Disable notifications
        prefs.put("intl.accept_languages", "en-US,en");
        options.setExperimentalOption("prefs", prefs);

        // Add extensions
        options.addExtensions(new File("/path/to/extension.crx"));
        options.addExtensions(new File("/path/to/another_extension.crx"));

        // Set experimental options
        Map<String, Object> experimentalOptions = new HashMap<>();
        experimentalOptions.put("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("prefs", prefs);

        // Mobile emulation
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone X");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);

     // Performance logging
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);

        // Proxy settings
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("myhttpproxy:3337");
        options.setCapability(CapabilityType.PROXY, proxy);

        // Create WebDriver instance with ChromeOptions
        WebDriver driver = new ChromeDriver(options);

        // Navigate to a website
        driver.get("https://www.selenium.dev/");

        // Example: Taking a screenshot of a specific element
        try {
            WebElement logo = driver.findElement(By.xpath("//*[@id='td-cover-block-0']/div/div/div/div/h1"));
            File source = ((TakesScreenshot) logo).getScreenshotAs(OutputType.FILE);
            File screenshotsDir = new File("./Screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdir();
            }
            File destination = new File(screenshotsDir, "logo" + System.currentTimeMillis() + ".png");
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot saved at: " + destination.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Error taking screenshot: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("The Screenshot is taken and saved under Screenshots folder");

        // Close the browser
        driver.quit();
    }
}
