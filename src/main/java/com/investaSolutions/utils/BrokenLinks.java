package com.investaSolutions.utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrokenLinks {

	/*
	 * 200 – Valid Link/success 
	 * 301/302 - Page redirection temporary/permanent 
	 * 404 – Page not found 
	 * 400 – Bad request 
	 * 401 – Unauthorized 
	 * 500 – Internal Server Error
	 */
	
	public static void brokenImages(WebDriver driver) throws Exception
	{
		// Storing all elements with img tag in a list of WebElements
        List<WebElement> images = driver.findElements(By.tagName("img"));
        System.out.println("Total number of Images on the Page are " + images.size());
        
        
        //checking the links fetched.
        for(int index=0;index<images.size();index++)
        {
            WebElement image= images.get(index);
            String imageURL= image.getAttribute("src");
            System.out.println("URL of Image " + (index+1) + " is: " + imageURL);
            verifyLinkURL(imageURL);
          
            //Validate image display using JavaScript executor
            try {
                boolean imageDisplayed = (Boolean) ((JavascriptExecutor) driver).executeScript("return (typeof arguments[0].naturalWidth !=\"undefined\" && arguments[0].naturalWidth > 0);", image);
                if (imageDisplayed) {
                    System.out.println("DISPLAY - OK");
                }else {
                     System.out.println("DISPLAY - BROKEN");
                }
            } 
            catch (Exception e) {
            	System.out.println("Error Occured");
            }
        }
        
        
     driver.quit();	
	}
	public static void brokenLinks(WebDriver driver) throws Exception {
		try {

			List<WebElement> listOfElements = driver.findElements(By.tagName("a"));
			int lengthofElement = listOfElements.size();

			for (int i = 0; i < lengthofElement; i++) {
				WebElement ele = listOfElements.get(i);

				String attributeValue = ele.getAttribute("href");
				verifyLinkURL(attributeValue);

			}
			driver.quit();

		} catch (Exception e) {
			throw e;
		}
	}

	public static void verifyLinkURL(String urlLinks) throws Exception {
		try {

			URL url = new URL(urlLinks);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(3000);
			connection.connect();

			if (connection.getResponseCode() >= 400) {

				System.out.println(urlLinks + " - " + connection.getResponseMessage() + "is a broken link");
			}
			// Fetching and Printing the response code obtained
			else {
				System.out.println(urlLinks + " - " + connection.getResponseMessage());
			}

		} catch (Exception e) {
			throw e;
		}
	}

}
