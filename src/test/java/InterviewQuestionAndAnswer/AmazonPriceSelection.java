package InterviewQuestionAndAnswer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.investaSolutions.base.SingletonWebDriver;
import com.investaSolutions.base.TestBase;
import com.investaSolutions.utils.ExtentReportManager;
import com.investaSolutions.utils.SeleniumUtils;

import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

public class AmazonPriceSelection extends TestBase {
	
	@BeforeClass(alwaysRun = true)
	@Parameters("browser")
	public void setUp(String browser) throws MalformedURLException {
		// Set the base URL specifically for this test class
        setupURL("AMAZON_URL"); // Pass the URL key to the setupURL method  
	}
	
	@Test
	public void clickOnCheckBoxBasisOnCountrySearch() {
		// Search for the product
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("mobile phones");
        driver.findElement(By.id("nav-search-submit-button")).click();
        
        // Wait until results are loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.a-price")));
        
        // Find all price elements
        List<WebElement> prices = driver.findElements(By.cssSelector("span.a-price-whole"));
        
        // Logic for selecting the highest or lowest priced product
        selectProductWithPrice(driver, prices, "highest"); // Can also pass "lowest"
        
        // Close the browser
        driver.quit();
	}

    // Method to select the highest or lowest priced product
    public static void selectProductWithPrice(WebDriver driver, List<WebElement> priceElements, String option) {
        int extremePrice = (option.equals("highest")) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        WebElement desiredProduct = null;
        
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace(",", "").trim();
            
            try {
                int price = Integer.parseInt(priceText);
                
                if (option.equals("highest") && price > extremePrice) {
                    extremePrice = price;
                    desiredProduct = priceElement;
                } else if (option.equals("lowest") && price < extremePrice) {
                    extremePrice = price;
                    desiredProduct = priceElement;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format: " + priceText);
            }
        }
        
        if (desiredProduct != null) {
            // Scroll to the product and click
            desiredProduct.click();
        } else {
            System.out.println("No product found with the specified price criteria.");
        }
    }
}
