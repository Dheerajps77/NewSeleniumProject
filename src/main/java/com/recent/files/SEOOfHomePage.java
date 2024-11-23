package com.recent.files;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.investaSolutions.base.*;
import com.investaSolutions.utils.ExtentReportManager;
import com.investaSolutions.utils.PropertiesManager;
import com.investaSolutions.utils.SeleniumUtils;

public class SEOOfHomePage {

	WebDriver driver;
	SeleniumUtils objSeleniumUtils = new SeleniumUtils();
	static PropertiesManager contsProperties = PropertiesManager.getInstance();

	public SEOOfHomePage(WebDriver driver) {
		this.driver = driver;
	}

	public final By BUY_CAR_GUARANTEED_HEADING = By
			.xpath("//article[@id='top-banner']//div[@class='heading-section']//h1");
	public final By BUY_CAR_YOU_LOVE_SUBHEADING = By
			.xpath("//article[@id='top-banner']//div[@class='heading-section']//h2");

	public final By SPINNY_LOGO = By.xpath("//div[@id='header-wrapper']/child::div[1]/child::div[1]/a/img[1]");

	public final By BUY_A_CAR_Title = By.xpath(
			"//div[contains(@class,'show-for-large viewport-wrapper') and @id='header-wrapper']//div[contains(@class,'right-section')]/child::div[1]//ul[@class='main-menu']//a[text()='BUY A CAR']");
	public final By SELL_CAR_Title = By.xpath(
			"//div[contains(@class,'show-for-large viewport-wrapper') and @id='header-wrapper']//div[contains(@class,'right-section')]/child::div[1]//ul[@class='main-menu']//a[text()='SELL CAR']");
	public final By MORE_Title = By.xpath(
			"//div[contains(@class,'show-for-large viewport-wrapper') and @id='header-wrapper']//div[contains(@class,'right-section')]/child::div[1]//ul[@class='main-menu']//a[text()='MORE']");

	public final By MOBILE_NUMBER = By.xpath("//div[@class='mobile-icon-section']//a");

	public final By SPINNY_ASSURED_HEADING_TITLE = By.xpath(
			"//section[@id='spinny-assured']//div[@class='spinny-assured-content-wrapper hide-for-small-only']//div[@class='section-heading-wrapper']//h3");
	public final By SPINNY_ASSURED_SUB_HEADING_TITLE = By.xpath(
			"//section[@id='spinny-assured']//div[@class='spinny-assured-content-wrapper hide-for-small-only']//div[@class='section-heading-wrapper']//p");

	public final By UNIVERSAL_DROPDOWN_TITLE = By.xpath(
			"//div[@class='show-for-large viewport-wrapper clearfix top-header']//article[@class='universal-search ng-isolate-scope']//div[@class='city-dropdown no-select hide-for-small-only']//span");

	public By HEADINGS_UNDER_HOW_IT_WORS = By.xpath(
			"//div[@class='content-items-section show-for-large']//div//div[@class='content-section']//div[@class='heading']");

	public By SUB_HEADINGS_UNDER_HOW_IT_WORS = By.xpath(
			"//div[@class='content-items-section show-for-large']//div//div[@class='content-section']//div[@class='sub-heading']");

	public By SPINNYASSURED_CONTENTS = By.xpath(
			"//div[@class='spinny-assured-content-wrapper hide-for-small-only']//div[@class='fixed-price-section']//div[@class='content-section']//div");

	public By HEADING_HOW_IT_WORKS = By.xpath("//div[@class='how-it-work-wrapper']/child::div/div[1]/child::div[1]");
	public By SUB_HEADING_OF_HOW_IT_WORKS = By
			.xpath("//div[@class='how-it-work-wrapper']/child::div/div[1]/child::div[2]");

	public By HEADINGS_OF_CAR_CATEGORY = By
			.xpath("//div[@class='category-section']/child::div//a/div[@class='category-heading']");

	public By HEADING_SPINNY_CAR_HUBS = By.xpath(
			"//div[@class='viewport-wrapper']//div[@class='section-heading-wrapper']//div[text()='Spinny car hubs']");
	public By SUB_HEADING_OF_SPINNY_CAR_HUBS = By.xpath(
			"//div[@class='viewport-wrapper']//div[@class='section-heading-wrapper']//div[text()='Choose online. Test drive at the nearest car hub.']");

	
	public By HEADING_SPINNY_LOVE_STORIES = By.xpath(
			"//section[@class='testimonials-section hide-for-small-only']//div[@class='section-heading-wrapper']//div[@class='heading' and text()='Spinny love stories']");
	public By SUB_HEADING_OF_SPINNY_LOVE_STORIES = By.xpath(
			"//section[@class='testimonials-section hide-for-small-only']//div[@class='section-heading-wrapper']//div[text()='6000+ reasons to Spinny.']");

	public WebElement headingSpinnyLoveStories() {
		return SeleniumUtils.waitForElementPresence(driver, HEADING_SPINNY_LOVE_STORIES, 20);
	}

	public WebElement subHeadingSpinnyLoveStories() {
		return SeleniumUtils.waitForElementPresence(driver, SUB_HEADING_OF_SPINNY_LOVE_STORIES, 20);
	}


	
	public WebElement headingSpinnyCarHubs() {
		return SeleniumUtils.waitForElementPresence(driver, HEADING_SPINNY_CAR_HUBS, 20);
	}

	public WebElement subHeadingSpinnyCarHubs() {
		return SeleniumUtils.waitForElementPresence(driver, SUB_HEADING_OF_SPINNY_CAR_HUBS, 20);
	}

	public WebElement headingHowItWorks() {
		return SeleniumUtils.waitForElementPresence(driver, HEADING_HOW_IT_WORKS, 20);
	}

	public WebElement subHeadingHowItWorks() {
		return SeleniumUtils.waitForElementPresence(driver, SUB_HEADING_OF_HOW_IT_WORKS, 20);
	}

	public WebElement universalDropDownTitle() {
		return SeleniumUtils.waitForElementPresence(driver, UNIVERSAL_DROPDOWN_TITLE, 20);
	}

	public WebElement spinnyAssuredHeadingTitle() {
		return SeleniumUtils.waitForElementPresence(driver, SPINNY_ASSURED_HEADING_TITLE, 20);
	}

	public WebElement spinnyAssuredSubHeadingTitle() {
		return SeleniumUtils.waitForElementPresence(driver, SPINNY_ASSURED_SUB_HEADING_TITLE, 20);
	}

	public WebElement buyACarTitle() {
		return SeleniumUtils.waitForElementPresence(driver, BUY_A_CAR_Title, 20);
	}

	public WebElement sellCarTitle() {
		return SeleniumUtils.waitForElementPresence(driver, SELL_CAR_Title, 20);
	}

	public WebElement moreTitle() {
		return SeleniumUtils.waitForElementPresence(driver, MORE_Title, 20);
	}

	public WebElement mobileNumber() {
		return SeleniumUtils.waitForElementPresence(driver, MOBILE_NUMBER, 20);
	}

	public WebElement buyCarYouLoveHeading() {
		return SeleniumUtils.waitForElementPresence(driver, BUY_CAR_GUARANTEED_HEADING, 20);
	}

	public WebElement buyCarYouLovSubeHeading() {
		return SeleniumUtils.waitForElementPresence(driver, BUY_CAR_YOU_LOVE_SUBHEADING, 20);
	}

	public WebElement spinnyLogo() {
		return SeleniumUtils.waitForElementPresence(driver, SPINNY_LOGO, 20);
	}

	String attributeName_Alt = contsProperties.getConstant("ALT");

	/***
	 * This will verify the title of Home page
	 * 
	 * @return
	 */
	public boolean verifyTitleOfHomePage(String expectedtitleOfSpinny) {
		String actualtitleOfSpinny = "";
		boolean flag = false;
		try {
			actualtitleOfSpinny = SeleniumUtils.getCurrentPageTitle(driver);
			if (actualtitleOfSpinny.equalsIgnoreCase(expectedtitleOfSpinny)) {
				ExtentReportManager.logInfoDetails(String.format(TestBase.properties.getLogMessage("VerifyTitleOfHomePagePassed"),
						actualtitleOfSpinny, expectedtitleOfSpinny));
				flag = true;
			}

		} catch (Exception e) {
			ExtentReportManager.logFailureDetails(String.format(TestBase.properties.getLogMessage("VerifyTitleOfHomePageFailed"),
					actualtitleOfSpinny, expectedtitleOfSpinny));
			throw e;
		}
		return flag;
	}

	/***
	 * This will verify the Spinny LOGO present in the page
	 * 
	 * @return
	 */
	public boolean verifySpinnyLogo() {
		String attributeValue = "";
		boolean flag = false;
		try {
			if (spinnyLogo().isDisplayed()) {
				attributeValue = objSeleniumUtils.getAttributeValue(driver, spinnyLogo(), attributeName_Alt);
				ExtentReportManager.logInfoDetails(
						String.format(TestBase.properties.getLogMessage("VerifySpinnyLogoPassed"), attributeValue));
				flag = true;
			}
		} catch (Exception e) {
			ExtentReportManager.logFailureDetails(
					String.format(TestBase.properties.getLogMessage("VerifySpinnyLogoFailed"), attributeValue));
			throw e;
		}
		return flag;
	}
	
	

	/***
	 * This will verify the heading title of "Buy a car you will love. Guaranteed"
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public boolean verifyBuyCarYouLoveHeadingTitle(String expectedHeadingTitleOfBuyCarYouLove) throws Exception {
		boolean flag = false;
		String actualHeadingTitleOfBuyCarYouLove = "";
		try {
			objSeleniumUtils = new SeleniumUtils();
			if (buyCarYouLoveHeading().isDisplayed()) {
				actualHeadingTitleOfBuyCarYouLove = objSeleniumUtils.getTextOfElement(driver, buyCarYouLoveHeading());
				if (actualHeadingTitleOfBuyCarYouLove.equalsIgnoreCase(expectedHeadingTitleOfBuyCarYouLove)) {
					ExtentReportManager.logInfoDetails(
							String.format(TestBase.properties.getLogMessage("VerifyHeadinOfBuyCarYouLovePassed"),
									actualHeadingTitleOfBuyCarYouLove, expectedHeadingTitleOfBuyCarYouLove));
					flag = true;
				}
			}

		} catch (Exception e) {
			ExtentReportManager.logFailureDetails(String.format(TestBase.properties.getLogMessage("VerifyHeadinOfBuyCarYouLoveFailed"),
					actualHeadingTitleOfBuyCarYouLove, expectedHeadingTitleOfBuyCarYouLove));
			throw e;
		}
		return flag;
	}

	/***
	 * This will verify the Sub heading title of "#LetsGoForASpinny"
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public boolean verifyBuyCarYouLoveHSubHeadingTitle(String expectedSubHeadingTitleOfBuyCarYouLove) throws Exception {
		boolean flag = false;
		String actualSubHeadingTitleOfBuyCarYouLove = "";
		try {
			if (buyCarYouLovSubeHeading().isDisplayed()) {
				actualSubHeadingTitleOfBuyCarYouLove = objSeleniumUtils.getTextOfElement(driver,
						buyCarYouLovSubeHeading());
				if (actualSubHeadingTitleOfBuyCarYouLove.equalsIgnoreCase(expectedSubHeadingTitleOfBuyCarYouLove)) {
					ExtentReportManager.logInfoDetails(
							String.format(TestBase.properties.getLogMessage("VerifySubHeadinOfBuyCarYouLovePassed"),
									actualSubHeadingTitleOfBuyCarYouLove, expectedSubHeadingTitleOfBuyCarYouLove));
					flag = true;
				}
			}

		} catch (Exception e) {
			ExtentReportManager.logFailureDetails(String.format(TestBase.properties.getLogMessage("VerifySubHeadinOfBuyCarYouLoveFailed"),
					actualSubHeadingTitleOfBuyCarYouLove, expectedSubHeadingTitleOfBuyCarYouLove));
			throw e;
		}
		return flag;
	}

	/***
	 * This will verify the title of Drop Down "BUY A CAR"
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifyBuyACarTitle(String expectedtitleBuyACar) throws Exception {
		String actualtitleOfBuyACar = "";
		boolean flag = false;
		try {
			if (buyACarTitle().isDisplayed()) {
				actualtitleOfBuyACar = objSeleniumUtils.getTextOfElement(driver, buyACarTitle());
				if (actualtitleOfBuyACar.equalsIgnoreCase(expectedtitleBuyACar)) {
					ExtentReportManager.logInfoDetails(String.format(TestBase.properties.getLogMessage("VerifyTitleOfBuyACarPassed"),
							actualtitleOfBuyACar, expectedtitleBuyACar));
					flag = true;
				}
			}

		} catch (Exception e) {
			ExtentReportManager.logFailureDetails(String.format(TestBase.properties.getLogMessage("VerifyTitleOfBuyACarFailed"),
					actualtitleOfBuyACar, expectedtitleBuyACar));
			throw e;
		}
		return flag;
	}

	/***
	 * This will verify the title of SELL CAR
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifySellCarTitle(String expectedtitleSellCar) throws Exception {
		String actualtitleOfSellCar = "";
		boolean flag = false;
		try {
			if (sellCarTitle().isDisplayed()) {
				actualtitleOfSellCar = objSeleniumUtils.getTextOfElement(driver, sellCarTitle());
				if (actualtitleOfSellCar.equalsIgnoreCase(expectedtitleSellCar)) {
					ExtentReportManager.logInfoDetails(String.format(TestBase.properties.getLogMessage("VerifyTitleOfSellCarPassed"),
							actualtitleOfSellCar, expectedtitleSellCar));
					flag = true;
				}
			}

		} catch (Exception e) {
			ExtentReportManager.logFailureDetails(String.format(TestBase.properties.getLogMessage("VerifyTitleOfSellCarFailed"),
					actualtitleOfSellCar, expectedtitleSellCar));
			throw e;
		}
		return flag;
	}

	/***
	 * This will verify the title of Drop Down "MORE"
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifyMoreTitle(String expectedtitleMore) throws Exception {
		String actualtitleOfMore = "";
		boolean flag = false;
		try {
			if (moreTitle().isDisplayed()) {
				actualtitleOfMore = objSeleniumUtils.getTextOfElement(driver, moreTitle());
				if (actualtitleOfMore.equalsIgnoreCase(expectedtitleMore)) {

					ExtentReportManager.logInfoDetails(String.format(TestBase.properties.getLogMessage("VerifyTitleOfMorePassed"),
							actualtitleOfMore, expectedtitleMore));
					flag = true;
				}

			}

		} catch (Exception e) {
			ExtentReportManager.logFailureDetails(String.format(TestBase.properties.getLogMessage("VerifyTitleOfMoreFailed"),
					actualtitleOfMore, expectedtitleMore));
			throw e;
		}
		return flag;
	}

	/***
	 * This will verify the title of Mobile Number
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifyMobileNumberTitle(String expectedTitleOfMobileNumber) throws Exception {
		String actualTitleOfMobileNumber = "";
		boolean flag = false;
		try {
			if (moreTitle().isDisplayed()) {
				actualTitleOfMobileNumber = objSeleniumUtils.getTextOfElement(driver, mobileNumber()).trim();
				if (actualTitleOfMobileNumber.equalsIgnoreCase(expectedTitleOfMobileNumber)) {
					ExtentReportManager.logInfoDetails(String.format(TestBase.properties.getLogMessage("VerifyTitleOfMobileNumberPassed"),
							actualTitleOfMobileNumber, expectedTitleOfMobileNumber));
					flag = true;
				}
			}

		} catch (Exception e) {
			ExtentReportManager.logFailureDetails(String.format(TestBase.properties.getLogMessage("VerifyTitleOfMobileNumberFailed"),
					actualTitleOfMobileNumber, expectedTitleOfMobileNumber));
			throw e;
		}
		return flag;
	}

	/***
	 * This will verify the heading title of "Spinny Assured"
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public boolean verifySpinnyAssuredHeadingTitle(String expectedHeadingSpinnyAssuredTitle) throws Exception {
		boolean flag = false;
		String actualHeadingSpinnyAssuredTitle = "";
		try {
			SeleniumUtils.scrollToViewElement(driver, SPINNY_ASSURED_HEADING_TITLE);
			if (spinnyAssuredSubHeadingTitle().isDisplayed()) {
				actualHeadingSpinnyAssuredTitle = objSeleniumUtils.getTextOfElement(driver,
						spinnyAssuredHeadingTitle());
				if (actualHeadingSpinnyAssuredTitle.equalsIgnoreCase(expectedHeadingSpinnyAssuredTitle)) {
					ExtentReportManager.logInfoDetails(
							String.format(TestBase.properties.getLogMessage("VerifyTitleOfSpinnyAssuredPassed"),
									actualHeadingSpinnyAssuredTitle, expectedHeadingSpinnyAssuredTitle));
					flag = true;
				}
			}

		} catch (Exception e) {
			ExtentReportManager.logFailureDetails(String.format(TestBase.properties.getLogMessage("VerifyTitleOfSpinnyAssureFailed"),
					actualHeadingSpinnyAssuredTitle, expectedHeadingSpinnyAssuredTitle));
			throw e;
		}
		return flag;
	}

	/***
	 * This will verify the Sub-heading title of "Spinny Assured"
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public boolean verifySpinnyAssuredSubHeadingTitle(String expectedSubHeadingOfSpinnyAssuredTitle) throws Exception {
		boolean flag = false;
		String actualSubHeadingOfSpinnyAssuredTitle = "";
		try {
			objSeleniumUtils = new SeleniumUtils();
			SeleniumUtils.scrollToViewElement(driver, SPINNY_ASSURED_SUB_HEADING_TITLE);
			if (spinnyAssuredSubHeadingTitle().isDisplayed()) {
				actualSubHeadingOfSpinnyAssuredTitle = objSeleniumUtils.getTextOfElement(driver,
						spinnyAssuredSubHeadingTitle());
				if (actualSubHeadingOfSpinnyAssuredTitle.equalsIgnoreCase(expectedSubHeadingOfSpinnyAssuredTitle)) {
					ExtentReportManager.logInfoDetails(
							String.format(TestBase.properties.getLogMessage("VerifySubHeadingOfSpinnyAssuredPassed"),
									actualSubHeadingOfSpinnyAssuredTitle, expectedSubHeadingOfSpinnyAssuredTitle));
					flag = true;
				}
			}

		} catch (Exception e) {
			ExtentReportManager.logFailureDetails(String.format(TestBase.properties.getLogMessage("VerifySubHeadingOfSpinnyAssuredFailed"),
					actualSubHeadingOfSpinnyAssuredTitle, expectedSubHeadingOfSpinnyAssuredTitle));
			throw e;
		}
		return flag;
	}

	public ArrayList<String> listOfContentsTitleOfSpinnyAssured() {
		ArrayList<String> arraylist = null;
		String textOfElement = "";
		try {
			arraylist = new ArrayList<String>();
			List<WebElement> elements = driver.findElements(SPINNYASSURED_CONTENTS);
			int totalCountOfContent = elements.size();
			for (int i = 1; i <= totalCountOfContent; i++) {
				WebElement element = driver.findElement(By.xpath(
						"//div[@class='spinny-assured-content-wrapper hide-for-small-only']//div[@class='fixed-price-section']//div[@class='content-section']//div["
								+ i + "]"));
				textOfElement = element.getText();
				arraylist.add(textOfElement);
			}
		} catch (Exception e) {
			throw e;
		}

		return arraylist;
	}

	/***
	 * This will verify the contents under Spinny Assure sections
	 * 
	 * @return
	 */
	public boolean verifyContentUnderSpinnyAssuredSections(String expectedintroducingTitle,
			String expectedFixedPriceAussranceTitle, String expectedKeepingItSimpleTitle) {
		boolean flag = false;
		ArrayList<String> arraylist = null;
		try {
			arraylist = listOfContentsTitleOfSpinnyAssured();
			if (arraylist.contains(expectedintroducingTitle) && arraylist.contains(expectedFixedPriceAussranceTitle)
					&& arraylist.contains(expectedKeepingItSimpleTitle)) {
				ExtentReportManager.logInfoDetails(String.format(
						TestBase.properties.getLogMessage("VerifyContentsUnderSpinnyAssuredPassed"), arraylist,
						expectedintroducingTitle, expectedFixedPriceAussranceTitle, expectedKeepingItSimpleTitle));
				flag = true;
			}
		} catch (Exception e) {
			ExtentReportManager.logFailureDetails(String.format(TestBase.properties.getLogMessage("VerifyContentsUnderSpinnyAssuredFailed"),
					arraylist, expectedintroducingTitle, expectedFixedPriceAussranceTitle,
					expectedKeepingItSimpleTitle));
			throw e;
		}
		return flag;
	}

	/***
	 * This will verify the dropdown title of universal search
	 * 
	 * @param expextedUniversalDropdownTitle
	 * @return
	 * @throws Exception
	 */
	public boolean verifyUniversalDropdownTitle(String expextedUniversalDropdownTitle) throws Exception {
		boolean flag = false;
		String actualUniversalDropdownTitle = "";
		try {

			if (universalDropDownTitle().isDisplayed()) {
				actualUniversalDropdownTitle = objSeleniumUtils.getTextOfElement(driver, universalDropDownTitle());
				if (actualUniversalDropdownTitle.equalsIgnoreCase(expextedUniversalDropdownTitle)) {
					ExtentReportManager.logInfoDetails(String.format(
							TestBase.properties.getLogMessage("VerifyTitleOfUniversalDropdownTitlePassed"),
							actualUniversalDropdownTitle, expextedUniversalDropdownTitle));
					flag = true;
				}
			}
		} catch (Exception e) {
			ExtentReportManager.logFailureDetails(
					String.format(TestBase.properties.getLogMessage("VerifyTitleOfUniversalDropdownTitleFailed"),
							actualUniversalDropdownTitle, expextedUniversalDropdownTitle));
			throw e;
		}
		return flag;
	}

	public boolean verifyHeadingOfHowItWorks(String expectedHeadingOfHowItWorks) throws Exception {
		boolean flag = false;
		String actualHeadingOfHowItWorks = "";
		try {
			SeleniumUtils.scrollToViewElement(driver, HEADING_HOW_IT_WORKS);
			if (headingHowItWorks().isDisplayed()) {
				actualHeadingOfHowItWorks = objSeleniumUtils.getTextOfElement(driver, headingHowItWorks());
				if (actualHeadingOfHowItWorks.equalsIgnoreCase(expectedHeadingOfHowItWorks)) {
					ExtentReportManager.logInfoDetails(String.format(TestBase.properties.getLogMessage("VerifyHeadingOfHowItWorksPassed"),
							actualHeadingOfHowItWorks, expectedHeadingOfHowItWorks));
					flag = true;
				}
			}

		} catch (Exception e) {

			ExtentReportManager.logInfoDetails(String.format(TestBase.properties.getLogMessage("VerifyHeadingOfHowItWorksFailed"),
					actualHeadingOfHowItWorks, expectedHeadingOfHowItWorks));

			throw e;
		}
		return flag;
	}

	public boolean verifySubHeadingOfHowItWorks(String expectedSubHeadingOfHowItWorks) throws Exception {
		boolean flag = false;
		String actualSubHeadingOfHowItWorks = "";
		try {
			if (subHeadingHowItWorks().isDisplayed()) {
				actualSubHeadingOfHowItWorks = objSeleniumUtils.getTextOfElement(driver, subHeadingHowItWorks());
				if (actualSubHeadingOfHowItWorks.equalsIgnoreCase(expectedSubHeadingOfHowItWorks)) {
					ExtentReportManager.logInfoDetails(
							String.format(TestBase.properties.getLogMessage("VerifySubHeadingOfHowItWorksPassed"),
									actualSubHeadingOfHowItWorks, expectedSubHeadingOfHowItWorks));
					flag = true;
				}
			}

		} catch (Exception e) {

			ExtentReportManager.logFailureDetails(String.format(TestBase.properties.getLogMessage("VerifySubHeadingOfHowItWorksFailed"),
					actualSubHeadingOfHowItWorks, expectedSubHeadingOfHowItWorks));

			throw e;
		}
		return flag;
	}

	/***
	 * This will return the list of headings under How it Work title
	 * 
	 * @return
	 */
	public ArrayList<String> listOfHeadingsTitleUnderHowItWorks() {
		ArrayList<String> arraylist = null;
		String textOfElement = "";
		try {
			arraylist = new ArrayList<String>();
			List<WebElement> elements = driver.findElements(HEADINGS_UNDER_HOW_IT_WORS);
			int totalCountOfContent = elements.size();
			for (int i = 1; i <= totalCountOfContent; i++) {
				WebElement element = driver
						.findElement(By.xpath("//div[@class='content-items-section show-for-large']//div[" + i
								+ "]//div[@class='content-section']//div[@class='heading']"));
				textOfElement = element.getText();
				arraylist.add(textOfElement);
			}
		} catch (Exception e) {
			throw e;
		}

		return arraylist;
	}

	/***
	 * This will verify the contents under Spinny Assure sections
	 * 
	 * @return
	 */
	public boolean verifyHeadingsUnderHowItWorksSections(String expectedHeading1, String expectedHeading2,
			String expectedHeading3) {
		boolean flag = false;
		ArrayList<String> arraylist = null;
		try {
			arraylist = listOfHeadingsTitleUnderHowItWorks();
			for (int i = 0; i < arraylist.size(); i++) {
				String str = arraylist.get(i);
				arraylist.set(i, str.replaceAll("\n", " "));
			}

			if (arraylist.contains(expectedHeading1) && arraylist.contains(expectedHeading2)
					&& arraylist.contains(expectedHeading3)) {
				ExtentReportManager.logInfoDetails(String.format(TestBase.properties.getLogMessage("VerifyHeadingUnderHowItWorksPassed"),
						arraylist, expectedHeading1, expectedHeading2, expectedHeading3));
				flag = true;

			}

		} catch (Exception e) {
			ExtentReportManager.logFailureDetails(String.format(TestBase.properties.getLogMessage("VerifyContentsUnderSpinnyAssuredFailed"),
					arraylist, expectedHeading1, expectedHeading2, expectedHeading3));
			throw e;
		}
		return flag;
	}

	/***
	 * This will return the list of Sub-headings under How it Work title
	 * 
	 * @return
	 */
	public ArrayList<String> listOfSubHeadingsTitleUnderHowItWorks() {
		ArrayList<String> arraylist = null;
		String textOfElement = "";
		try {
			arraylist = new ArrayList<String>();
			List<WebElement> elements = driver.findElements(SUB_HEADINGS_UNDER_HOW_IT_WORS);
			int totalCountOfContent = elements.size();
			for (int i = 1; i <= totalCountOfContent; i++) {
				WebElement element = driver
						.findElement(By.xpath("//div[@class='content-items-section show-for-large']//div[" + i
								+ "]//div[@class='content-section']//div[@class='sub-heading']"));
				textOfElement = element.getText();
				arraylist.add(textOfElement);
			}
		} catch (Exception e) {
			throw e;
		}

		return arraylist;
	}

	public boolean verifySubHeadingsUnderHowItWorksSections(String expectedHeading1, String expectedHeading2,
			String expectedHeading3) {
		boolean flag = false;
		ArrayList<String> arraylist = null;
		try {
			arraylist = listOfSubHeadingsTitleUnderHowItWorks();
			for (int i = 0; i < arraylist.size(); i++) {
				String str = arraylist.get(i);
				arraylist.set(i, str.replaceAll("\n", " "));
			}
			if (arraylist.contains(expectedHeading1) && arraylist.contains(expectedHeading2)
					&& arraylist.contains(expectedHeading3)) {
				ExtentReportManager.logInfoDetails(
						String.format(TestBase.properties.getLogMessage("VerifySubHeadingUnderHowItWorksPassed"),
								arraylist, expectedHeading1, expectedHeading2, expectedHeading3));
				flag = true;
			}
		} catch (Exception e) {
			ExtentReportManager.logFailureDetails(String.format(TestBase.properties.getLogMessage("VerifySubHeadingUnderHowItWorksFailed"),
					arraylist, expectedHeading1, expectedHeading2, expectedHeading3));
			throw e;
		}
		return flag;
	}

	public By HEADING_TAKE_YOUR_PICK = By.xpath("//div[@class='heading-section']//div[text()='Take your pick']");
	public By SUB_HEADING_OF_TAKE_YOUR_PICK = By
			.xpath("//section[@class='car-category-section viewport-wrapper']//div[contains(text(),'There')]");

	public WebElement headingTakeYourPick() {
		return SeleniumUtils.waitForElementPresence(driver, HEADING_TAKE_YOUR_PICK, 20);
	}

	public WebElement subHeadingTakeYourPick() {
		return SeleniumUtils.waitForElementPresence(driver, SUB_HEADING_OF_TAKE_YOUR_PICK, 20);
	}

	public boolean verifyHeadingTakeYourPick(String expectedHeadingOfTakeYourPick) throws Exception {
		boolean flag = false;
		String actualHeadingOfTakeYourPick = "";
		try {
			SeleniumUtils.scrollToViewElement(driver, HEADING_TAKE_YOUR_PICK);
			if (headingHowItWorks().isDisplayed()) {
				actualHeadingOfTakeYourPick = objSeleniumUtils.getTextOfElement(driver, headingTakeYourPick());
				if (actualHeadingOfTakeYourPick.equalsIgnoreCase(expectedHeadingOfTakeYourPick)) {
					ExtentReportManager.logInfoDetails(
							String.format(TestBase.properties.getLogMessage("VerifyHeadingOfTakeYourPickPassed"),
									actualHeadingOfTakeYourPick, expectedHeadingOfTakeYourPick));
					flag = true;
				}
			}

		} catch (Exception e) {

			ExtentReportManager.logInfoDetails(String.format(TestBase.properties.getLogMessage("VerifyHeadingOfTakeYourPickFailed"),
					actualHeadingOfTakeYourPick, expectedHeadingOfTakeYourPick));

			throw e;
		}
		return flag;
	}

	public boolean verifySubHeadingOfTakeYourPick(String expectedSubHeadingOfTakeYourPick) throws Exception {
		boolean flag = false;
		String actualSubHeadingOfTakeYourPick = "";
		try {
			if (subHeadingHowItWorks().isDisplayed()) {
				actualSubHeadingOfTakeYourPick = objSeleniumUtils.getTextOfElement(driver, subHeadingTakeYourPick());
				if (actualSubHeadingOfTakeYourPick.equalsIgnoreCase(expectedSubHeadingOfTakeYourPick)) {
					ExtentReportManager.logInfoDetails(
							String.format(TestBase.properties.getLogMessage("VerifySubHeadingOfTakeYourPickPassed"),
									actualSubHeadingOfTakeYourPick, expectedSubHeadingOfTakeYourPick));
					flag = true;
				}
			}

		} catch (Exception e) {

			ExtentReportManager.logFailureDetails(String.format(TestBase.properties.getLogMessage("VerifySubHeadingOfTakeYourPickFailed"),
					actualSubHeadingOfTakeYourPick, expectedSubHeadingOfTakeYourPick));

			throw e;
		}
		return flag;
	}

	/***
	 * This will return the list of headings of Car Category
	 * 
	 * @return
	 */
	public ArrayList<String> listOfHeadingOfCarCategory() {
		ArrayList<String> arraylist = null;
		String textOfElement = "";
		try {
			arraylist = new ArrayList<String>();
			List<WebElement> elements = driver.findElements(HEADINGS_OF_CAR_CATEGORY);
			int totalCountOfContent = elements.size();
			for (int i = 1; i <= totalCountOfContent; i++) {
				WebElement element = driver.findElement(By.xpath(
						"//div[@class='category-section']/child::div[" + i + "]//a/div[@class='category-heading']"));
				textOfElement = element.getText();
				arraylist.add(textOfElement);
			}
		} catch (Exception e) {
			throw e;
		}

		return arraylist;
	}

	/***
	 * This will verify the headins of Car Category
	 * 
	 * @return
	 */
	public boolean verifyHeadingOfCarCategory(String expectedHeading1, String expectedHeading2, String expectedHeading3,
			String expectedHeading4, String expectedHeading5, String expectedHeading6, String expectedHeading7) {
		boolean flag = false;
		ArrayList<String> arraylist = null;
		try {
			arraylist = listOfHeadingOfCarCategory();
			if (arraylist.contains(expectedHeading1) && arraylist.contains(expectedHeading2)
					&& arraylist.contains(expectedHeading3) && arraylist.contains(expectedHeading4)
					&& arraylist.contains(expectedHeading5) && arraylist.contains(expectedHeading6)
					&& arraylist.contains(expectedHeading7)) {
				ExtentReportManager.logInfoDetails(String.format(TestBase.properties.getLogMessage("VerifyHeadingOfCarCategoryPassed"),
						arraylist, expectedHeading1, expectedHeading2, expectedHeading3, expectedHeading4,
						expectedHeading5, expectedHeading6, expectedHeading7));
				flag = true;
			}
		} catch (Exception e) {
			ExtentReportManager.logFailureDetails(String.format(TestBase.properties.getLogMessage("VerifyHeadingOfCarCategoryFailed"),
					arraylist, expectedHeading1, expectedHeading2, expectedHeading3, expectedHeading4, expectedHeading5,
					expectedHeading6, expectedHeading7));
			throw e;
		}
		return flag;
	}

	public boolean verifyHeadingOfSpinnyCarHubs(String expectedHeadingOfSpinnyCarHubs) throws Exception {
		boolean flag = false;
		String actualHeadingOfSpinnyCarHubs = "";
		try {
			SeleniumUtils.scrollToViewElement(driver, HEADING_SPINNY_CAR_HUBS);
			if (headingSpinnyCarHubs().isDisplayed()) {
				actualHeadingOfSpinnyCarHubs = objSeleniumUtils.getTextOfElement(driver, headingSpinnyCarHubs());
				if (actualHeadingOfSpinnyCarHubs.equalsIgnoreCase(expectedHeadingOfSpinnyCarHubs)) {
					ExtentReportManager.logInfoDetails(
							String.format(TestBase.properties.getLogMessage("VerifyHeadingOfSpinnyCarHubsPassed"),
									actualHeadingOfSpinnyCarHubs, expectedHeadingOfSpinnyCarHubs));
					flag = true;
				}
			}

		} catch (Exception e) {

			ExtentReportManager.logInfoDetails(String.format(TestBase.properties.getLogMessage("VerifyHeadingOfSpinnyCarHubsFailed"),
					actualHeadingOfSpinnyCarHubs, expectedHeadingOfSpinnyCarHubs));

			throw e;
		}
		return flag;
	}

	public boolean verifySubHeadingOfSpinnyCarHubs(String expectedSubHeadingOfSpinnyCarHubs) throws Exception {
		boolean flag = false;
		String actualSubHeadingOfSpinnyCarHubs = "";
		try {
			if (subHeadingSpinnyCarHubs().isDisplayed()) {
				actualSubHeadingOfSpinnyCarHubs = objSeleniumUtils.getTextOfElement(driver, subHeadingSpinnyCarHubs());
				if (actualSubHeadingOfSpinnyCarHubs.equalsIgnoreCase(expectedSubHeadingOfSpinnyCarHubs)) {
					ExtentReportManager.logInfoDetails(
							String.format(TestBase.properties.getLogMessage("VerifySubHeadingOfSpinnyCarHubsPassed"),
									actualSubHeadingOfSpinnyCarHubs, expectedSubHeadingOfSpinnyCarHubs));
					flag = true;
				}
			}

		} catch (Exception e) {

			ExtentReportManager.logFailureDetails(String.format(TestBase.properties.getLogMessage("VerifySubHeadingOfSpinnyCarHubsFailed"),
					actualSubHeadingOfSpinnyCarHubs, expectedSubHeadingOfSpinnyCarHubs));

			throw e;
		}
		return flag;
	}
		
	public boolean verifyHeadingOfSpinnyLoveStories(String expectedHeadingOfSpinnyLoveStories) throws Exception {
		boolean flag = false;
		String actualHeadingOfSpinnyLoveStories = "";
		try {
			SeleniumUtils.scrollToViewElement(driver, HEADING_SPINNY_LOVE_STORIES);
			if (headingSpinnyLoveStories().isDisplayed()) {
				actualHeadingOfSpinnyLoveStories = objSeleniumUtils.getTextOfElement(driver, headingSpinnyLoveStories());
				if (actualHeadingOfSpinnyLoveStories.equalsIgnoreCase(expectedHeadingOfSpinnyLoveStories)) {
					ExtentReportManager.logInfoDetails(
							String.format(TestBase.properties.getLogMessage("VerifyHeadingOfSpinnyLoveStoriesPassed"),
									actualHeadingOfSpinnyLoveStories, expectedHeadingOfSpinnyLoveStories));
					flag = true;
				}
			}

		} catch (Exception e) {

			ExtentReportManager.logInfoDetails(String.format(TestBase.properties.getLogMessage("VerifyHeadingOfSpinnyLoveStoriesFailed"),
					actualHeadingOfSpinnyLoveStories, expectedHeadingOfSpinnyLoveStories));

			throw e;
		}
		return flag;
	}

	public boolean verifySubHeadingOfSpinnyLoveStories(String expectedSubHeadingOfSpinnyLoveStories) throws Exception {
		boolean flag = false;
		String actualSubHeadingOfSpinnyLoveStories = "";
		try {
			if (subHeadingSpinnyLoveStories().isDisplayed()) {
				actualSubHeadingOfSpinnyLoveStories = objSeleniumUtils.getTextOfElement(driver, subHeadingSpinnyLoveStories());
				if (actualSubHeadingOfSpinnyLoveStories.equalsIgnoreCase(expectedSubHeadingOfSpinnyLoveStories)) {
					ExtentReportManager.logInfoDetails(
							String.format(TestBase.properties.getLogMessage("VerifySubHeadingOfSpinnyLoveStoriesPassed"),
									actualSubHeadingOfSpinnyLoveStories, expectedSubHeadingOfSpinnyLoveStories));
					flag = true;
				}
			}

		} catch (Exception e) {

			ExtentReportManager.logFailureDetails(String.format(TestBase.properties.getLogMessage("VerifySubHeadingOfSpinnyLoveStoriesFailed"),
					actualSubHeadingOfSpinnyLoveStories, expectedSubHeadingOfSpinnyLoveStories));

			throw e;
		}
		return flag;
	}
	

	public boolean verifySEOOfHomePageTest(String titleOfSpinny, String UniversalDropdownTitle, String titleBuyACar,
			String titleSellCar, String titleMore, String titleOfMobileNumber, String headingTitleOfBuyCarYouLove,
			String subHeadingTitleOfBuyCarYouLove, String headingSpinnyAssuredTitle,
			String subHeadingOfSpinnyAssuredTitle, String introducingTitle, String fixedPriceAussranceTitle,
			String keepingItSimpleTitle) throws Exception {
		boolean flag = false;
		try {
			if (verifyTitleOfHomePage(titleOfSpinny)) {
				if (verifySpinnyLogo()) {
					if (verifyUniversalDropdownTitle(UniversalDropdownTitle)) {
						if (verifyBuyACarTitle(titleBuyACar)) {
							if (verifySellCarTitle(titleSellCar)) {
								if (verifyMoreTitle(titleMore)) {
									if (verifyMobileNumberTitle(titleOfMobileNumber)) {
										if (verifyBuyCarYouLoveHeadingTitle(headingTitleOfBuyCarYouLove)) {
											if (verifyBuyCarYouLoveHSubHeadingTitle(subHeadingTitleOfBuyCarYouLove)) {
												if (verifySpinnyAssuredHeadingTitle(headingSpinnyAssuredTitle)) {
													if (verifySpinnyAssuredSubHeadingTitle(
															subHeadingOfSpinnyAssuredTitle)) {
														if (verifyContentUnderSpinnyAssuredSections(introducingTitle,
																fixedPriceAussranceTitle, keepingItSimpleTitle)) {
															flag = true;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			return flag;
		} catch (Exception e) {
			throw e;
		}
	}
}
