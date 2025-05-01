package com.investaSolutions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.investaSolutions.base.TestBase;
import com.investaSolutions.utils.PropertiesManager;
import com.investaSolutions.utils.SeleniumUtils;

public class AdminInstrumentManagementPage {


	private WebDriver driver;
	public static PropertiesManager properties = PropertiesManager.getInstance();

	public AdminInstrumentManagementPage (WebDriver driver) {
		this.driver = driver;
	}

	private static final int WAIT_SECONDS = Integer.valueOf(SeleniumUtils.fetchUserDetails("GLOBAL_WAIT"));
	private final By INSTRUMENT_MANAGEMENT_TITLE = By.xpath("//legend[contains(text(),'Instrument Management')]");
	private final By D_COLUMN_NAME = By.xpath("//th[text()='D']");
	private final By ISIN_COLUMN_NAME = By.xpath("//th[contains(text(),'Isin')]");
	private final By NAME_COLUMN_NAME = By.xpath("//th[contains(text(),'Name')]");
	private final By PRODUCT_TYPE_COLUMN_NAME = By.xpath("//th[contains(text(),'Product Type')]");
	private final By MAJOR_ASSET_CLASS_COLUMN_NAME = By.xpath("//th[contains(text(),'Major Asset Class')]");
	private final By CURRENCY_COLUMN_NAME = By.xpath("//th[contains(text(),'Currency')]");
	private final By LAST_PRICE_COLUMN_NAME = By.xpath("//th[contains(text(),'Last Price')]");
	private final By LAST_PRICE_DATE_COLUMN_NAME = By.xpath("//th[contains(text(),'Last Price Date')]");
	private final By GLOBAL_FILTER = By.xpath("//input[@placeholder='Global Filter']");	
	private final By BACK_BUTTON = By.xpath("//span[contains(text(),'Back')]");
	private final By NEW_INSTRUMENT_BUTTON = By.xpath("//span[contains(text(),'New Instrument')]");
	private final By INSTRUMENT_MANAGEMENT_TAB = By.xpath("//span[contains(text(),'Instrument Management')]");
	private final By INSTRUMENTS_TAB = By.xpath("//span[text()='Instruments']");
	
	public WebElement instrumentManagementTabElement() {
		return SeleniumUtils.waitForElementPresence(driver, INSTRUMENT_MANAGEMENT_TAB, WAIT_SECONDS);
	}
	
	public WebElement instrumentsTabElement() {
		return SeleniumUtils.waitForElementPresence(driver, INSTRUMENTS_TAB, WAIT_SECONDS);
	}

	public String instrumentManagementTitleText() {
		return SeleniumUtils.waitForElementPresence(driver, INSTRUMENT_MANAGEMENT_TITLE, WAIT_SECONDS).getText();
	}

	public String dColumnText() {
		return SeleniumUtils.waitForElementPresence(driver, D_COLUMN_NAME, WAIT_SECONDS).getText();
	}

	public String isinColumnText() {
		return SeleniumUtils.waitForElementPresence(driver, ISIN_COLUMN_NAME, WAIT_SECONDS).getText();
	}
	
	public String nameColumnText() {
		return SeleniumUtils.waitForElementPresence(driver, NAME_COLUMN_NAME, WAIT_SECONDS).getText();
	}

	public String productTypeColumnText() {
		return SeleniumUtils.waitForElementPresence(driver, PRODUCT_TYPE_COLUMN_NAME, WAIT_SECONDS).getText();
	}

	public String majorAssetClassColumnText() {
		return SeleniumUtils.waitForElementPresence(driver, MAJOR_ASSET_CLASS_COLUMN_NAME, WAIT_SECONDS).getText();
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

	public String globalFilterText() {
		return SeleniumUtils.waitForElementPresence(driver, GLOBAL_FILTER, WAIT_SECONDS).getAttribute("placeholder");
	}

	public WebElement backButtonElement() {
		return SeleniumUtils.waitForElementPresence(driver, BACK_BUTTON, WAIT_SECONDS);
	}

	public WebElement newInstrumentButtonElement() {
		return SeleniumUtils.waitForElementPresence(driver, NEW_INSTRUMENT_BUTTON, WAIT_SECONDS);
	}

	public void clickOnInstrumentsTab(){
		try {
			SeleniumUtils.waitForElementClickable(driver, INSTRUMENT_MANAGEMENT_TAB, WAIT_SECONDS);
			SeleniumUtils.click(instrumentManagementTabElement(), properties.getLogMessage("AdminInstrumentManagementTabClickedSuccessfully"));
			SeleniumUtils.waitForElementClickable(driver, INSTRUMENTS_TAB, WAIT_SECONDS);
			SeleniumUtils.click(instrumentsTabElement(), properties.getLogMessage("AdminInstrumentsTabClickedSuccessfully"));
		} catch (Exception e) {
			
		}
	}

	// This method verifies the details present on the Admin Portal's Instrument Management page
	public boolean verifyAdminInstrumentManagementPageDetails(String instrumentManagementTitle, String viewColumnText, String isinColumnText, String nameColumnText, String productTypeColumnText, String majorAssetClassColumnText, String currencyColumnText, String lastPriceColumnText, String lastPriceDateText, String globalFilterText, String backButtonText, String newInstrumentButtonText) throws Exception{
		boolean flag = false;
		try {
			clickOnInstrumentsTab();
			String instrumentManagementTitleTextFromUI = instrumentManagementTitleText();
			String viewColumnTextFromUI = dColumnText();
			String isinColumnTextFromUI = isinColumnText();
			String nameColumnTextFromUI = nameColumnText();
			String productTypeColumnTextFromUI = productTypeColumnText();
			String majorAssetClassColumnTextFromUI = majorAssetClassColumnText();
			String currencyColumnTextFromUI = currencyColumnText();
			String lastPriceColumnTextFromUI = lastPriceColumnText();
			String lastPriceDateTextFromUI = lastPriceDateColumnText();
			String globalFilterTextFromUI = globalFilterText();
			SeleniumUtils.scrollToViewElement(driver, BACK_BUTTON);
			String backButtonTextFromUI = backButtonElement().getText();
			String newInstrumentButtonTextFromUI = newInstrumentButtonElement().getText();
			
			if(instrumentManagementTitleTextFromUI.equals(instrumentManagementTitle) && viewColumnTextFromUI.equals(viewColumnText) && isinColumnTextFromUI.equals(isinColumnText) && nameColumnTextFromUI.equals(nameColumnText) && productTypeColumnTextFromUI.equals(productTypeColumnText) && majorAssetClassColumnTextFromUI.equals(majorAssetClassColumnText) && currencyColumnTextFromUI.equals(currencyColumnText) && lastPriceColumnTextFromUI.equals(lastPriceColumnText) && lastPriceDateTextFromUI.equals(lastPriceDateText) && globalFilterTextFromUI.equals(globalFilterText) && backButtonTextFromUI.equals(backButtonText) && newInstrumentButtonTextFromUI.equals(newInstrumentButtonText)){
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
