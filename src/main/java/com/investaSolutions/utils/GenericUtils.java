package com.investaSolutions.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GenericUtils {

	// Method to create a file using FileOutputStream
	public static void createFileUsingFileOutputStreamClass(String filenameWithPath, String data) throws IOException {
		try (FileOutputStream out = new FileOutputStream(filenameWithPath)) {
			out.write(data.getBytes());
		}
	}

	// Method to get total number of characters in a string
	public int getTotalNumbersOfCharacters(String str) {
		return str != null ? str.length() : 0;
	}

	// Method to convert a string to an integer
	public int convertStringToInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid input for conversion to int: " + str, e);
		}
	}

	// Get current date and time in milliseconds format
	public static String getCurrentDateTimeMS() {
		SimpleDateFormat formatedNow = new SimpleDateFormat("yyMMddhhmmssMs");
		return formatedNow.format(new Date());
	}

	// Get current date in MM-dd-YYYY format
	public static String getCurrentDate() {
		SimpleDateFormat formatedNow = new SimpleDateFormat("MM-dd-yyyy");
		return formatedNow.format(new Date());
	}

	// Get current time in hh:mma format
	public static String getCurrentTime() {
		SimpleDateFormat formatedNow = new SimpleDateFormat("hh:mma");
		String uniqueValue = formatedNow.format(new Date());
		return StringUtils.stripStart(uniqueValue, "0").toLowerCase();
	}

	// Get current time minus 60 seconds
	public static String getCurrentTimeMinusSecond() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, -60);
		SimpleDateFormat formatedNow = new SimpleDateFormat("hh:mma");
		String uniqueValue = formatedNow.format(cal.getTime());
		return StringUtils.stripStart(uniqueValue, "0").toLowerCase();
	}

	// Clean up folder by deleting its contents
	public void cleanUpFolder(String directoryPath) throws IOException {
		File file = new File(directoryPath);
		if (!file.exists()) {
			file.mkdir();
		}
		FileUtils.cleanDirectory(file);
	}

	// Generate a random string of specified length
	public static String randomString(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		StringBuilder result = new StringBuilder(length);
		Random rand = new Random();

		for (int i = 0; i < length; i++) {
			int index = rand.nextInt(characters.length());
			result.append(characters.charAt(index));
		}
		return result.toString();
	}

	// Generate a random integer within a specified range
	public static int randomNumber(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("Max must be greater than min.");
		}
		Random rand = new Random();
		return rand.nextInt((max - min)) + min; // Returns a number between min (inclusive) and max (exclusive)
	}

	// Method to generate a random double within a specified range
	public static double randomDouble(double min, double max) {
		if (min >= max) {
			throw new IllegalArgumentException("Max must be greater than min.");
		}
		Random rand = new Random();
		return rand.nextDouble() * (max - min) + min; // Returns a double between min (inclusive) and max (exclusive)
	}

	// Method to convert a double to a string
	public static String doubleToString(double value) {
		return String.valueOf(value);
	}

	// Method to convert a string to a double
	public static double stringToDouble(String str) {
		try {
			return Double.parseDouble(str);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid input for conversion to double: " + str, e);
		}
	}

	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	private static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static Date parseDate(String dateString) throws ParseException {
		return new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(dateString);
	}

	public static int getCurrentYear() {
		return LocalDate.now().getYear();
	}

	public static String formatDate(Date date) {
		return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(date);
	}

	public static Date parseDateTime(String dateTimeString) throws ParseException {
		return new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT).parse(dateTimeString);
	}

	public static String formatDateTime(Date date) {
		return new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT).format(date);
	}

	public static Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

	public static Date subtractDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -days);
		return calendar.getTime();
	}

	public static boolean areDatesEqual(Date date1, Date date2) {
		return date1.compareTo(date2) == 0;
	}

	public static boolean isDateBefore(Date date1, Date date2) {
		return date1.compareTo(date2) < 0;
	}

	public static boolean isDateAfter(Date date1, Date date2) {
		return date1.compareTo(date2) > 0;
	}

	// Method to fetch user details from JSON file
	public HashMap<String, String> getUserDetailsFromJSON(String jsonFilePath, String user) throws Exception {
		HashMap<String, String> userDetailsMap = new HashMap<>();

		// Parse the JSON file
		JSONParser parser = new JSONParser();
		try (FileReader reader = new FileReader(jsonFilePath)) {
			// Read JSON file
			Object obj = parser.parse(reader);
			JSONObject jsonObject = (JSONObject) obj;

			// Get the specific user object from JSON
			JSONObject userObject = (JSONObject) jsonObject.get(user);

			// Extract details and add to the map
			if (userObject != null) {
				userDetailsMap.put("username", (String) userObject.get("username"));
				userDetailsMap.put("password", (String) userObject.get("password"));
				// Add other fields if necessary
			} else {
				throw new Exception("User not found in the JSON file: " + user);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error occurred while parsing JSON file.", e);
		}

		return userDetailsMap;
	}
}
