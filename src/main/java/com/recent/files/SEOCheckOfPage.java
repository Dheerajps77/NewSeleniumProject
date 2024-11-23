package com.recent.files;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.investaSolutions.base.TestBase;
import com.investaSolutions.utils.GenericUtils;
import com.investaSolutions.utils.PropertiesManager;
import com.investaSolutions.utils.ExtentReportManager;
import com.investaSolutions.utils.SeleniumUtils;

public class SEOCheckOfPage {

	WebDriver driver;
	HashMap<String, ArrayList<String>> map;
	public static PropertiesManager properties = PropertiesManager.getInstance();
	public static final By LINKS = By.tagName("a");
	SeleniumUtils objSeleniumUtils = new SeleniumUtils();
	GenericUtils objGenericUtils = new GenericUtils();
	static int i = 0;
	String initalCharCount = properties.getConstant("STARTCOUNT_CHARACTERS");
	String endCharCount = properties.getConstant("ENDCOUNT_CHARACTERS");
	String startCharCountOfMetaDesc = properties.getConstant("STARTCOUNT_DESCRIPTION_CHARACTERS");
	String endCharCountOfMetaDesc = properties.getConstant("ENDCOUNT_DESCRIPTION_CHARACTERS");
	String nameAttribute = properties.getConstant("ATTRIBUTE_NAME");
	String contentAttribute = properties.getConstant("ATTRIBUTE_CONTENT");
	String propertyAttribute = properties.getConstant("ATTRIBUTE_PROPERTY");
	String metaRobotAttirbuteName = properties.getConstant("ATTRIBUTE_ROBOTS");
	String indexFollowValueOfRobotsAttributeName = properties.getConstant("INDEX_VALUE_OF_ROBOTS");
	String keywordsAttirbuteName = properties.getConstant("KEYWORDS");
	String ALTAttirbuteName = properties.getConstant("ALT");
	String descritpionAttirbuteName = properties.getConstant("DESCRIPTION");

	public SEOCheckOfPage(WebDriver driver) {
		this.driver = driver;
	}

	public void sEOCheckerOfPage(String pageURL, String h1, String h2) throws Exception {
		try {
			String[] urlListArray = pageURL.split(",");
			for (String url : urlListArray) {
				if (!url.contains("https://webtest.myspinny.com/buy-used-cars/") || url.equalsIgnoreCase(
						"https://webtest.myspinny.com/buy-used-cars/bangalore/hyundai/grand-i10/sportz-12-kappa-vtvt-whitefield-2016/95685/")) {
					verifySEOOfPageData(url, h1, h2);
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public void verifyALTAttributeOfImg() throws InterruptedException {
		List<WebElement> elements;
		String strAlt = "alt";
		String src = "";
		String altAttributeValue = "";
		ArrayList<String> ALTArrayList = new ArrayList<String>();
		Set<String> set = new HashSet<String>();
		ArrayList<String> altValues = new ArrayList<String>();
		boolean flag = false;
		try {
			elements = driver.findElements(By.tagName("img"));
			int totalImgTagCount = elements.size();
			for (int i = 0; i < totalImgTagCount; i++) {
				elements = driver.findElements(By.tagName("img"));
				src = elements.get(i).getAttribute("src");
				altAttributeValue = elements.get(i).getAttribute(ALTAttirbuteName);
				WebElement el = elements.get(i);
				if ((el.isEnabled()) || (el.isDisplayed())) {
					altAttributeValue = elements.get(i).getAttribute(strAlt);
					altValues.add(altAttributeValue);
					if (ALTAttirbuteName.equalsIgnoreCase(strAlt)) {
						flag = true;
					} else {
						flag = false;
					}
				}
			}
			if (flag) {
				ExtentReportManager.logInfoDetails(String
						.format(TestBase.properties.getLogMessage("VerifyImgALTAttributePassed"), strAlt, altValues));
				/*
				 * ALTArrayList.add("Alt Tag"); ALTArrayList.add(strAlt);
				 * ALTArrayList.add("Not Missing"); map.put("Alt Tag", ALTArrayList);
				 */
			} else {
				ExtentReportManager.logInfoDetails(String
						.format(TestBase.properties.getLogMessage("VerifyImgALTAttributeFailed"), strAlt, altValues));
				ALTArrayList.add("Alt Tag");
				ALTArrayList.add(strAlt);
				ALTArrayList.add("Missing");
				map.put("Alt Tag", ALTArrayList);
			}
		}

		catch (StaleElementReferenceException e) {
			driver.navigate().refresh();
			Thread.sleep(1000);
			verifyALTAttributeOfImg();
		} catch (Exception e) {
			throw e;
		}
	}

	/***
	 * This will verify the title of page between 30-60 characters
	 * 
	 * @return
	 * 
	 */
	public void verifyLengthOfTitleOfPage() {
		int totalNumberOfChars = 0;
		int startCountOfCharNumber = 0;
		int endCountOfCharNumber = 0;
		String titleOfPage = null;
		ArrayList<String> titleOfArrayList = new ArrayList<String>();
		try {
			startCountOfCharNumber = objGenericUtils.convertStringToInt(initalCharCount);
			endCountOfCharNumber = objGenericUtils.convertStringToInt(endCharCount);
			titleOfPage = driver.getTitle();
			totalNumberOfChars = objGenericUtils.getTotalNumbersOfCharacters(titleOfPage);

			if (titleOfPage == null || titleOfPage.isEmpty()) {
				titleOfArrayList.add("Title");
				titleOfArrayList.add(titleOfPage);
				titleOfArrayList.add("Missing");
				map.put("Title", titleOfArrayList);

			} else if (!(totalNumberOfChars >= startCountOfCharNumber && totalNumberOfChars <= endCountOfCharNumber)) {
				ExtentReportManager
						.logInfoDetails(String.format(properties.getLogMessage("VerifyTheLengthOfHomePageTitleFailed"),
								titleOfPage, totalNumberOfChars, startCountOfCharNumber, endCountOfCharNumber));
				titleOfArrayList.add("Title");
				titleOfArrayList.add(titleOfPage);
				titleOfArrayList.add("Length is not > 30 and < 60 Characters");
				map.put("Title", titleOfArrayList);
			} else if (titleOfPage != null || !(titleOfPage.isEmpty())) {
				ExtentReportManager
						.logInfoDetails(String.format(properties.getLogMessage("VerifyTheLengthOfHomePageTitlePassed"),
								titleOfPage, totalNumberOfChars, startCountOfCharNumber, endCountOfCharNumber));
				/*
				 * titleOfArrayList.add("Title"); titleOfArrayList.add(titleOfPage);
				 * titleOfArrayList.add("Not Missing, Length is > 30 and < 60 Characters");
				 * map.put("Title", titleOfArrayList);
				 */
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/***
	 * This will verify meta keyword missing in a page
	 */
	public void verifyKeywordOfMetaDataOfage() {
		List<WebElement> metaElements;
		int totalCount;
		try {
			metaElements = driver.findElements(By.tagName("meta"));
			totalCount = metaElements.size();
			for (int i = 0; i < totalCount; i++) {
				{
					if (!(metaElements.get(i).isEnabled())) {
						ExtentReportManager.logFailureDetails(
								String.format(TestBase.properties.getLogMessage("VerifyMetaDataKeywordDisplayFailed")));
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/***
	 * This will verify is Keyword is missing in the page or not
	 */
	public void verifyMetaKeyWordsIsMissingOfPage() {
		List<WebElement> metaElements;
		String nameAttributeValue = "";
		String contentAttributeValue = "";
		int totalCountOfDescription = 0;
		int totalCount;
		String keyWordsMissing = "";
		ArrayList<String> keywordsArrayList = new ArrayList<String>();
		String keyWordDesc = "";
		keyWordsMissing = "missing";
		boolean flag = false;
		try {
			metaElements = driver.findElements(By.tagName("meta"));
			totalCount = metaElements.size();
			for (int i = 0; i < totalCount; i++) {
				nameAttributeValue = metaElements.get(i).getAttribute(nameAttribute);
				contentAttributeValue = metaElements.get(i).getAttribute(contentAttribute);
				totalCountOfDescription = objGenericUtils.getTotalNumbersOfCharacters(contentAttributeValue);

				if (metaElements.get(i).isEnabled()) {
					if (nameAttributeValue.equalsIgnoreCase(keywordsAttirbuteName)) {
						keyWordDesc = contentAttributeValue;
						if ((contentAttributeValue == null || contentAttributeValue.isEmpty())) {
							ExtentReportManager.logInfoDetails(String.format(
									properties.getLogMessage("VerifyKeywordsMissingFailed"), nameAttributeValue,
									totalCountOfDescription, contentAttributeValue, keyWordsMissing));
						} else {
							ExtentReportManager.logInfoDetails(String.format(
									properties.getLogMessage("VerifyKeywordsMissingPassed"), nameAttributeValue,
									totalCountOfDescription, contentAttributeValue, keyWordsMissing));
							flag = true;
						}
					}
				} else {
					ExtentReportManager
							.logInfoDetails(String.format(properties.getLogMessage("VerifyMetaElementVisibility")));
				}
			}
			if (flag) {
				/*
				 * keywordsArrayList.add("Keywords"); keywordsArrayList.add(keyWordDesc);
				 * keywordsArrayList.add("Not Missing"); map.put("Keywords", keywordsArrayList);
				 */
			}

			else {
				keywordsArrayList.add("Keywords");
				keywordsArrayList.add(keyWordDesc);
				keywordsArrayList.add("Missing");
				map.put("Keywords", keywordsArrayList);
			}

		} catch (Exception e) {
			throw e;
		}
	}

	/***
	 * This will verify the meta data description value between 50-160 characters
	 * 
	 * @throws Exception
	 */
	public void verifyMetaDescriptionLengthOfPage() throws Exception {
		List<WebElement> metaElements;
		String nameAttributeValue = "";
		String contentAttributeValue = "";
		int totalCountOfDescription = 0;
		int startCountOfCharNumber = 0;
		int endCountOfCharNumber = 0;
		startCountOfCharNumber = objGenericUtils.convertStringToInt(startCharCountOfMetaDesc);
		endCountOfCharNumber = objGenericUtils.convertStringToInt(endCharCountOfMetaDesc);
		int totalCount;
		String DescriptionValue = "";
		ArrayList<String> descriptionArrayList = new ArrayList<String>();
		boolean flag = false;
		try {
			metaElements = driver.findElements(By.tagName("meta"));
			totalCount = metaElements.size();
			for (int i = 0; i < totalCount; i++) {
				nameAttributeValue = metaElements.get(i).getAttribute(nameAttribute);
				contentAttributeValue = metaElements.get(i).getAttribute(contentAttribute);
				totalCountOfDescription = objGenericUtils.getTotalNumbersOfCharacters(contentAttributeValue);
				if (metaElements.get(i).isEnabled()) {
					if (nameAttributeValue.equalsIgnoreCase(descritpionAttirbuteName)) {
						DescriptionValue = contentAttributeValue;
						if ((contentAttributeValue == null && contentAttributeValue.isEmpty())) {
							descriptionArrayList.add("Descrptions");
							descriptionArrayList.add(DescriptionValue);
							descriptionArrayList.add("Missing");
							map.put("Descrptions", descriptionArrayList);
						} else if (!(totalCountOfDescription >= startCountOfCharNumber
								&& totalCountOfDescription <= endCountOfCharNumber)) {
							ExtentReportManager.logInfoDetails(
									String.format(properties.getLogMessage("VerifyDescriptionContentsAndLengthFailed"),
											nameAttributeValue, contentAttributeValue, startCountOfCharNumber,
											endCountOfCharNumber));
							descriptionArrayList.add("Descrptions");
							descriptionArrayList.add(DescriptionValue);
							descriptionArrayList.add("Length is not > 50 and < 160 Characters");
							map.put("Descrptions", descriptionArrayList);
						} else if (totalCountOfDescription >= startCountOfCharNumber
								&& totalCountOfDescription <= endCountOfCharNumber) {
							ExtentReportManager.logInfoDetails(
									String.format(properties.getLogMessage("VerifyDescriptionContentsAndLengthPassed"),
											nameAttributeValue, contentAttributeValue, startCountOfCharNumber,
											endCountOfCharNumber));
						}
					}
				} else {
					ExtentReportManager
							.logInfoDetails(String.format(properties.getLogMessage("VerifyMetaElementVisibility")));
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public ArrayList<String> getH1Heading(String h1) {
		String h1StringValue = "";
		ArrayList<String> h1ArrayList = new ArrayList<String>();
		try {
			List<WebElement> H1WebElements = driver.findElements(By.tagName(h1));
			for (int i = 0; i < H1WebElements.size(); i++) {
				WebElement element = H1WebElements.get(i);
				if (element.isDisplayed()) {
					h1StringValue = element.getText();
					h1ArrayList.add(h1StringValue);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return h1ArrayList;
	}

	public ArrayList<String> getH2Heading(String h2) {
		String h2StringValue = "";
		ArrayList<String> h2ArrayList = new ArrayList<String>();
		try {
			List<WebElement> H2WebElements = driver.findElements(By.tagName(h2));
			for (int i = 0; i < H2WebElements.size(); i++) {
				WebElement element = H2WebElements.get(i);
				if (element.isDisplayed()) {
					h2StringValue = element.getText();
					h2ArrayList.add(h2StringValue);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return h2ArrayList;
	}

	public void verifyH2OnPage(String h2) {
		Set<String> set;
		ArrayList<String> arraylist;
		ArrayList<String> h2Arraylist = new ArrayList<String>();
		String str = "";
		boolean flag = true;

		try {
			set = new HashSet<String>();
			arraylist = getH2Heading(h2);
			for (int i = 0; i < arraylist.size(); i++) {
				str = arraylist.get(i);
				if (set.add(str) == false) {
					ExtentReportManager.logInfoDetails(
							String.format(properties.getLogMessage("VerifyHeadingDuplicacyFailed"), h2, arraylist));
				} else {
					flag = true;
				}
			}

			if (!(arraylist.isEmpty())) {
				if (flag) {
					ExtentReportManager.logInfoDetails(
							String.format(properties.getLogMessage("VerifyHeadingDuplicacyPassed"), h2, arraylist));
					/*
					 * h2Arraylist.add("h2"); h2Arraylist.add(str); h2Arraylist.add("Not Missing");
					 * map.put("h2", h2Arraylist);
					 */
				}
			} else {
				h2Arraylist.add("h2");
				h2Arraylist.add(str);
				h2Arraylist.add("Missing");
				map.put("h2", h2Arraylist);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/***
	 * This will verify heading H1 on page
	 * 
	 * @param pageURL
	 * @param enterHeading
	 */
	public void verifyH1OnPage(String h1) {
		Set<String> set;
		ArrayList<String> arraylist;
		ArrayList<String> h1Arraylist = new ArrayList<String>();
		String str = "";
		boolean flag = true;

		try {
			set = new HashSet<String>();
			arraylist = getH1Heading(h1);
			for (int i = 0; i < arraylist.size(); i++) {
				str = arraylist.get(i);
				if (set.add(str) == false) {
					ExtentReportManager.logInfoDetails(
							String.format(properties.getLogMessage("VerifyHeadingDuplicacyFailed"), h1, arraylist));

				} else {
					flag = true;
				}
			}
			if (!(arraylist.isEmpty())) {
				if (flag) {
					ExtentReportManager.logInfoDetails(
							String.format(properties.getLogMessage("VerifyHeadingDuplicacyPassed"), h1, arraylist));
					/*
					 * h1Arraylist.add("h1"); h1Arraylist.add(str); h1Arraylist.add("Not Missing");
					 * map.put("h1", h1Arraylist);
					 */
				}

			} else {
				h1Arraylist.add("h1");
				h1Arraylist.add(str);
				h1Arraylist.add("Missing");
				map.put("h1", h1Arraylist);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/***
	 * This will verify if ROBOTS attribute name have content value is
	 * [INDEX,FOLLOW]
	 * 
	 * @throws Exception
	 */
	public void verifyMetaRobotsKeyWordMissingOfPage() throws Exception {
		List<WebElement> metaElements;
		int totalCount;
		String nameAttributeValue = "";
		String contentAttributeValue = "";
		String str1 = "";
		String indexFollow = "Index,follow";
		ArrayList<String> robotsMeta = new ArrayList<String>();
		try {
			metaElements = driver.findElements(By.tagName("meta"));
			totalCount = metaElements.size();
			for (int i = 0; i < totalCount; i++) {
				nameAttributeValue = metaElements.get(i).getAttribute(nameAttribute);
				contentAttributeValue = metaElements.get(i).getAttribute(contentAttribute);
				{
					if (metaElements.get(i).isEnabled()) {
						if (metaRobotAttirbuteName.equals(nameAttributeValue)) {
							if (contentAttributeValue == null || contentAttributeValue.isEmpty()
									|| contentAttributeValue.equalsIgnoreCase("null")) {
								ExtentReportManager.logInfoDetails(
										String.format(properties.getLogMessage("VerifyRobotsMetaValueIsNull"),
												nameAttributeValue, contentAttributeValue));
								robotsMeta.add("Robots");
								robotsMeta.add(contentAttributeValue);
								robotsMeta.add("Missing");
								map.put("Robots", robotsMeta);
							} else if (!(contentAttributeValue.equalsIgnoreCase(indexFollow))) {

								robotsMeta.add("Robots");
								robotsMeta.add(contentAttributeValue);
								robotsMeta
										.add("Robots does not have 'Index,follow' value and it have different values");
								map.put("Robots", robotsMeta);
							} else {
								ExtentReportManager.logInfoDetails(
										String.format(properties.getLogMessage("VerifyRobotsMetaValueIsNotNull"),
												nameAttributeValue, contentAttributeValue));
							}
						}
					} else {
						ExtentReportManager
								.logInfoDetails(String.format(properties.getLogMessage("VerifyMetaElementVisibility")));
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/***
	 * This will verify the valid URL on page
	 * 
	 * @param urls
	 * @throws Exception
	 */
	public void getBrokenURLLinks(String urls) throws Exception {
		URL url;
		int responseCode;
		ArrayList<String> urlArrayList = new ArrayList<String>();
		ArrayList<String> serverRedirectUrl = new ArrayList<String>();
		try {
			url = new URL(urls);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(5000);
			httpURLConnection.connect();

			responseCode = httpURLConnection.getResponseCode();
			if ((responseCode >= 400)) {
				ExtentReportManager.logInfoDetails(
						String.format(properties.getLogMessage("ValidLinkURLFailed"), urls, responseCode));

				if (map.containsKey("URLs")) {
					urlArrayList = map.get("URLs");
					urlArrayList.set(1, urlArrayList.get(1) + "<br> , " + "<a href=" + urls + ">" + urls + "</a>");
				} else {
					urlArrayList.add("URLs");
					urlArrayList.add(urls);
					urlArrayList.add("Invalid/Broken");
					map.put("URLs", urlArrayList);
				}
			}
		}

		catch (ProtocolException e) {
			if (map.containsKey("Redirected URLs")) {
				serverRedirectUrl = map.get("Redirected URLs");
				serverRedirectUrl.set(1,
						serverRedirectUrl.get(1) + ", " + "<br> , " + "<a href=" + urls + ">" + urls + "</a>");
			} else {
				serverRedirectUrl.add("Redirected URLs");
				serverRedirectUrl.add(urls);
				serverRedirectUrl.add("Server Redirected URLs");
				map.put("Redirected URLs", serverRedirectUrl);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public void verifyIfURLLinksIsBrokenOfPage() throws Exception {
		String url;
		String urlTitleName;
		try {
			List<WebElement> urlLinks = driver.findElements(LINKS);
			int totalLinksPresent = urlLinks.size();
			for (int i = 1; i < totalLinksPresent; i++) {
				url = urlLinks.get(i).getAttribute("href");

				if (url != null) {
					/// String url=urlsss.replace("https", "http");
					if (url != null && !url.contains("javascript") && !url.contains("tel:")
							&& !url.contains("mailto:")) {
						if (url.contains("http://api.whatsapp.com/send/?phone=917277277275") || url.isEmpty()
								|| url.contains("whatsapp://send?text=")) {
							System.out.println(url);
						} else {
							getBrokenURLLinks(url);
						}
					}
				} else {
					System.out.println("url is null");
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public ArrayList<String> getSiteXMLURL() throws IOException {
		ArrayList<String> urlList = new ArrayList<>();
		Document doc = Jsoup.connect("https://www.spinny.com/sitemap.xml").get();
		Elements urls = doc.getElementsByTag("loc");

		for (Element url : urls) {
			urlList.add(url.text());
		}

		return urlList;
	}

	public ArrayList<String> getALLSiteURL() throws IOException, InterruptedException {

		ArrayList<String> arraylist = getSiteXMLURL();
		ArrayList<String> urlList = new ArrayList<String>();
		int count = arraylist.size();
		for (int i = 0; i < count; i++) {
			String str = arraylist.get(i);
			Document doc = Jsoup.connect(str).get();
			Elements urls = doc.getElementsByTag("loc");

			for (Element url : urls) {
				urlList.add(url.text());
			}
		}
		return urlList;
	}

	public void sEOCheckerOfPageUsingSiteXMLExtractor(String pageURL, String h1, String h2) throws Exception {
		try {
			String[] urlListArray = pageURL.split(",");
			for (String url : urlListArray) {
				verifySEOOfPageData(url, h1, h2);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/***
	 * This method used to check for all URL which is present in
	 * webtest.myspinny.com
	 * 
	 * @param h1
	 * @param h2
	 * @throws Exception
	 */
	public void sEOCheckerOfPageUsingSiteXMLExtractor(String h1, String h2) throws Exception {
		ArrayList<String> arraylist = getALLSiteURL();
		int totalURLPresentCount = arraylist.size();
		try {
			for (int i = 0; i < totalURLPresentCount; i++) {
				String str = arraylist.get(i);
				String extactURL = str.replace("www.spinny", "webtest.myspinny");

				URL url = new URL(extactURL);
				HttpURLConnection httpURLConnections = (HttpURLConnection) url.openConnection();
				httpURLConnections.setConnectTimeout(5000);
				httpURLConnections.connect();
				int responseCode = httpURLConnections.getResponseCode();
				String responseMsg = httpURLConnections.getResponseMessage();
				if (responseCode >= 200 && responseCode <= 299) {

					if (!extactURL.contains("https://webtest.myspinny.com/buy-used-cars/")) {
						verifySEOOfPageData(extactURL, h1, h2);
					}
				} else {
					ExtentReportManager.logInfoDetails(String.format("PageURL", extactURL, responseCode, responseMsg));
					System.out.println(
							extactURL + "response Code : " + responseCode + " response message : " + responseMsg);
				}
			}

		} catch (Exception e) {
			throw e;
		}
	}

	public void verifySEOOfPageData(String pageURL, String h1, String h2) throws Exception {
		driver.navigate().to(pageURL);
		map = new HashMap<String, ArrayList<String>>();
		int b = ++i;
		String no = "";
		String str = "";
		try {
			no = Integer.toString(b);
			str = no + ". Current page and title of page is [" + driver.getTitle() + "] and URL is : [" + pageURL + "]";
			ExtentReportManager.getExtentTest().log(Status.INFO, MarkupHelper.createLabel(str, ExtentColor.BLUE));
			verifyLengthOfTitleOfPage();
			verifyKeywordOfMetaDataOfage();
			verifyMetaKeyWordsIsMissingOfPage();
			verifyMetaDescriptionLengthOfPage();
			verifyIfURLLinksIsBrokenOfPage();
			verifyMetaRobotsKeyWordMissingOfPage();
			verifyH1OnPage(h1);
			verifyH2OnPage(h2);
			verifyALTAttributeOfImg();
			Reporter.writeResults(map, pageURL);

		} catch (Exception e) {
			throw e;
		}
	}

}
