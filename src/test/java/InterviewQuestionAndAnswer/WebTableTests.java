package InterviewQuestionAndAnswer;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.investaSolutions.base.TestBase;
import com.investaSolutions.utils.ExtentReportManager;
import com.investaSolutions.utils.SeleniumUtils;

public class WebTableTests extends TestBase {

	By COUNTRY_NAME = By.xpath("//div[@class='entry-content']//table[@id='countries']//tbody/tr/td[2]/strong");
	String userSpecificCountryName = "Australia";
	SeleniumUtils seleniumUtils = new SeleniumUtils();

	public List<WebElement> getListOfElement(By by) {
		return driver.findElements(by);
	}

	public int sizeofList(List<WebElement> lists) {
		return lists.size();
	}

	@BeforeClass(alwaysRun = true)
	@Parameters("browser")
	public void setUp(String browser) throws MalformedURLException {
		// Set the base URL specifically for this test class
		setupURL("WEBTABLE_URL"); // Retrieve the URL from properties
		/*
		 * driver.get(baseUrl); // Navigate to the URL
		 */	}

	@Test
	public void clickOnCheckBoxBasisOnCountrySearch() {
		String testName = "Test Method 1";
		String description = "Verifying country-specific checkbox selection in the web table.";

		// Start the test in ExtentReport
		ExtentReportManager.startTest(testName, description);

		try {
			List<WebElement> listOfWeb = getListOfElement(COUNTRY_NAME);
			int number = sizeofList(listOfWeb);

			for (int i = 2; i < number; i++) {
				SeleniumUtils.waitForElementClickable(driver, By.xpath(
						"//div[@class='entry-content']//table[@id='countries']//tbody/tr[" + i + "]/td[2]/strong"),
						WAIT_SECONDS);

				SeleniumUtils.scrollToViewElement(driver, By.xpath(
						"//div[@class='entry-content']//table[@id='countries']//tbody/tr[" + i + "]/td[2]/strong"));

				String countryName = driver.findElement(By.xpath(
						"//div[@class='entry-content']//table[@id='countries']//tbody/tr[" + i + "]/td[2]/strong"))
						.getText();

				if (userSpecificCountryName.equalsIgnoreCase(countryName)) {
					WebElement checkBoxElement = driver
							.findElement(By.xpath("//div[@class='entry-content']//table[@id='countries']//tbody/tr[" + i
									+ "]/td[1]//input[@type='checkbox']"));

					checkBoxElement.click();
					log.info("Checkbox clicked for country: " + countryName);
					ExtentReportManager.logInfoDetails("Checkbox clicked for country: " + countryName);
					break; // Exit loop once the checkbox is clicked for the target country
				}
			}

			ExtentReportManager.logSuccessDetails(testName + ": Test Case Passed");
		} catch (Exception e) {
			ExtentReportManager.logExceptionDetails(testName + ": An error occurred while selecting checkbox", e);
			log.error("Error in test execution: ", e);
		} finally {
			ExtentReportManager.endTest(testName); // End the test in ExtentReport
		}
	}
}
