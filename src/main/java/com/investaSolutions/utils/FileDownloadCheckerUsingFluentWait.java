package com.investaSolutions.utils;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class FileDownloadCheckerUsingFluentWait {

	public static void main(String[] args) {

		String downloadFolderPath = "C:\\Users\\thrah\\Downloads"; // 🔁 Replace with your actual path
		String expectedFileName = "gcapi.dll";
		File file=new File(downloadFolderPath);

		// Create a FluentWait object that waits for a file in the given folder
		FluentWait<File> wait = new FluentWait<File>(file)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(Exception.class);

		File downloadedFile = wait.until(new FileDownloadCondition(expectedFileName));

		if (downloadedFile != null && downloadedFile.exists()) {
			System.out.println("✅ File found: " + downloadedFile.getAbsolutePath());
		} else {
			System.out.println("❌ File not found within the timeout.");
		}
	}
}

// 👇 This is your custom Function class (instead of lambda)
class FileDownloadCondition implements Function<File, File> {
	private String expectedFileName;

	public FileDownloadCondition(String expectedFileName) {
		this.expectedFileName = expectedFileName;
	}

	@Override
	public File apply(File folder) {
		File[] files = folder.listFiles();
		if (files == null)
			return null;

		for (File file : files) {
			if (file.getName().equalsIgnoreCase(expectedFileName)) {
				return file;
			}
		}
		return null; // Not yet downloaded
	}
}
