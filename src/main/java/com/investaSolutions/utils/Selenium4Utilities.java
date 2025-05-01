package com.investaSolutions.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class Selenium4Utilities {

    // ========== Browser Launch ================
    public static WebDriver launchBrowser(String browserName) {
        WebDriver driver = null;
        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Browser not supported");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    // ========== Relative Locators ================
    public static WebElement getElementBelow(WebDriver driver, By baseElementLocator, By targetLocator) {
        WebElement baseElement = driver.findElement(baseElementLocator);
        return driver.findElement(with(targetLocator).below(baseElement));
    }

    public static WebElement getElementAbove(WebDriver driver, By baseElementLocator, By targetLocator) {
        WebElement baseElement = driver.findElement(baseElementLocator);
        return driver.findElement(with(targetLocator).above(baseElement));
    }

    public static WebElement getElementNear(WebDriver driver, By baseElementLocator, By targetLocator) {
        WebElement baseElement = driver.findElement(baseElementLocator);
        return driver.findElement(with(targetLocator).near(baseElement));
    }

    // ========== Open New Tab/Window ================
    public static void openNewTab(WebDriver driver, String url) {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(url);
    }

    public static void openNewWindow(WebDriver driver, String url) {
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(url);
    }

    // ========== Screenshot Utilities ================
    public static void takeElementScreenshot(WebDriver driver, By locator, String destinationPath) throws IOException {
        WebElement element = driver.findElement(locator);
        File srcFile = element.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(destinationPath));
    }

    public static void takeFullPageScreenshot(WebDriver driver, String destinationPath) throws IOException {
        File srcFile = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(destinationPath));
    }

    // ========== DevTools ================
    public static DevTools createDevToolsSession(ChromeDriver driver) {
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        return devTools;
    }

    // ========== Window Handles ================
    public static String getCurrentWindowHandle(WebDriver driver) {
        return driver.getWindowHandle();
    }

    public static void switchToWindow(WebDriver driver, String windowHandle) {
        driver.switchTo().window(windowHandle);
    }
}
