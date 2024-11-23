package com.recent.files;

import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.investaSolutions.utils.PropertiesManager;

public class SiteMapURLExtractor {

	PropertiesManager properties = PropertiesManager.getInstance();

	private String getBasePath() {
		return System.getProperty("user.dir") + "\\src\\main\\resources\\";
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
		ArrayList<String> urlList = new ArrayList<>();
		for (String str : arraylist) {
			System.out.println(str);
			Document doc = Jsoup.connect(str).get();
			Elements urls = doc.getElementsByTag("loc");

			for (Element url : urls) {
				urlList.add(url.text());
			}
		}
		return urlList;
	}

	public ArrayList<String> getAllSiteMapURL() throws IOException, InterruptedException {
		ArrayList<String> arraylist = getALLSiteURL();
		ArrayList<String> ab = new ArrayList<>();
		ArrayList<String> ab1 = new ArrayList<>();
		int totalURLPresentCount = arraylist.size();
		System.out.println("Total URL present under -- > https://www.spinny.com/sitemap.xml: " + totalURLPresentCount);

		for (String str : arraylist) {
			String str1 = str.replace("www.spinny", "webtest.myspinny");

			URL url = new URL(str1);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(10000);
			httpURLConnection.connect();
			int responseCode = httpURLConnection.getResponseCode();
			System.out.println(str1 + " response code is: " + responseCode);

			if (responseCode >= 200 && responseCode < 400) {
				ab.add(str1);
				if (str1.contains("https://webtest.myspinny.com/buy-used-cars/")) {
					ab1.add(str1);
				}
			}
		}

		String pathOfFile = getBasePath() + "URLDetailsWithUsedCar.txt";
		try (FileWriter writer = new FileWriter(pathOfFile, false)) {
			for (int i = 0; i < ab1.size(); i++) {
				writer.write(ab1.get(i));
				if (i < ab1.size() - 1)
					writer.write("\n");
			}
		}

		return ab;
	}

	public void writeFile() throws IOException, InterruptedException {
		ArrayList<String> ab = getAllSiteMapURL();
		String pathOfFile = getBasePath() + "URLDetails.txt";
		try (FileWriter writer = new FileWriter(pathOfFile, false)) {
			for (int i = 0; i < ab.size(); i++) {
				writer.write(ab.get(i));
				if (i < ab.size() - 1)
					writer.write("\n");
			}
		}
	}

	public ArrayList<String> getStaticURLFromSiteMap() throws IOException, InterruptedException {
		ArrayList<String> staticURLArrayList = new ArrayList<>();
		Document doc = Jsoup.connect("https://www.spinny.com/sitemap-static.xml").get();
		Elements urls = doc.getElementsByTag("loc");

		for (Element url : urls) {
			staticURLArrayList.add(url.text());
		}
		return staticURLArrayList;
	}

	public void printALLURLsUnderStaticSiteMap() throws IOException, InterruptedException {
		ArrayList<String> arraylist = getStaticURLFromSiteMap();
		ArrayList<String> ab = new ArrayList<>();
		int totalURLPresentCount = arraylist.size();
		System.out.println("Total number of URLs present under -- > https://www.spinny.com/sitemap-static.xml: "
				+ totalURLPresentCount);

		for (String str : arraylist) {
			String str1 = str.replace("www.spinny", "webtest.myspinny");
			ab.add(str1);
			System.out.println(str1);
		}

		String pathOfFile = getBasePath() + "URLDetailsStatic.txt";
		try (FileWriter writer = new FileWriter(pathOfFile, false)) {
			for (int i = 0; i < ab.size(); i++) {
				writer.write(ab.get(i));
				if (i < ab.size() - 1)
					writer.write("\n");
			}
		}
	}

	public ArrayList<String> getCarsListURLFromSiteMap() throws IOException, InterruptedException {
		ArrayList<String> staticURLArrayList = new ArrayList<>();
		Document doc = Jsoup.connect("https://www.spinny.com/used-cars-list-sitemap.xml").get();
		Elements urls = doc.getElementsByTag("loc");

		for (Element url : urls) {
			staticURLArrayList.add(url.text());
		}
		return staticURLArrayList;
	}

	public void printALLURLsUnderCarsListSiteMap() throws IOException, InterruptedException {
		ArrayList<String> arraylist = getCarsListURLFromSiteMap();
		ArrayList<String> ab = new ArrayList<>();
		int totalURLPresentCount = arraylist.size();
		System.out.println("Total number of URLs present under -- > https://www.spinny.com/used-cars-list-sitemap.xml: "
				+ totalURLPresentCount);

		for (String str : arraylist) {
			String str1 = str.replace("www.spinny", "webtest.myspinny");
			ab.add(str1);
			System.out.println(str1);
		}

		String pathOfFile = getBasePath() + "URLDetailsCarsList.txt";
		try (FileWriter writer = new FileWriter(pathOfFile, false)) {
			for (int i = 0; i < ab.size(); i++) {
				writer.write(ab.get(i));
				if (i < ab.size() - 1)
					writer.write("\n");
			}
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		SiteMapURLExtractor objSiteMapURLExtractor = new SiteMapURLExtractor();
		objSiteMapURLExtractor.writeFile();
		System.out.println("Hello...");
	}
}
