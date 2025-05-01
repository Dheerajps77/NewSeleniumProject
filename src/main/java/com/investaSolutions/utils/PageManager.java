package com.investaSolutions.utils;

import org.openqa.selenium.WebDriver;

import com.investaSolutions.pages.AdminAMManagementPage;
import com.investaSolutions.pages.AdminBankManagementPage;
import com.investaSolutions.pages.AdminBenchmarkManagementPage;
import com.investaSolutions.pages.AdminInstrumentManagementPage;

public class PageManager {
	private WebDriver driver;

	private AdminAMManagementPage adminAMManagementPage;
	private AdminBankManagementPage adminBankManagementPage;
	private AdminBenchmarkManagementPage adminBenchmarkManagementPage;
	private AdminInstrumentManagementPage adminInstrumentManagementPage;

	public PageManager(WebDriver driver) {
		this.driver = driver;
	}

	public AdminAMManagementPage getAdminAMManagementPage() {
		if (adminAMManagementPage == null) {
			adminAMManagementPage = new AdminAMManagementPage(driver);
		}
		return adminAMManagementPage;
	}

	public AdminBankManagementPage getAdminBankManagementPage() {
		if (adminBankManagementPage == null) {
			adminBankManagementPage = new AdminBankManagementPage(driver);
		}
		return adminBankManagementPage;
	}

	public AdminBenchmarkManagementPage getAdminBenchmarkManagementPage() {
		if (adminBenchmarkManagementPage == null) {
			adminBenchmarkManagementPage = new AdminBenchmarkManagementPage(driver);
		}
		return adminBenchmarkManagementPage;
	}

	public AdminInstrumentManagementPage getAdminInstrumentManagementPage() {
		if (adminInstrumentManagementPage == null) {
			adminInstrumentManagementPage = new AdminInstrumentManagementPage(driver);
		}
		return adminInstrumentManagementPage;
	}
}
