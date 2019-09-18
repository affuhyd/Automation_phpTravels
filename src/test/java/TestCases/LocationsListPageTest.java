/*
 * 14
 * 
 */
package TestCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.ParentBase;
import PageObjects.AddLocationPage;
import PageObjects.AdminConsole;
import PageObjects.LocationsListPage;
import PageObjects.LoginPage;



public class LocationsListPageTest extends ParentBase {
	LoginPage loginPage;
	AdminConsole adminConsole;
	LocationsListPage locationsListPage;
	AddLocationPage addLocationPage;

	public LocationsListPageTest() {
		super();
		initialize();
	}

	@BeforeMethod()
	public void setUp() {
		loginPage = new LoginPage();
		adminConsole = loginPage.ClickLoginButton();
		locationsListPage = adminConsole.gotoLocationsListPage();
	}

	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}

	@Test()
	public void LocationsHeader_isDisplayed_Test() {
		boolean flag = locationsListPage.locationsHeader_isDisplayed();
		Assert.assertTrue(flag);
	}

	@Test
	public void Add_btn_isDisplayedTest() {
		boolean flag = locationsListPage.Add_btn_isDisplayed();
		Assert.assertTrue(flag);
	}

	@Test
	public void DeleteSelected_btn_isDisplayedTest() {
		boolean flag = locationsListPage.DeleteSelected_btn_isDisplayed();
		Assert.assertTrue(flag);
	}

	@Test
	public void print_btn_isDisplayedTest() {
		boolean flag = locationsListPage.print_btn_isDisplayed();
		Assert.assertTrue(flag);
	}

	@Test
	public void Add_btn_isClickableTest() {
		boolean flag = locationsListPage.Add_btn_isClickable();
		Assert.assertTrue(flag);
	}

	@Test
	public void DeleteSelected_btn_isClickableTest() {
		boolean flag = locationsListPage.DeleteSelected_btn_isClickable();
		Assert.assertTrue(flag);
	}

	@Test
	public void print_btn_isClickableTest() {
		boolean flag = locationsListPage.print_btn_isClickable();
		Assert.assertTrue(flag);
	}

	@Test
	public void export_into_csv_btn_isDisplayedTest() {
		boolean flag = locationsListPage.exportIntocsv_btn_isDisplayed();
		Assert.assertTrue(flag);
	}

	@Test
	public void AddLocationTest(String Country, String Location, String Status, String Russian, String Farsi,
			String German, String French, String Turkish, String Arabic, String Spanish) {
		addLocationPage = locationsListPage.Click_Add_btn();
		List<String> ls_location = addLocationPage.setData(Country, Location, Status, Russian, Farsi, German, French,
				Turkish, Arabic, Spanish);
		locationsListPage = addLocationPage.ClickSubmit_btn();
		List<String> ls = locationsListPage.getLocationRecord(Location);
		Assert.assertTrue(ls.equals(ls_location));
	}

	@Test
	public void edit_LocationTest(String Country, String Location, String Status, String Russian, String Farsi,
			String German, String French, String Turkish, String Arabic, String Spanish) {
		addLocationPage = locationsListPage.EditLocation(Location);
		List<String> ls = addLocationPage.setData(Country, Location, Status, Russian, Farsi, German, French, Turkish,
				Arabic, Spanish);
		locationsListPage = addLocationPage.ClickSubmit_btn();
		List<String> ls_loc = locationsListPage.getLocationRecord(Location);
		Assert.assertTrue(ls_loc.equals(ls));
	}

	@Test
	public void delete_locationByIcon_CancelTest(String location) {
		boolean flag = locationsListPage.deleteLocationByIcon_Click_Cancel(location);
		Assert.assertTrue(flag);
		List<String> ls = locationsListPage.getLocationRecord(location);
		Assert.assertFalse(ls.isEmpty());
	}

	@Test
	public void delete_locationByIcon_OKTest(String location) {
		boolean flag = locationsListPage.deleteLocationByIcon_Click_OK(location);
		Assert.assertTrue(flag);
		List<String> ls = locationsListPage.getLocationRecord(location);
		Assert.assertTrue(ls.isEmpty());
	}

	@Test
	public void deleteMultipleRecords_using_DeleteSelected_btn_Cancel_Test(String... cities) {
		boolean flag = locationsListPage.Click_DeleteSelected_btn_ClickCancel(cities);
		Assert.assertTrue(flag);

		for (String city : cities) {
			List<String> ls = locationsListPage.getLocationRecord(city);
			Assert.assertFalse(ls.isEmpty());
		}
	}

	@Test
	public void deleteMultipleRecords_using_DeleteSelected_btn_OK_Test(String... cities) {
		boolean flag = locationsListPage.Click_DeleteSelected_btn_ClickOK(cities);
		Assert.assertTrue(flag);

		for (String city : cities) {
			List<String> ls = locationsListPage.getLocationRecord(city);
			Assert.assertTrue(ls.isEmpty());
		}
	}

}
