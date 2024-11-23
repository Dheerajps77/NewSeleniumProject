package com.recent.files;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;


public class Reporter {
	
	
	private static final String resultPlaceholder = "<!-- INSERT_RESULTS -->";
	private static final String resultPlaceholderTable = "<!-- INSERT_TABLE_RESULTS -->";
	// add your path 
	private static final String templatePath = "C:\\Users\\Dheeraj\\eclipse-workspace\\SpinnyPortal\\Execution Reports\\my_td.html";
	private static final String templatePathTable = "C:\\Users\\Dheeraj\\eclipse-workspace\\SpinnyPortal\\Execution Reports\\my_custom_report .html";
			
	
	public static void writeResults(HashMap<String,ArrayList<String>> map, String pagURL) {
		try {	
			String reportIn = new String(Files.readAllBytes(Paths.get(templatePath)));
			//String reportInTable = new String(Files.readAllBytes(Paths.get(templatePathTable)));
			for (ArrayList<String> details : map.values()) {
				reportIn = reportIn.replaceFirst(resultPlaceholder,"<tr><td>" + details.get(0) + "</td><td>" + details.get(1) + "</td><td>" + details.get(2)+ "</td></tr>" + resultPlaceholder);
			}
			reportIn = reportIn.replace("replace_url", pagURL);
			
			//reportInTable.replaceFirst(resultPlaceholderTable,reportIn+resultPlaceholderTable);
			Files.write(Paths.get(templatePathTable),reportIn.getBytes(),StandardOpenOption.APPEND);
			
		} catch (Exception e) {
			System.out.println("Error when writing report file:\n" + e.toString());
		}
	}

}
