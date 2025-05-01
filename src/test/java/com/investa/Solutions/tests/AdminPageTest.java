package com.investa.Solutions.tests;

import java.net.MalformedURLException;

import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.investaSolutions.base.TestBase;
import com.investaSolutions.pages.AdminAMManagementPage;

public class AdminPageTest extends TestBase {

	@BeforeClass(alwaysRun = true)
	@Parameters("browser")
	public void setUp(String browser) throws MalformedURLException {
		// Set the base URL specifically for this test class
		setupURL("AMAZON_URL"); // Pass the URL key to the setupURL method
	}

	@Test
	public void testAdminConsole() {
		AdminAMManagementPage adminAMManagementPage;
		try {
			adminAMManagementPage = pageManager.getAdminAMManagementPage();
			adminAMManagementPage.addressColumnText();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
