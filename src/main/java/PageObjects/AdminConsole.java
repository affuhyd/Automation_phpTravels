package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.ParentBase;
import Utilities.TestUtilities;



public class AdminConsole extends ParentBase {
	@FindBy(xpath="//a/i[@class='fa fa-desktop']")
	private WebElement dashboard;
	//*[@id="sidebarCollapse"]/i
	@FindBy(xpath="//*[@id='sidebarCollapse']")
	private WebElement sidebar;
	@FindBy(xpath="//*/a[contains(text(),'General')]/i")
	private WebElement general;
	@FindBy(xpath="//li/a[text()='Currencies']")
	private WebElement currencies;
	@FindBy(id="Locations")
	private WebElement locations;
	@FindBy(xpath="//li/a[text()='Locations List']")
	private WebElement locations_list;
	
	//*[@id="social-sidebar-menu"]/li[4]/a/text()
	
	//*[@id="social-sidebar-menu"]/li/a/text()='General'
	public AdminConsole(){
		PageFactory.initElements(driver, this);
	}
	
	public CurrenciesManagementPage gotoCurrenciesPage(){
		//TestUtilities.dynamicWait(driver, general);
		general.click();
		//TestUtilities.dynamicWait(driver, currencies);
		currencies.click();
		return new CurrenciesManagementPage();
	}
	/*
	 * The below method is to navigate to Locations List Page
	 * 
	 * 
	 */
	public LocationsListPage gotoLocationsListPage(){
		TestUtilities.dynamicWait(driver, locations);
		locations.click();
		TestUtilities.dynamicWait(driver, locations);
		locations_list.click();
		return new LocationsListPage();
	}
}
