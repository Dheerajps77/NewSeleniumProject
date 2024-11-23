package com.investaSolutions.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShadowDOMUtils {

	private WebDriver driver;

	public ShadowDOMUtils(WebDriver driver) {
		this.driver = driver;
	}
	
	/*
	<div id="outer-host">
	  #shadow-root (open)
	  <div id="inner-host">
	    #shadow-root (open)
	    <button id="submit-button">Submit</button>
	  </div>
	</div>
	
	*/

	
	/* shadowUtils.clickShadowElement("#outer-host", "#inner-host", "#submit-button");
	 */
	// Returns the shadow root of a given shadow host element
	private WebElement getShadowRoot(WebElement shadowHost) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost);
	}

	// Get a nested shadow DOM element by providing a sequence of CSS selectors
	public WebElement getNestedShadowDomElement(String... selectors) {
		WebElement currentElement = driver.findElement(By.cssSelector(selectors[0]));

		for (int i = 1; i < selectors.length; i++) {
			currentElement = getShadowRoot(currentElement).findElement(By.cssSelector(selectors[i]));
		}

		return currentElement;
	}

	// Click a shadow DOM element
	public void clickShadowElement(String... selectors) {
		WebElement element = getNestedShadowDomElement(selectors);
		element.click();
	}

	// Send text to a shadow DOM element
	public void sendKeysToShadowElement(String text, String... selectors) {
		WebElement element = getNestedShadowDomElement(selectors);
		element.sendKeys(text);
	}

	// Get text from a shadow DOM element
	public String getTextFromShadowElement(String... selectors) {
		WebElement element = getNestedShadowDomElement(selectors);
		return element.getText();
	}

	// Get the value attribute from a shadow DOM element
	public String getValueFromShadowElement(String... selectors) {
		WebElement element = getNestedShadowDomElement(selectors);
		return element.getAttribute("value");
	}
}