package InterviewQuestionAndAnswer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.investaSolutions.utils.ExcelUtils;

public class ExcelUtilsTest {

    private static final String FILE_PATH = "src/test/resources/testdata/TestData.xlsx";
    private static final String SHEET_NAME = "Sheet3";

    private ExcelUtils excelUtils;

    @BeforeSuite
    void setup() throws IOException {
        excelUtils = new ExcelUtils(FILE_PATH, SHEET_NAME);
    }

    @Test
    @DisplayName("Verify reading test data as a Map")
    void testGetTestDataAsMap() throws IOException {
        Map<String, Map<String, String>> testData = ExcelUtils.getTestDataAsMap(FILE_PATH, SHEET_NAME);

        assertNotNull(testData, "Test data should not be null");
        assertFalse(testData.isEmpty(), "Test data should not be empty");

        testData.forEach((testCaseId, dataMap) -> {
            System.out.println("TestCase ID: " + testCaseId + " -> " + dataMap);
            assertFalse(dataMap.isEmpty(), "Each test case should have data");
        });
    }

    @Test
    @DisplayName("Verify reading specific test case data")
    void testGetTestCaseData() throws IOException {
        String testCaseId = "TC_001";
        Map<String, String> testCaseData = ExcelUtils.getTestCaseData(FILE_PATH, SHEET_NAME, testCaseId);

        assertNotNull(testCaseData, "Test case data should not be null");
        assertFalse(testCaseData.isEmpty(), "Test case data should not be empty");

        System.out.println("Test case data for " + testCaseId + ": " + testCaseData);
    }

    @Test
    @DisplayName("Verify reading all data as an Object array")
    void testGetExcelData() throws IOException {
        Object[][] excelData = ExcelUtils.getExcelData(FILE_PATH, SHEET_NAME);

        assertNotNull(excelData, "Excel data should not be null");
        assertTrue(excelData.length > 0, "Excel data should contain rows");

        for (Object[] row : excelData) {
            assertTrue(row.length > 0, "Each row should have columns");
            System.out.println("Row Data: " + java.util.Arrays.toString(row));
        }
    }

    @Test
    @DisplayName("Verify getting all data as a List")
    void testGetAllData() {
        List<Map<String, String>> allData = excelUtils.getAllData();

        assertNotNull(allData, "All data should not be null");
        assertFalse(allData.isEmpty(), "All data should not be empty");

        for (Map<String, String> rowData : allData) {
            System.out.println(rowData);
        }
    }

    @Test
    @DisplayName("Verify reading data based on Test Set Name")
    void testGetExcelDataBasedOnTestSet() throws IOException {
        String testSetName = "Sheet3";
        List<Map<String, String>> testData = ExcelUtils.getExcelDataBasedOnTestSet(FILE_PATH, SHEET_NAME, testSetName);

        assertNotNull(testData, "Test data should not be null");
        assertFalse(testData.isEmpty(), "Test data should not be empty");

        testData.forEach(row -> System.out.println("Row Data: " + row));
    }

    @Test
    @DisplayName("Verify reading data with Random Number handling")
    void testGetExcelDataWithRandomNumber() throws IOException {
        List<LinkedHashMap<String, String>> testData = ExcelUtils.getExcelDataWithRandomNumber("TestData", "Sheet1");

        assertNotNull(testData, "Test data should not be null");
        assertFalse(testData.isEmpty(), "Test data should not be empty");

        testData.forEach(row -> System.out.println("Row Data: " + row));
    }
}
