package com.investaSolutions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.investaSolutions.base.TestBase;
import com.investaSolutions.utils.PropertiesManager;
import com.investaSolutions.utils.SeleniumUtils;

public class AdminBenchmarkManagementPage {

	private WebDriver driver;
	public static PropertiesManager properties = PropertiesManager.getInstance();

	public AdminBenchmarkManagementPage (WebDriver driver) {
		this.driver = driver;
	}

	private static final int WAIT_SECONDS = Integer.valueOf(SeleniumUtils.fetchUserDetails("GLOBAL_WAIT"));
	private final By BENCHMARK_MANAGEMENT_TITLE = By.xpath("//legend[contains(text(),'Benchmark Management')]");
	private final By D_COLUMN_NAME = By.xpath("//body//th[1]");
	private final By BENCHMARK_NAME_COLUMN_NAME = By.xpath("//th[contains(text(),'Benchmark Name')]");
	private final By PROVIDER_COLUMN_NAME = By.xpath("//th[contains(text(),'Provider')]");
	private final By CURRENCY_COLUMN_NAME = By.xpath("//th[contains(text(),'Currency')]");
	private final By LAST_PRICE_COLUMN_NAME = By.xpath("//th[contains(text(),'Last Price')]");
	private final By LAST_PRICE_DATE_COLUMN_NAME = By.xpath("//th[contains(text(),'Last Price Date')]");
	private final By DATE_ADDED_COLUMN_NAME = By.xpath("//th[contains(text(),'Date Added')]");
	private final By GLOBAL_FILTER = By.xpath("//input[@placeholder='Global Filter']");	
	private final By BACK_BUTTON = By.xpath("//span[contains(text(),'Back')]");
	private final By NEW_BENCHMARK_BUTTON = By.xpath("//span[contains(text(),'New Benchmark')]");
	private final By INSTRUMENT_MANAGEMENT_TAB = By.xpath("//span[contains(text(),'Instrument Management')]");
	private final By BENCHMARKS_TAB = By.xpath("//span[text()='Benchmarks']");
	
	public WebElement instrumentManagementTabElement() {
		return SeleniumUtils.waitForElementPresence(driver, INSTRUMENT_MANAGEMENT_TAB, WAIT_SECONDS);
	}
	
	public WebElement benchmarksTabElement() {
		return SeleniumUtils.waitForElementPresence(driver, BENCHMARKS_TAB, WAIT_SECONDS);
	}

	public String benchmarkManagementTitleText() {
		return SeleniumUtils.waitForElementPresence(driver, BENCHMARK_MANAGEMENT_TITLE, WAIT_SECONDS).getText();
	}

	public String dColumnText() {
		return SeleniumUtils.waitForElementPresence(driver, D_COLUMN_NAME, WAIT_SECONDS).getText();
	}

	public String benchmarkNameColumnText() {
		return SeleniumUtils.waitForElementPresence(driver, BENCHMARK_NAME_COLUMN_NAME, WAIT_SECONDS).getText();
	}
	
	public String providerColumnText() {
		return SeleniumUtils.waitForElementPresence(driver, PROVIDER_COLUMN_NAME, WAIT_SECONDS).getText();
	}

	public String currencyColumnText() {
		return SeleniumUtils.waitForElementPresence(driver, CURRENCY_COLUMN_NAME, WAIT_SECONDS).getText();
	}
	
	public String lastPriceColumnText() {
		return SeleniumUtils.waitForElementPresence(driver, LAST_PRICE_COLUMN_NAME, WAIT_SECONDS).getText();
	}

	public String lastPriceDateColumnText() {
		return SeleniumUtils.waitForElementPresence(driver, LAST_PRICE_DATE_COLUMN_NAME, WAIT_SECONDS).getText();
	}
	
	public String dateAddedColumnText() {
		return SeleniumUtils.waitForElementPresence(driver, DATE_ADDED_COLUMN_NAME, WAIT_SECONDS).getText();
	}

	public String globalFilterText() {
		return SeleniumUtils.waitForElementPresence(driver, GLOBAL_FILTER, WAIT_SECONDS).getAttribute("placeholder");
	}

	public WebElement backButtonElement() {
		return SeleniumUtils.waitForElementPresence(driver, BACK_BUTTON, WAIT_SECONDS);
	}

	public WebElement newBenchmarkButtonElement() {
		return SeleniumUtils.waitForElementPresence(driver, NEW_BENCHMARK_BUTTON, WAIT_SECONDS);
	}

	public void clickOnBenchmarksTab(){
		try {
			SeleniumUtils.waitForElementClickable(driver, INSTRUMENT_MANAGEMENT_TAB, WAIT_SECONDS);
			SeleniumUtils.click(instrumentManagementTabElement(), properties.getLogMessage("AdminInstrumentManagementTabClickedSuccessfully"));
			SeleniumUtils.waitForElementClickable(driver, BENCHMARKS_TAB, WAIT_SECONDS);
			SeleniumUtils.click(benchmarksTabElement(), properties.getLogMessage("AdminBenchmarksTabClickedSuccessfully"));
		} catch (Exception e) {
			
		}
	}

	// This method verifies the details present on the Admin Portal's Benchmarks Management page
	public boolean verifyAdminBenchmarkManagementPageDetails(String benchmarkManagementTitleText, String viewColumnText, String benchmarkNameColumnText, String providerText, String currencyColumnText, String lastPriceColumnText, String lastPriceDateText, String dateAddedColumnText, String globalFilterText, String backButtonText, String newBenchmarkButtonText) throws Exception{
		boolean flag = false;
		try {
			clickOnBenchmarksTab();
			String benchmarkManagementTitleTextFromUI = benchmarkManagementTitleText();
			String viewColumnTextFromUI = dColumnText();
			String benchmarkNameColumnTextFromUI = benchmarkNameColumnText();
			String providerTextFromUI = providerColumnText();
			String currencyColumnTextFromUI = currencyColumnText();
			String lastPriceColumnTextFromUI = lastPriceColumnText();
			String lastPriceDateTextFromUI = lastPriceDateColumnText();
			String dateAddedColumnTextFromUI = dateAddedColumnText();
			String globalFilterTextFromUI = globalFilterText();
			SeleniumUtils.scrollToViewElement(driver, BACK_BUTTON);
			String backButtonTextFromUI = backButtonElement().getText();
			String newBenchmarkButtonTextFromUI = newBenchmarkButtonElement().getText();
			
			if(benchmarkManagementTitleTextFromUI.equals(benchmarkManagementTitleText) && viewColumnTextFromUI.equals(viewColumnText) && benchmarkNameColumnTextFromUI.equals(benchmarkNameColumnText) && providerTextFromUI.equals(providerText) && currencyColumnTextFromUI.equals(currencyColumnText) && lastPriceColumnTextFromUI.equals(lastPriceColumnText) && dateAddedColumnTextFromUI.equals(dateAddedColumnText) && lastPriceDateTextFromUI.equals(lastPriceDateText) && globalFilterTextFromUI.equals(globalFilterText) && backButtonTextFromUI.equals(backButtonText) && newBenchmarkButtonTextFromUI.equals(newBenchmarkButtonText)){
				flag = true;
				return flag;
			}
			if(!flag){
				
			}
		} catch (Exception e) {
			throw e;
		}		
		return flag;
	}



}
