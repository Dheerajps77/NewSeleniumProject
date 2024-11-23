package com.investaSolutions.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

	private static final Logger log = LogManager.getLogger(ExtentReportManager.class);
	public static ExtentReports extentReports;
	public static ExtentTest test;
	public static String reportPath;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	public static ExtentReports createInstance(String reportName, String documentTitle) {
		if (extentReports == null) {
			synchronized (ExtentReportManager.class) {
				if (extentReports == null) {
					reportPath = System.getProperty("user.dir") + "/Execution Reports/HTML Report/"
							+ getReportNameWithTimeStamp();
					ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);
					extentSparkReporter.config().setReportName(reportName);
					extentSparkReporter.config().setDocumentTitle(documentTitle);
					extentSparkReporter.config().setTheme(Theme.DARK);
					extentSparkReporter.config().setEncoding("utf-8");
					extentReports = new ExtentReports();
					extentReports.attachReporter(extentSparkReporter);

					// Custom information
					log.info("Creating Extent Report with name: " + reportName);
					extentReports.setSystemInfo("Test Environment", "UAT");
					extentReports.setSystemInfo("Tester Name", "Dheeraj Singh");
					extentReports.setSystemInfo("Designation", "Senior Consultant");
					extentReports.setSystemInfo("Module Name", "User Management");

					// System and custom information
					extentReports.setSystemInfo("OS", System.getProperty("os.name"));
					extentReports.setSystemInfo("OS Version", System.getProperty("os.version"));
					extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
					extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
					extentReports.setSystemInfo("Java Vendor", System.getProperty("java.vendor"));
					extentReports.setSystemInfo("User Country", System.getProperty("user.country"));
					extentReports.setSystemInfo("User Language", System.getProperty("user.language"));
					extentReports.setSystemInfo("Java Version Date", System.getProperty("java.version.date"));
				}
			}
		}
		return extentReports;
	}

	public static void setSystemInfo(String key, String value) {
		if (extentReports != null) {
			extentReports.setSystemInfo(key, value);
			log.info("Set system info: " + key + " = " + value);
		}
	}

	public static String getReportNameWithTimeStamp() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		LocalDateTime localDateTime = LocalDateTime.now();
		String formattedTime = dateTimeFormatter.format(localDateTime);
		return "TestReport_" + formattedTime + ".html";
	}

	public static void startTest(String testName, String description) {
		test = extentReports.createTest(testName, description);
		setExtentTest(test);
		log.info("Test [" + testName + "] has been started");
	}

	public static void endTest(String testName) {
		if (getExtentTest() != null) {
			log.info("Test [" + testName + "] has been completed");
			getExtentTest().info("Test completed");
		} else {
			log.warn("ExtentTest is null while ending test: " + testName);
		}
		extentTest.remove(); // Remove the ExtentTest for this thread
	}

	public static void setTestCategory(String category) {
		if (getExtentTest() != null) {
			getExtentTest().assignCategory(category);
			log.info("Assigned category: " + category);
		}
	}

	public static void logPassDetails(String logGreen) {
		if (getExtentTest() != null) {
			getExtentTest().pass(MarkupHelper.createLabel(logGreen, ExtentColor.GREEN));
			log.info("PASS: " + logGreen);
		} else {
			log.error("ExtentTest is null. Cannot log pass details.");
		}
	}

	public static void logFailureDetails(String logRed) {
		if (getExtentTest() != null) {
			getExtentTest().fail(MarkupHelper.createLabel(logRed, ExtentColor.RED));
			log.error("FAILURE: " + log);
		} else {
			log.error("ExtentTest is null. Cannot log fail details.");
		}
	}

	public static void logSuccessDetails(String message) {
		if (getExtentTest() != null) {
			getExtentTest().log(Status.PASS, message);
			log.info("SUCCESS: " + message);
		} else {
			log.error("ExtentTest is null. Cannot log success details.");
		}
	}

	public static void logExceptionDetails(String logError, Throwable throwable) {
		if (getExtentTest() != null) {
			getExtentTest().fail(logError);
			getExtentTest().fail(throwable); // Logs the exception stack trace
			log.error("EXCEPTION: " + logError, throwable);
		} else {
			log.error("ExtentTest is null. Cannot log exception details.");
		}
	}

	public static void logInfoDetails(String logInfo) {
		if (getExtentTest() != null) {
			getExtentTest().info(MarkupHelper.createLabel(logInfo, ExtentColor.GREY));
			log.info("INFO: " + log);
		} else {
			log.error("ExtentTest is null. Cannot log info details.");
		}
	}

	public static void logWarningDetails(String logWarn) {
		if (getExtentTest() != null) {
			getExtentTest().warning(MarkupHelper.createLabel(logWarn, ExtentColor.YELLOW));
			log.warn("WARNING: " + log);
		} else {
			log.error("ExtentTest is null. Cannot log warning details.");
		}
	}

	// Getter for ExtentTest
	public static ExtentTest getExtentTest() {
		return extentTest.get();
	}

	// Setter for ExtentTest
	public static void setExtentTest(ExtentTest test) {
		extentTest.set(test);
	}

	// Additional methods to get test status and messages
	public static void setTestStatus(Status status) {
		if (extentTest.get() != null) {
			extentTest.get().log(status, "Test status: " + status.toString());
			log.info("Test status: " + status.toString());
		}
	}

	public static void flushReports() {
		if (extentReports != null) {
			extentReports.flush();
			log.info("Extent reports flushed.");
		}
	}

	public static void addScreenshotToReport(String screenshotPath) {
		try {
			getExtentTest().addScreenCaptureFromPath(screenshotPath); // This attaches the screenshot to the report
			log.info("Screenshot added to Extent Report: " + screenshotPath);
		} catch (Exception e) {
			log.error("Error while adding screenshot to Extent Report: " + e.getMessage());
		}
	}

}
