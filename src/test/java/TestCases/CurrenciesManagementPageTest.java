package TestCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.ParentBase;
import PageObjects.AddCurrencyPage;
import PageObjects.AdminConsole;
import PageObjects.CurrenciesManagementPage;
import PageObjects.LoginPage;
import PageObjects.ViewCurrencyPage;
import Utilities.TestUtilities;

public class CurrenciesManagementPageTest extends ParentBase {
	LoginPage loginPage;
	AdminConsole adminConsole;
	CurrenciesManagementPage currencyPage;
	ViewCurrencyPage viewCurrencyPage;
	AddCurrencyPage addCurrencyPage;
	Object[][] data;

	public CurrenciesManagementPageTest() {
		super();
		
	}

	@BeforeMethod()
	public void setUp() {
		initialize();
		loginPage = new LoginPage();
		adminConsole = loginPage.ClickLoginButton();
		currencyPage = adminConsole.gotoCurrenciesPage();
	}

	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}

	@Test()
	public void CurrencyManagementHeader_isDisplayed_Test() {
		boolean flag = currencyPage.Currency_Mgmt_header_isDisplayed();
		Assert.assertTrue(flag);
	}

	@Test()
	public void Currency_ApiKeys_label_isDisplayed_Test() {
		boolean flag = currencyPage.Currency_APIKeys_label_isDisplayed();
		Assert.assertTrue(flag);
	}

	@Test()
	public void Cronjob_url_label_isDisplayed_Test() {
		boolean flag = currencyPage.Cronjob_url_label_isDisplayed();
		Assert.assertTrue(flag);
	}

	@Test()
	public void Verify_CurrencyAPIKeys_field_ReadOnly_Test() {
		boolean flag = currencyPage.Currency_APIKeys_isEnabled();
		Assert.assertFalse(flag);
	}

	@Test()
	public void Verify_CronjobURL_field_ReadOnly_Test() {
		boolean flag = currencyPage.Cronjob_url_isEnabled();
		Assert.assertFalse(flag);
	}

	@Test
	public void Verify_Copy_btn_isClickable_Test() {
		boolean flag = currencyPage.Copy_btn_isClickable();
		Assert.assertTrue(flag);
	}

	// TODO - Checking copy button functionality
	@Test
	public void Verify_refresh_btn_isClickable_Test() {
		boolean flag = currencyPage.Refresh_button_isClickable();
		Assert.assertTrue(flag);
	}

	// Checking Refresh button functionality
	@Test
	public void Verify_Add_btn_isClickable_Test() {
		boolean flag = currencyPage.Add_btn_isClickable();
		Assert.assertTrue(flag);
	}

	
	@Test
	public void Verify_ViewCurrencyRecord_Test(String Code) {
		List<String> ls_currencyPage = currencyPage.getCurrencyRecord(Code);
		if (ls_currencyPage.isEmpty()) {
			System.out.println("The Currency " + Code + " record does not exist");
		} else {
			viewCurrencyPage = currencyPage.Click_View_btn(Code);
			List<String> ls_viewCurrencyPage = viewCurrencyPage.getRecordValues();
			boolean flag = ls_currencyPage.equals(ls_viewCurrencyPage);
			Assert.assertTrue(flag);
		}
	}

	@Test
	public void Verify_Edit_Currency_Test(String Name, String Symbol, String Code, String Rate, String Active) {
		List<String> ls_record = currencyPage.getCurrencyRecord(Code);
		if (ls_record.isEmpty()) {
			System.out.println("The Currency " + Code + " record does not exist");
		} else {

			addCurrencyPage = currencyPage.Click_Edit_btn(Code);
			List<String> ls_set = addCurrencyPage.setData(Name, Symbol, Code, Rate, Active);
			currencyPage = addCurrencyPage.Save_and_return_Currency(Name, Symbol, Code, Rate, Active);
			List<String> ls = currencyPage.getCurrencyRecord(Code);
			Assert.assertTrue(ls_set.equals(ls));
		}
	}

	@Test(dataProvider = "getDataToClickDeleteAndCancel")
	public void Verify_ClickDelete_and_Cancel_Deletion_Test(String Code) {
		List<String> ls_record = currencyPage.getCurrencyRecord(Code);
		if (ls_record.isEmpty()) {
			System.out.println("The Currency " + Code + " record does not exist");
		} else {
			currencyPage.DeleteCurrencyRecord_Click_Cancel_btn(Code);
			List<String> ls = currencyPage.getCurrencyRecord(Code);
			Assert.assertFalse(ls.isEmpty());
		}
	}

	@Test(dataProvider = "getDataToClickDeleteAndOK")
	public void Verify_Deletion_record_Test(String Code) {
		List<String> ls_record = currencyPage.getCurrencyRecord(Code);
		if (ls_record.isEmpty()) {
			System.out.println("The Currency " + Code + " record does not exist");
		} else {
			currencyPage.DeleteCurrencyRecord_Click_OK_btn(Code);
			List<String> ls = currencyPage.getCurrencyRecord(Code);
			Assert.assertTrue(ls.isEmpty());
		}
	}

	@Test
	public void setCurrencyAsDefaultTest(String Code) {
		List<String> ls_record = currencyPage.getCurrencyRecord(Code);
		if (ls_record.isEmpty()) {
			System.out.println("The Currency " + Code + " record does not exist");
		} else {
			boolean flag = currencyPage.selectCurrencyAsDefault(Code);
			Assert.assertTrue(flag);
		}
	}

	@Test
	public void setCurrencyAsDefault_Cancel_Test(String Code) {
		List<String> ls_record = currencyPage.getCurrencyRecord(Code);
		if (ls_record.isEmpty()) {
			System.out.println("The Currency " + Code + " record does not exist");
		} else {
			boolean flag = currencyPage.selectCurrencyAsDefault_Cancel(Code);
			Assert.assertTrue(flag);
		}
	}

	@Test()
	public void sort_records_ByName_Test() {
		List<String> ls = currencyPage.sort_Records_ByNameHeader();
		Assert.assertTrue(TestUtilities.isSorted_ascending_order(ls));
		ls = currencyPage.sort_Records_BySymbolHeader();
		Assert.assertTrue(TestUtilities.isSorted_descending_order(ls));
	}

	@Test()
	public void sort_records_BySymbol_Test() {
		List<String> ls = currencyPage.sort_Records_BySymbolHeader();
		Assert.assertTrue(TestUtilities.isSorted_ascending_order(ls));
		ls = currencyPage.sort_Records_ByNameHeader();
		Assert.assertTrue(TestUtilities.isSorted_descending_order(ls));
	}

	@Test()
	public void sort_records_ByCode_Test() {
		List<String> ls = currencyPage.sort_Records_ByCodeHeader();
		Assert.assertTrue(TestUtilities.isSorted_ascending_order(ls));
		ls = currencyPage.sort_Records_ByCodeHeader();
		Assert.assertTrue(TestUtilities.isSorted_descending_order(ls));
	}

	@Test()
	public void sort_records_ByRate_Test() {
		List<String> ls = currencyPage.sort_Records_ByRateHeader();
		Assert.assertTrue(TestUtilities.isSorted_ascending_order(ls));
		ls = currencyPage.sort_Records_ByRateHeader();
		Assert.assertTrue(TestUtilities.isSorted_descending_order(ls));
	}

	@Test()
	public void sort_records_ByActive_Test() {
		List<String> ls = currencyPage.sort_Records_ByActiveHeader();
		Assert.assertTrue(TestUtilities.isSorted_descending_order(ls));
		currencyPage.sort_Records_ByActiveHeader();
		Assert.assertTrue(TestUtilities.isSorted_ascending_order(ls));
	}

	@Test
	public void exec_time_isDisplayedTest() {
		boolean flag = currencyPage.exec_time_isDisplayed();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void memory_usage_isDisplayedTest() {
		boolean flag = currencyPage.memory_usage_isDisplayed();
		Assert.assertTrue(flag);
	}
	

	@DataProvider()
	public Object[][] getDataToClickDeleteAndCancel() {
		/*
		 * TestUtilities.getTestData(sheetName) Code to be implemented here
		 */

		return data;

	}
}
