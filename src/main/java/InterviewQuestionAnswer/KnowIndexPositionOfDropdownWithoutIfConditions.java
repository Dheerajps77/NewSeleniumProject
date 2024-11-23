package InterviewQuestionAnswer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class KnowIndexPositionOfDropdownWithoutIfConditions {

	/*
	 * To handle a dropdown with duplicate values and find the index of each
	 * occurrence without using an explicit if condition, you can use a loop and a
	 * Map to track the occurrences of each value as you iterate through the
	 * dropdown options. Here's how you can achieve this in Java:
	 */

	public static void usingMap() {
		// Set up WebDriver
		System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
		WebDriver driver = new ChromeDriver();

		// Navigate to the webpage
		driver.get("URL_OF_YOUR_WEBPAGE");

		// Locate the dropdown element
		WebElement dropdownElement = driver.findElement(By.id("dropdown_id")); // Change to your dropdown locator

		// Create a Select object
		Select dropdown = new Select(dropdownElement);

		// Get all options in the dropdown
		List<WebElement> options = dropdown.getOptions();

		// Map to store occurrences of each value
		Map<String, Integer> valueOccurrences = new HashMap<>();

		// Loop through options and print the index and occurrence of each value
		for (int index = 0; index < options.size(); index++) {
			String value = options.get(index).getAttribute("value");

			int currentCount = valueOccurrences.getOrDefault(value, 0); // Get the current count, default to 0 if not
																		// present
			int occurrence = currentCount + 1; // Increment the count
			valueOccurrences.put(value, occurrence); // Update the map with the new count

			// Print the index and occurrence
			System.out.println("Index: " + index + ", Value: " + value + ", Occurrence: " + occurrence);
		}

		// Close the driver
		driver.quit();
	}
	
	public static void usingWithoutMap() {
		 // Set up the WebDriver
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
        WebDriver driver = new ChromeDriver();

        // Navigate to the webpage
        driver.get("URL_OF_YOUR_WEBPAGE");

        // Locate the dropdown element
        WebElement dropdownElement = driver.findElement(By.id("dropdown_id")); // Change to your dropdown locator

        // Create a Select object
        Select dropdowns = new Select(dropdownElement);
        List<WebElement> dropdown = dropdowns.getOptions();

        // Get all options
        for (int i = 0; i < dropdown.size(); i++) {
            WebElement option = dropdown.get(i);
            System.out.println("Index: " + i + ", Value: " + option.getAttribute("value") + ", Text: " + option.getText());
        }

        // Select an option by index (example: select the second option)
        dropdowns.selectByIndex(1);

        // Close the browser
        driver.quit();
    }

	public static void main(String[] args) {

	}
}
