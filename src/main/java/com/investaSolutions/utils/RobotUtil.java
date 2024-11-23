package com.investaSolutions.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class RobotUtil {
    private Robot robot;

    // Constructor to initialize Robot instance
    public RobotUtil() {
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            System.err.println("Failed to initialize Robot: " + e.getMessage());
        }
    }

    // Press a key
    public void pressKey(int keyCode) {
        try {
            robot.keyPress(keyCode);
            System.out.println("Pressed key: " + KeyEvent.getKeyText(keyCode));
        } catch (Exception e) {
            System.err.println("Failed to press key: " + KeyEvent.getKeyText(keyCode));
            e.printStackTrace();
        }
    }

    // Release a key
    public void releaseKey(int keyCode) {
        try {
            robot.keyRelease(keyCode);
            System.out.println("Released key: " + KeyEvent.getKeyText(keyCode));
        } catch (Exception e) {
            System.err.println("Failed to release key: " + KeyEvent.getKeyText(keyCode));
            e.printStackTrace();
        }
    }

    // Type a character (press and release)
    public void typeCharacter(int keyCode) {
        pressKey(keyCode);
        releaseKey(keyCode);
    }

    // Type a string (sequential typing of characters)
    public void typeString(String text) {
        try {
            for (char c : text.toCharArray()) {
                int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                if (Character.isUpperCase(c)) {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                }
                pressKey(keyCode);
                releaseKey(keyCode);
                if (Character.isUpperCase(c)) {
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }
            }
            System.out.println("Typed string: " + text);
        } catch (Exception e) {
            System.err.println("Failed to type string: " + text);
            e.printStackTrace();
        }
    }

    // Simulate copy operation (Ctrl + C)
    public void copy() {
        try {
            robot.keyPress(KeyEvent.VK_CONTROL);
            pressKey(KeyEvent.VK_C);
            releaseKey(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            System.out.println("Performed copy operation (Ctrl + C)");
        } catch (Exception e) {
            System.err.println("Failed to perform copy operation");
            e.printStackTrace();
        }
    }

    // Simulate paste operation (Ctrl + V)
    public void paste() {
        try {
            robot.keyPress(KeyEvent.VK_CONTROL);
            pressKey(KeyEvent.VK_V);
            releaseKey(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            System.out.println("Performed paste operation (Ctrl + V)");
        } catch (Exception e) {
            System.err.println("Failed to perform paste operation");
            e.printStackTrace();
        }
    }

    // Simulate cut operation (Ctrl + X)
    public void cut() {
        try {
            robot.keyPress(KeyEvent.VK_CONTROL);
            pressKey(KeyEvent.VK_X);
            releaseKey(KeyEvent.VK_X);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            System.out.println("Performed cut operation (Ctrl + X)");
        } catch (Exception e) {
            System.err.println("Failed to perform cut operation");
            e.printStackTrace();
        }
    }

    // Move the mouse to specified screen coordinates
    public void moveMouse(int x, int y) {
        try {
            robot.mouseMove(x, y);
            System.out.println("Moved mouse to coordinates: (" + x + ", " + y + ")");
        } catch (Exception e) {
            System.err.println("Failed to move mouse to coordinates: (" + x + ", " + y + ")");
            e.printStackTrace();
        }
    }

    // Left-click the mouse
    public void leftClick() {
        try {
            robot.mousePress(java.awt.event.InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(java.awt.event.InputEvent.BUTTON1_DOWN_MASK);
            System.out.println("Performed left click");
        } catch (Exception e) {
            System.err.println("Failed to perform left click");
            e.printStackTrace();
        }
    }

    // Right-click the mouse
    public void rightClick() {
        try {
            robot.mousePress(java.awt.event.InputEvent.BUTTON3_DOWN_MASK);
            robot.mouseRelease(java.awt.event.InputEvent.BUTTON3_DOWN_MASK);
            System.out.println("Performed right click");
        } catch (Exception e) {
            System.err.println("Failed to perform right click");
            e.printStackTrace();
        }
    }

    // Scroll the mouse wheel
    public void scrollMouse(int wheelAmount) {
        try {
            robot.mouseWheel(wheelAmount);
            System.out.println("Scrolled mouse wheel by amount: " + wheelAmount);
        } catch (Exception e) {
            System.err.println("Failed to scroll mouse wheel");
            e.printStackTrace();
        }
    }

    // Press and hold a key
    public void pressAndHoldKey(int keyCode) {
        try {
            pressKey(keyCode);
            System.out.println("Held key down: " + KeyEvent.getKeyText(keyCode));
        } catch (Exception e) {
            System.err.println("Failed to hold key down: " + KeyEvent.getKeyText(keyCode));
            e.printStackTrace();
        }
    }

    // Release a held key
    public void releaseHeldKey(int keyCode) {
        try {
            releaseKey(keyCode);
            System.out.println("Released held key: " + KeyEvent.getKeyText(keyCode));
        } catch (Exception e) {
            System.err.println("Failed to release held key: " + KeyEvent.getKeyText(keyCode));
            e.printStackTrace();
        }
    }

    // Delay between actions
    public void addDelay(int milliseconds) {
        try {
            robot.delay(milliseconds);
            System.out.println("Added delay of " + milliseconds + " milliseconds");
        } catch (Exception e) {
            System.err.println("Failed to add delay");
            e.printStackTrace();
        }
    }
}
