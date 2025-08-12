package com.investaSolutions.utils;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CookieUtils {

    private WebDriver driver;

    public CookieUtils(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Add cookie
    public void addCookie(String name, String value) {
        Cookie cookie = new Cookie.Builder(name, value)
                .domain(getDomain()) // Optional, based on need
                .isHttpOnly(true)
                .isSecure(false)
                .build();
        driver.manage().addCookie(cookie);
    }

    // ✅ Get all cookies
    public Set<Cookie> getAllCookies() {
        return driver.manage().getCookies();
    }

    // ✅ Get specific cookie by name
    public Cookie getCookieByName(String name) {
        return driver.manage().getCookieNamed(name);
    }

    // ✅ Delete specific cookie by name
    public void deleteCookieByName(String name) {
        driver.manage().deleteCookieNamed(name);
    }

    // ✅ Delete specific cookie by object
    public void deleteCookie(Cookie cookie) {
        driver.manage().deleteCookie(cookie);
    }

    // ✅ Delete all cookies
    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    // Optional method to get domain (from current URL)
    private String getDomain() {
        String url = driver.getCurrentUrl();
        return url.replace("https://", "")
                  .replace("http://", "")
                  .split("/")[0];
    }
    
    public static void main(String[] args) {
		
    	WebDriver driver = new ChromeDriver();
    	driver.get("https://example.com");

    	CookieUtils cookieUtils = new CookieUtils(driver);

    	// Add a cookie
    	cookieUtils.addCookie("session_id", "123456");

    	// Get and print all cookies
    	Set<Cookie> allCookies = cookieUtils.getAllCookies();
    	for (Cookie cookie : allCookies) {
    	    System.out.println(cookie.getName() + ": " + cookie.getValue());
    	}

    	// Get specific cookie
    	Cookie sessionCookie = cookieUtils.getCookieByName("session_id");
    	System.out.println("Session Cookie: " + sessionCookie);

    	// Delete a cookie
    	cookieUtils.deleteCookieByName("session_id");

    	// Clear all cookies
    	cookieUtils.deleteAllCookies();
	
	}
}
