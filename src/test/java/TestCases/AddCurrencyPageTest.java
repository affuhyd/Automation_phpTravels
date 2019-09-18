/*
 * TODO - write tc for Save and Edit - total 6
 * 
 */
package TestCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.ParentBase;
import PageObjects.AddCurrencyPage;
import PageObjects.AdminConsole;
import PageObjects.CurrenciesManagementPage;
import PageObjects.LoginPage;
import PageObjects.ViewCurrencyPage;


public class AddCurrencyPageTest extends ParentBase {
	LoginPage loginPage;
	AdminConsole adminConsole;
	CurrenciesManagementPage currencyPage;
	ViewCurrencyPage viewCurrencyPage;
	AddCurrencyPage addCurrencyPage;
	Object[][] data;

	public AddCurrencyPageTest() {
		super();
	}

	@BeforeMethod()
	public void setUp() {
		initialize();
		loginPage = new LoginPage();
		adminConsole = loginPage.ClickLoginButton();
		currencyPage = adminConsole.gotoCurrenciesPage();
		addCurrencyPage = currencyPage.Click_Add_btn();
	}

	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}

	@Test(dependsOnMethods = "Verify_Add_btn_isClickable_Test")
	public void Verify_Save_and_Return_btn_With_ValidData(String Name, String Symbol, String Code, String Rate,
			String Active) {
		currencyPage = addCurrencyPage.Save_and_return_Currency(Name, Symbol, Code, Rate, Active);
		currencyPage.Click_All_btn();
		List<String> record = currencyPage.getCurrencyRecord(Code);
		Assert.assertFalse(record.isEmpty());
	}

	@Test(dependsOnMethods = "Verify_Add_btn_isClickable_Test")
	public void Verify_Save_and_Return_btn_With_InValidData(String Name, String Symbol, String Code, String Rate,
			String Active) {
		addCurrencyPage.Save_and_return_Currency(Name, Symbol, Code, Rate, Active);
		Assert.assertEquals(addCurrencyPage.displayErrorMessage(),
				"Some fields are likely to contain errors. Fix errors and try again.");
	}

	@Test(dependsOnMethods = "Verify_Add_btn_isClickable_Test")
	public void Verify_Save_and_New_btn_With_ValidData(String Name, String Symbol, String Code, String Rate,
			String Active) {
		addCurrencyPage.Save_and_New(Name, Symbol, Code, Rate, Active);
		List<String> ls_data = addCurrencyPage.getData();
		Assert.assertTrue(ls_data.get(0).isEmpty());
		Assert.assertTrue(ls_data.get(1).isEmpty());
		Assert.assertTrue(ls_data.get(2).isEmpty());
		currencyPage = addCurrencyPage.Click_Return_btn();
		currencyPage.Click_All_btn();
		List<String> record = currencyPage.getCurrencyRecord(Code);
		Assert.assertFalse(record.isEmpty());
	}

	@Test(dependsOnMethods = "Verify_Add_btn_isClickable_Test")
	public void Verify_Save_and_New_btn_With_InValidData(String Name, String Symbol, String Code, String Rate,
			String Active) {
		addCurrencyPage.Save_and_New(Name, Symbol, Code, Rate, Active);
		Assert.assertEquals(addCurrencyPage.displayErrorMessage(),
				"Some fields are likely to contain errors. Fix errors and try again.");
	}

	@Test
	public void exec_time_isDisplayedTest() {
		boolean flag = addCurrencyPage.exec_time_isDisplayed();
		Assert.assertTrue(flag);
	}

	@Test
	public void memory_usage_isDisplayedTest() {
		boolean flag = currencyPage.memory_usage_isDisplayed();
		Assert.assertTrue(flag);
	}

}
