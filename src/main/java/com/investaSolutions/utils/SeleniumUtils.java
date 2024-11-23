package com.investaSolutions.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.investaSolutions.base.TestBase;

public class SeleniumUtils {

	JavascriptUtils javascriptUtils = new JavascriptUtils();

	public static PropertiesManager properties = PropertiesManager.getInstance();

	public static void turnOffImplicitWaits(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public static void turnOnImplicitWaits(WebDriver driver) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}

	public static WebElement waitForElementVisibility(WebDriver driver, WebElement element, int waitInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitInSeconds));
		return (WebElement) wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public static WebElement waitForElementPresence(WebDriver driver, By findByCondition, int waitInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitInSeconds));
		return wait.until(ExpectedConditions.presenceOfElementLocated(findByCondition));
	}

	public static WebElement waitForElementClickable(WebDriver driver, By findByCondition, int waitInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitInSeconds));
		return wait.until(ExpectedConditions.elementToBeClickable(findByCondition));
	}

	public static int countWindow(WebDriver driver) {
		return driver.getWindowHandles().size();
	}
	
	 public static WebElement waitForElementWithFluentWait(WebDriver driver, By locator, int timeoutInSeconds, int pollingInSeconds) {
	        Wait<WebDriver> wait = new FluentWait<>(driver)
	            .withTimeout(Duration.ofSeconds(timeoutInSeconds))   // Maximum time to wait
	            .pollingEvery(Duration.ofSeconds(pollingInSeconds))  // How frequently to poll for the condition
	            .ignoring(NoSuchElementException.class)              // Ignore NoSuchElementException
	            .ignoring(TimeoutException.class);                   // Optionally ignore TimeoutException

	        return wait.until(driverInstance -> driverInstance.findElement(locator));
	    }

	public String getTextOfElement(WebDriver driver, WebElement element) throws Exception {
		String titleText = "";
		try {
			titleText = element.getText();
		} catch (Exception e) {
			throw e;
		}
		return titleText;
	}

	public static String getScreenshot(WebDriver driver, WebElement element, String screenshotName) throws Exception {
	    try {
	        // Check if element is not null, then try to highlight it
	        if (element != null) {
	            try {
	                JavascriptUtils.highlightElement(driver, element);
	            } catch (Exception e) {
	                e.printStackTrace(); // Log the exception if highlighting fails
	            }
	        }

	        // Take screenshot
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);

	        // Create directory for screenshots if it doesn't exist
	        File theDir = new File("." + File.separator + "Execution Reports" + File.separator + "FailedTestsScreenshots");
	        if (!theDir.exists()) {
	            theDir.mkdirs(); // Creates directory and any missing parent directories
	        }

	        // Create a unique screenshot name with timestamp
	        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        String destination = System.getProperty("user.dir") 
	                             + properties.getConfig("FAILEDTEST_SCREENSHOTS_PATH")
	                             + File.separator + screenshotName + "_" + timestamp + ".png";

	        File finalDestination = new File(destination);
	        FileUtils.copyFile(source, finalDestination);

	        return destination;
	    } catch (IOException e) {
	        TestBase.log.error("Error occurred while capturing screenshot: " + screenshotName, e);
	        throw new Exception("Screenshot capture failed for " + screenshotName, e);
	    }
	}


	public String getAttributeValue(WebDriver driver, WebElement element, String attributeValue) {
		String textOfAttributeValue = "";
		try {
			textOfAttributeValue = element.getAttribute(attributeValue);
		} catch (Exception e) {
			throw e;
		}
		return textOfAttributeValue;
	}

	public static void switchWindowByIndex(WebDriver driver, int windowIndex) {
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<>(windowHandles);
		if (windowIndex < windowHandlesList.size()) {
			driver.switchTo().window(windowHandlesList.get(windowIndex));
		} else {
			throw new IndexOutOfBoundsException("Window index out of bounds");
		}
	}

	public static void closeAllRightTabs(WebDriver driver) {
		String originalWindow = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalWindow)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}
		driver.switchTo().window(originalWindow);
	}

	public static String getCurrentPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public static String fetchUserDetails(String key) {
		Properties property = new Properties();
		try (FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + properties.getConfig("CONFIG_PROPERTIES_PATH"))) {
			property.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return property.getProperty(key);
	}

	public static long getLoadTimeInSeconds(WebDriver driver, By waitForLoadElement, int waitInSeconds) {
		StopWatch pageLoad = new StopWatch();
		pageLoad.start();
		waitForElementVisibility(driver, (WebElement) waitForLoadElement, waitInSeconds);
		pageLoad.stop();
		return pageLoad.getTime() / 1000;
	}

	public static String toGetTextValue(WebDriver driver, WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return (String) js.executeScript("return arguments[0].innerText;", element);
		} catch (Exception e) {
			throw e;
		}
	}

	public static void enterTextUsingJavaScriptExecutor(WebDriver driver, WebElement element, String value) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value = arguments[1];", element, value);
		} catch (Exception e) {
			throw e;
		}
	}

	public static void highlightAnElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px dotted blue'", element);
	}

	public static void scrollToViewElement(WebDriver driver, By findByCondition) {
		WebElement element = driver.findElement(findByCondition);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void clickOnElementUsingJSE(WebDriver driver, By findByCondition) {
		WebElement element = driver.findElement(findByCondition);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public static void scrollUp(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-250)");
	}

	public static void scrollToBottom(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static boolean isElementPresent(WebDriver driver, By locator) {
		turnOffImplicitWaits(driver);
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			turnOnImplicitWaits(driver);
		}
	}

	public static void switchToFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	public static void hoverElement(WebDriver driver, By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator)).perform();
	}

	public static void enterData(WebDriver driver, By locator, int waitInSeconds, String data) {
		WebElement element = waitForElementClickable(driver, locator, waitInSeconds);
		element.clear();
		element.sendKeys(data);
	}

	public static void click(WebElement element, String message) {
		try {
			element.click();
			ExtentReportManager.logInfoDetails(message);
		} catch (Exception e) {
			throw e;
		}
	}

	public static void handleAlertPopUpWindow(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			// No alert to handle
		}
	}

	public static ArrayList<String> getFileNamesFromFolder(String path) {
		ArrayList<String> listOfFilesArray = new ArrayList<>();
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles != null) {
			for (File file : listOfFiles) {
				if (file.isFile()) {
					listOfFilesArray.add(file.getName());
				}
			}
		}
		return listOfFilesArray;
	}

	public static boolean waitTillPageURLToBeLoad(WebDriver driver, String fraction, int waitInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitInSeconds));
		return wait.until(ExpectedConditions.urlContains(fraction));
	}

	public static String getCurrentPageURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public static void waitAndClick(WebDriver driver, WebElement webElement, int time) {
		for (int i = 0; i <= time; i++) {
			try {
				if (webElement.isDisplayed() && webElement.isEnabled()) {
					webElement.click();
					break;
				}
			} catch (StaleElementReferenceException e) {
				// Retry the click
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // Restore interrupted status
			}
		}
	}

	public static ArrayList<String> getfileNamesFromFolder(String path) {
		ArrayList<String> listOfFilesArray = new ArrayList<>();
		File folder = new File(path);

		// Ensure the folder exists and is a directory
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles != null) {
			// Use enhanced for-loop for better readability
			for (File file : listOfFiles) {
				if (file.isFile()) {
					listOfFilesArray.add(file.getName());
				} else if (file.isDirectory()) {
					System.out.println("Directory: " + file.getName());
				}
			}
		} else {
			System.err.println("The specified path is not a valid directory: " + path);
		}

		return listOfFilesArray;
	}

	public static ArrayList<String> getSpecificColumnCellDetailsFromTable(WebDriver driver) {
		ArrayList<String> arrayList = new ArrayList<>();
		List<WebElement> tableCellText = driver
				.findElements(By.xpath("//div[@class='ui-table-wrapper ng-star-inserted']//tbody//tr//td[3]"));
		for (WebElement element : tableCellText) {
			arrayList.add(element.getText());
		}
		return arrayList;
	}

	public static void dragSliderToSomeExtent(WebDriver driver, int xAxis, int yAxis, WebElement element) {
		Actions actions = new Actions(driver);
		actions.dragAndDropBy(element, xAxis, yAxis).perform();
	}

	public static void enterTextInTextBox(WebDriver driver, WebElement element, int waitInSeconds, String textValue) {
		WebElement webElement = waitForElementVisibility(driver, element, waitInSeconds);
		webElement.clear();
		webElement.sendKeys(textValue);
	}

	public static void escMethod(WebDriver driver) {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).perform();
	}

	public static List<String> getAllSelectedOptionsFromDropDown(WebElement element) {
		Select select = new Select(element);
		List<WebElement> selectedOptions = select.getAllSelectedOptions();
		List<String> textList = new ArrayList<>();
		for (WebElement option : selectedOptions) {
			textList.add(option.getText());
		}
		return textList;
	}

	public static WebElement staleElementHandle(WebDriver driver, WebElement element, By locator) {
		try {
			element.isDisplayed();
			return element;
		} catch (StaleElementReferenceException e) {
			return driver.findElement(locator);
		}
	}

	public static boolean verifyTextContains(WebElement element, String expectedText) {
		return element.getText().contains(expectedText);
	}
}
