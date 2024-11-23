package com.investaSolutions.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptUtils {

	public static void clickElementByJS(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public static void setValueByJS(WebDriver driver, WebElement element, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + value + "';", element);
	}

	public static void scrollIntoView(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void scrollByPixels(WebDriver driver, int pixels) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + pixels + ");");
	}

	public static void refreshPageByJS(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0)");
	}

	public static void triggerAlert(WebDriver driver, String message) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('" + message + "');");
	}

	// Scroll to the bottom of the page
	public static void scrollToBottom(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	public static String getTextByJS(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript("return arguments[0].innerText;", element);
	}

	public static void highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public static String getPageTitleByJS(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript("return document.title;");
	}

	// Simulating Mouse Hover
	public static void mouseHoverByJS(WebDriver driver, WebElement element) {
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} "
				+ "else if(document.createEventObject){ arguments[0].fireEvent('onmouseover');}";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(mouseOverScript, element);
	}

	// Simulating Right Click (Context Menu)
	public static void rightClickByJS(WebDriver driver, WebElement element) {
		String script = "var event = new MouseEvent('contextmenu', { 'bubbles': true, 'cancelable': true });"
				+ "arguments[0].dispatchEvent(event);";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script, element);
	}

	public static void disableAlerts(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
				"window.alert = function() {}; window.confirm = function() {}; window.prompt = function() {};");
	}

	// Zoom in (e.g., 150%)
	public static void zoomIn(WebDriver driver, double percentage) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='" + percentage + "%'");
	}

	// Reset Zoom
	public static void resetZoom(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='100%'");
	}

	public static void makeElementEditableByJS(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute('readonly');", element);
	}

	public static boolean checkIfImageIsBroken(WebDriver driver, WebElement imageElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Boolean imageStatus = (Boolean) js.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0;",
				imageElement);
		return !imageStatus;
	}
	
	public static String getPageSourceByJS(WebDriver driver) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    return (String) js.executeScript("return document.documentElement.outerHTML;");
	}


}
