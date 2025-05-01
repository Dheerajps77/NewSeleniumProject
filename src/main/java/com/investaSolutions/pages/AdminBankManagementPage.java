package com.investaSolutions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.investaSolutions.base.TestBase;
import com.investaSolutions.utils.PropertiesManager;
import com.investaSolutions.utils.SeleniumUtils;

public class AdminBankManagementPage {

	private WebDriver driver;
	public static PropertiesManager properties = PropertiesManager.getInstance();

	public AdminBankManagementPage (WebDriver driver) {
		this.driver = driver;
	}

	private static final int WAIT_SECONDS = Integer.valueOf(SeleniumUtils.fetchUserDetails("GLOBAL_WAIT"));
	private final By BANK_MANAGEMENT_TITLE = By.xpath("//legend[contains(text(),'Bank Management')]");
	private final By D_COLUMN_NAME = By.xpath("//th[contains(text(),'D')]");
	private final By NAME_COLUMN_NAME = By.xpath("//th[contains(text(),'Name')]");
	private final By ADDRESS_COLUMN_NAME = By.xpath("//th[contains(text(),'Address')]");
	private final By CONTACT_COLUMN_NAME = By.xpath("//th[contains(text(),'Contact')]");
	private final By LAST_UPDATE_DATE_COLUMN_NAME = By.xpath("//th[contains(text(),'Last update date')]");
	private final By BACK_BUTTON = By.xpath("//span[contains(text(),'Back')]");
	private final By NEW_BANK_BUTTON = By.xpath("//span[contains(text(),'New Bank')]");
	private final By BANK_MANAGEMENT_TAB = By.xpath("//span[contains(text(),'Bank Management')]");

	public WebElement bankManagementTabElement() {
		return SeleniumUtils.waitForElementPresence(driver, BANK_MANAGEMENT_TAB, WAIT_SECONDS);
	}

	public String bankManagementTitleText() {
		return SeleniumUtils.waitForElementPresence(driver, BANK_MANAGEMENT_TITLE, WAIT_SECONDS).getText();
	}

	public String dColumnText() {
		return SeleniumUtils.waitForElementPresence(driver, D_COLUMN_NAME, WAIT_SECONDS).getText();
	}

	public String nameColumnText() {
		return SeleniumUtils.waitForElementPresence(driver, NAME_COLUMN_NAME, WAIT_SECONDS).getText();
	}

	public String addressColumnText() {
		return SeleniumUtils.waitForElementPresence(driver, ADDRESS_COLUMN_NAME, WAIT_SECONDS).getText();
	}

	public String contactColumnText() {
		return SeleniumUtils.waitForElementPresence(driver, CONTACT_COLUMN_NAME, WAIT_SECONDS).getText();
	}

	public String lastUpdateDateText() {
		return SeleniumUtils.waitForElementPresence(driver, LAST_UPDATE_DATE_COLUMN_NAME, WAIT_SECONDS).getText();
	}

	public WebElement backButtonElement() {
		return SeleniumUtils.waitForElementPresence(driver, BACK_BUTTON, WAIT_SECONDS);
	}

	public WebElement newBankButtonElement() {
		return SeleniumUtils.waitForElementPresence(driver, NEW_BANK_BUTTON, WAIT_SECONDS);
	}

	public void clickOnBankManagementTab(){
		try {
			SeleniumUtils.click(bankManagementTabElement(), properties.getLogMessage("AdminBankManagementTabClickedSuccessfully"));
		} catch (Exception e) {
			
		}
	}

	// This method verifies the details present on the Admin Portal's page
	public boolean verifyBankManagementPageDetails(String bankManagementTitle, String dColumnText, String nameColumnText, String addressColumnText, String contactColumnText, String lastUpdateDateColumnText, String backButtonText, String newBankButtonText) throws Exception{
		boolean flag = false;
		try {
			clickOnBankManagementTab();
			String bankManagementTitleTextFromUI = bankManagementTitleText();
			String dColumnTextFromUI = dColumnText();
			String nameColumnTextFromUI = nameColumnText();
			String addressColumnTextFromUI = addressColumnText();
			String contactColumnTextFromUI = contactColumnText();
			String lastUpdateDateTextFromUI = lastUpdateDateText();
			String backButtonTextFromUI = backButtonElement().getText();
			String newBankButtonTextFromUI = newBankButtonElement().getText();
			if(bankManagementTitleTextFromUI.equals(bankManagementTitle) && dColumnTextFromUI.equals(dColumnText) && nameColumnTextFromUI.equals(nameColumnText) && addressColumnTextFromUI.equals(addressColumnText) && contactColumnTextFromUI.equals(contactColumnText) && lastUpdateDateTextFromUI.equals(lastUpdateDateColumnText) && backButtonTextFromUI.equals(backButtonText) && newBankButtonTextFromUI.equals(newBankButtonText)){
				flag = true;
			
				return flag;
			}
		} catch (Exception e) {
			throw e;
		}		
		return flag;
	}
}
