package com.investaSolutions.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ZoomInAndOutHandling {
	
	public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        
        // Zoom in by 50%
        zoomInUsingJavascriptExecutor(driver, 1.5); // Increase the zoom factor as needed
        
        // Zoom out by 50%
        zoomOutUsingJavascriptExecutor(driver, 0.5); // Decrease the zoom factor as needed
    }

    // Method to zoom in
    public static void zoomInUsingJavascriptExecutor(WebDriver driver, double zoomFactor) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='" + (zoomFactor * 100) + "%'");
    }

    // Method to zoom out
    public static void zoomOutUsingJavascriptExecutor(WebDriver driver, double zoomFactor) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='" + (zoomFactor * 100) + "%'");
    }
    
 // Method to zoom in
    public static void zoomIn(WebDriver driver, int numberOfTimes) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.ADD).keyUp(Keys.CONTROL).perform();
        }
    }

    // Method to zoom out
    public static void zoomOut(WebDriver driver, int numberOfTimes) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.SUBTRACT).keyUp(Keys.CONTROL).perform();
        }
    }
    
    public static void anotherWayToHandleStaleElementReferenceException(WebDriver driver)
    {
    	WebElement element = driver.findElement(By.id("dynamicElement"));
    	try {
    	    element.click();
    	} catch (StaleElementReferenceException e) {
    	    element = driver.findElement(By.id("dynamicElement"));
    	    element.click();
    	}
    }

}
