package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.ParentBase;

public class TestUtilities extends ParentBase {
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	public static int RERUN_FAILED_TESTCASES_COUNT = 5;
	public static int WebDriver_Waiting_TIMEOUT = 25;

	public static String TESTDATA_SHEET_PATH = "";

	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

	public static boolean isClickable(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WebDriver_Waiting_TIMEOUT);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void dynamicWait(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, WebDriver_Waiting_TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void avoid_StaleElementReferenceException(WebDriver driver, List<WebElement> list) {
		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver, WebDriver_Waiting_TIMEOUT);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(list)));
	}

	public static boolean isSorted_ascending_order(List<String> listOfStrings) {

		if ((listOfStrings.isEmpty()) || listOfStrings.size() == 1) {
			return true;
		}

		Iterator<String> it = listOfStrings.iterator();
		String current, previous = it.next();
		while (it.hasNext()) {
			current = it.next();
			if (previous.compareTo(current) > 0) {
				return false;
			}
			previous = current;
		}
		return true;
	}

	public static boolean isSorted_descending_order(List<String> listOfStrings) {

		if ((listOfStrings.isEmpty()) || listOfStrings.size() == 1) {
			return true;
		}

		Iterator<String> it = listOfStrings.iterator();
		String current, previous = it.next();
		while (it.hasNext()) {
			current = it.next();
			if (previous.compareTo(current) < 0) {
				return false;
			}
			previous = current;
		}
		return true;
	}

}
