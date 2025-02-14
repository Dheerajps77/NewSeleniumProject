package com.investaSolutions.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private Workbook workbook;
	private Sheet sheet;

	public ExcelUtils(String filePath, String sheetName) throws IOException {
		try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
			workbook = new XSSFWorkbook(fileInputStream);
			sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet " + sheetName + " does not exist in " + filePath);
			}
		}
	}

// Generic Function to Read Excel Data as List of Maps
public static List<Map<String, String>> readExcelDataAsList(String filePath, String sheetName) throws IOException {
    List<Map<String, String>> dataList = new ArrayList<>();
    DataFormatter formatter = new DataFormatter();

    try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet " + sheetName + " does not exist in " + filePath);
        }

        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            throw new IllegalArgumentException("Header row is missing in the sheet.");
        }

        // Get header names dynamically
        List<String> headers = new ArrayList<>();
        for (Cell cell : headerRow) {
            headers.add(formatter.formatCellValue(cell));
        }

        // Read data from remaining rows
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Map<String, String> rowData = new LinkedHashMap<>();
            for (int j = 0; j < headers.size(); j++) {
                String cellValue = formatter.formatCellValue(row.getCell(j));
                rowData.put(headers.get(j), cellValue);
            }
            dataList.add(rowData);
        }
    }
    return dataList;
}


	// Get test data as Map<String, Map<String, String>>
	public static Map<String, Map<String, String>> getTestDataAsMap(String filePath, String sheetName)
			throws IOException {
		Map<String, Map<String, String>> testData = new LinkedHashMap<>();
		DataFormatter dataFormatter = new DataFormatter();

		try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {
			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet " + sheetName + " does not exist in " + filePath);
			}

			int totalRows = sheet.getPhysicalNumberOfRows();
			for (int i = 1; i < totalRows; i++) { // Skip header row (assumed as row 0)
				Row row = sheet.getRow(i);
				if (row == null)
					continue;

				String testCaseId = dataFormatter.formatCellValue(row.getCell(0)).trim();
				Map<String, String> fieldKeyValueMap = new LinkedHashMap<>();

				for (int j = 1; j < row.getLastCellNum(); j += 2) {
					String fieldKey = dataFormatter.formatCellValue(row.getCell(j)).trim();
					String fieldValue = dataFormatter.formatCellValue(row.getCell(j + 1)).trim();
					fieldKeyValueMap.put(fieldKey, fieldValue);
				}

				testData.put(testCaseId, fieldKeyValueMap);
			}
		}

		return testData;
	}
	
    // New method to get test case data based on ID
    public static Map<String, String> getTestCaseData(String filePath, String sheetName, String testCaseId) throws IOException {
        Map<String, Map<String, String>> testData = getTestDataAsMap(filePath, sheetName);
        return testData.getOrDefault(testCaseId, new LinkedHashMap<>());
    }

	public static Object[][] getExcelData(String filePath, String sheetName) throws IOException {
		try (FileInputStream fileInputStream = new FileInputStream(filePath);
				Workbook workbook = new XSSFWorkbook(fileInputStream)) {

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet " + sheetName + " does not exist in " + filePath);
			}

			int rowCount = sheet.getPhysicalNumberOfRows();
			int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			Object[][] data = new Object[rowCount - 1][colCount];

			for (int i = 1; i < rowCount; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < colCount; j++) {
					Cell cell = row.getCell(j);
					data[i - 1][j] = getCellValueAsString(cell);
				}
			}

			return data;
		}
	}

	private static String getCellValueAsString(Cell cell) {
		DataFormatter formatter = new DataFormatter();
		return cell != null ? formatter.formatCellValue(cell) : "";
	}

	public List<Map<String, String>> getAllData() {
		List<Map<String, String>> allData = new ArrayList<>();
		Row headerRow = sheet.getRow(0);

		if (headerRow == null) {
			throw new IllegalArgumentException("Header row is missing in the sheet.");
		}

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row currentRow = sheet.getRow(i);
			Map<String, String> currentRowData = new LinkedHashMap<>();

			for (int j = 0; j < headerRow.getPhysicalNumberOfCells(); j++) {
				String columnName = headerRow.getCell(j).getStringCellValue();
				String cellValue = getCellValueAsString(currentRow.getCell(j));
				currentRowData.put(columnName, cellValue);
			}

			allData.add(currentRowData);
		}

		return allData;
	}

	public static List<LinkedHashMap<String, String>> getExcelDataWithRandomNumber(String excelFileName,
			String sheetName) throws IOException {
		List<LinkedHashMap<String, String>> dataFromExcel = new ArrayList<>();
		List<String> allKeys = new ArrayList<>();
		DataFormatter dataFormatter = new DataFormatter();

		try (Workbook workbook = WorkbookFactory
				.create(new File("src/test/resources/testdata/" + excelFileName + ".xlsx"))) {
			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet " + sheetName + " does not exist in " + excelFileName);
			}

			for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
				LinkedHashMap<String, String> mapData = new LinkedHashMap<>();
				Row row = sheet.getRow(i);
				if (i == 0) {
					for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
						allKeys.add(row.getCell(j).getStringCellValue());
					}
				} else {
					for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
						String cellValue = dataFormatter.formatCellValue(row.getCell(j));
						if (cellValue.contains("RandomNumber")) {
							int size = cellValue.contains("_") ? Integer.parseInt(cellValue.split("_")[1]) : 6;
							cellValue = RandomDataGenerator.getRandomNumber(size);
						}
						mapData.put(allKeys.get(j), cellValue);
					}
					dataFromExcel.add(mapData);
				}
			}
		}
		return dataFromExcel;
	}

	public static List<Map<String, String>> getExcelDataBasedOnTestSet(String excelFileName, String sheetName,
			String testSetName) throws IOException {
		List<Map<String, String>> testData = new ArrayList<>();
		DataFormatter formatter = new DataFormatter();

		try (Workbook workbook = WorkbookFactory
				.create(new File("src/test/resources/testdata/" + excelFileName + ".xlsx"))) {
			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet " + sheetName + " does not exist in " + excelFileName);
			}

			for (Row row : sheet) {
				if (formatter.formatCellValue(row.getCell(0)).equalsIgnoreCase(testSetName)) {
					Map<String, String> rowData = new LinkedHashMap<>();
					for (int j = 1; j < row.getPhysicalNumberOfCells(); j++) {
						String columnHeader = sheet.getRow(0).getCell(j).getStringCellValue();
						String cellValue = formatter.formatCellValue(row.getCell(j));
						rowData.put(columnHeader, cellValue);
					}
					testData.add(rowData);
				}
			}
		}
		return testData;
	}

	// Helper method to copy sheet content
	public void copySheetContent(String sourceSheetName, String destinationFilePath, String destinationSheetName)
			throws IOException {
		try (FileInputStream sourceStream = new FileInputStream(new File(this.sheet.getWorkbook().getSheetName(0)));
				Workbook sourceWorkbook = new XSSFWorkbook(sourceStream);
				Workbook destinationWorkbook = new XSSFWorkbook();
				FileOutputStream destinationStream = new FileOutputStream(destinationFilePath)) {

			Sheet sourceSheet = sourceWorkbook.getSheet(sourceSheetName);
			Sheet destinationSheet = destinationWorkbook.createSheet(destinationSheetName);

			for (int i = 0; i <= sourceSheet.getLastRowNum(); i++) {
				Row sourceRow = sourceSheet.getRow(i);
				Row destinationRow = destinationSheet.createRow(i);

				if (sourceRow != null) {
					for (int j = 0; j < sourceRow.getPhysicalNumberOfCells(); j++) {
						Cell sourceCell = sourceRow.getCell(j);
						Cell destinationCell = destinationRow.createCell(j);

						switch (sourceCell.getCellType()) {
						case STRING -> destinationCell.setCellValue(sourceCell.getStringCellValue());
						case NUMERIC -> destinationCell.setCellValue(sourceCell.getNumericCellValue());
						case BOOLEAN -> destinationCell.setCellValue(sourceCell.getBooleanCellValue());
						case FORMULA -> destinationCell.setCellFormula(sourceCell.getCellFormula());
						default -> destinationCell.setBlank();
						}
					}
				}
			}
			destinationWorkbook.write(destinationStream);
		}
	}

	// Random Data Generator class
	public static class RandomDataGenerator {
		public static String getRandomNumber(int size) {
			Random random = new Random();
			StringBuilder randomNumber = new StringBuilder();
			for (int i = 0; i < size; i++) {
				randomNumber.append(random.nextInt(10));
			}
			return randomNumber.toString();
		}
	}
}
