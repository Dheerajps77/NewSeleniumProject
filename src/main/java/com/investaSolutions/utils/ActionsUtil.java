package com.investaSolutions.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;

public class ActionsUtil {
    private WebDriver driver;
    private Actions actions;

    // Constructor to initialize Actions
    public ActionsUtil(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    // Click on an element
    public void click(WebElement element) {
        try {
            actions.moveToElement(element).click().perform();
            System.out.println("Clicked on the element: " + element);
        } catch (Exception e) {
            System.err.println("Failed to click on the element: " + element);
            e.printStackTrace();
        }
    }

    // Right-click (context click) on an element
    public void rightClick(WebElement element) {
        try {
            actions.contextClick(element).perform();
            System.out.println("Right-clicked on the element: " + element);
        } catch (Exception e) {
            System.err.println("Failed to right-click on the element: " + element);
            e.printStackTrace();
        }
    }

    // Double-click on an element
    public void doubleClick(WebElement element) {
        try {
            actions.doubleClick(element).perform();
            System.out.println("Double-clicked on the element: " + element);
        } catch (Exception e) {
            System.err.println("Failed to double-click on the element: " + element);
            e.printStackTrace();
        }
    }

    // Move to an element
    public void moveToElement(WebElement element) {
        try {
            actions.moveToElement(element).perform();
            System.out.println("Moved to the element: " + element);
        } catch (Exception e) {
            System.err.println("Failed to move to the element: " + element);
            e.printStackTrace();
        }
    }

    // Drag and drop from source element to target element
    public void dragAndDrop(WebElement source, WebElement target) {
        try {
            actions.dragAndDrop(source, target).perform();
            System.out.println("Dragged element from " + source + " to " + target);
        } catch (Exception e) {
            System.err.println("Failed to drag and drop from " + source + " to " + target);
            e.printStackTrace();
        }
    }

    // Drag and drop by offset
    public void dragAndDropByOffset(WebElement source, int xOffset, int yOffset) {
        try {
            actions.dragAndDropBy(source, xOffset, yOffset).perform();
            System.out.println("Dragged element by offset x: " + xOffset + ", y: " + yOffset);
        } catch (Exception e) {
            System.err.println("Failed to drag element by offset");
            e.printStackTrace();
        }
    }

    // Send keys to an element
    public void sendKeys(WebElement element, String keysToSend) {
        try {
            actions.moveToElement(element).click().sendKeys(keysToSend).perform();
            System.out.println("Sent keys to the element: " + element);
        } catch (Exception e) {
            System.err.println("Failed to send keys to the element: " + element);
            e.printStackTrace();
        }
    }

    // Key down and key up (for holding and releasing a key)
    public void keyDownAndUp(Keys key) {
        try {
            actions.keyDown(key).pause(1000).keyUp(key).perform();
            System.out.println("Pressed and released key: " + key);
        } catch (Exception e) {
            System.err.println("Failed to press and release key: " + key);
            e.printStackTrace();
        }
    }

    // Click and hold an element
    public void clickAndHold(WebElement element) {
        try {
            actions.clickAndHold(element).perform();
            System.out.println("Clicked and held the element: " + element);
        } catch (Exception e) {
            System.err.println("Failed to click and hold the element: " + element);
            e.printStackTrace();
        }
    }

    // Release an element (for mouse interactions)
    public void release(WebElement element) {
        try {
            actions.release(element).perform();
            System.out.println("Released the element: " + element);
        } catch (Exception e) {
            System.err.println("Failed to release the element: " + element);
            e.printStackTrace();
        }
    }

    // Hover over an element
    public void hoverOverElement(WebElement element) {
        try {
            actions.moveToElement(element).perform();
            System.out.println("Hovered over the element: " + element);
        } catch (Exception e) {
            System.err.println("Failed to hover over the element: " + element);
            e.printStackTrace();
        }
    }

    // Scroll to an element
    public void scrollToElement(WebElement element) {
        try {
            actions.moveToElement(element).perform();
            System.out.println("Scrolled to the element: " + element);
        } catch (Exception e) {
            System.err.println("Failed to scroll to the element: " + element);
            e.printStackTrace();
        }
    }

    // Composite actions: Example of performing multiple actions in sequence
    public void performCompositeActions(WebElement element1, WebElement element2) {
        try {
            actions.moveToElement(element1)
                    .click()
                    .moveToElement(element2)
                    .doubleClick()
                    .perform();
            System.out.println("Performed composite actions on elements");
        } catch (Exception e) {
            System.err.println("Failed to perform composite actions");
            e.printStackTrace();
        }
    }
}
