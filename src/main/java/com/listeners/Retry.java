package com.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private int count = 0;
    private static final int maxTry = 3;  // Maximum retry attempts

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (count < maxTry) {   // Retry the test if maxTry count is not reached
            count++;            // Increment retry count
            return true;        // Return true to retry the test
        }
        return false;           // Return false to not retry
    }
}
