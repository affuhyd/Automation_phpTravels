package PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.ParentBase;
import Utilities.TestUtilities;



public class LocationsListPage extends ParentBase {
	@FindBy(xpath = "//div[text()='Locations']")
	private WebElement locations_label;
	@FindBy(className = "btn btn-success")
	private WebElement Add_btn;
	@FindBy(xpath = "//a[@data-task='print']")
	private WebElement print_btn;
	@FindBy(xpath = "//a[@data-task = 'csv']")
	private WebElement export_into_csv_btn;
	@FindBy(className = "delete_button btn btn-danger btn-xs")
	private WebElement Delete_Selected_btn;

	@FindBy(xpath = "//span[contains(text(),'Execution time')]")
	private WebElement exec_time;

	@FindBy(xpath = "//span[contains(text(),'Memory')]")
	private WebElement memory_usage;

	@FindBy(xpath = "//button[text()='25']")
	private WebElement btn_25;
	@FindBy(xpath = "//button[text()='50']")
	private WebElement btn_50;
	@FindBy(xpath = "//button[text()='100']")
	private WebElement btn_100;
	@FindBy(xpath = "//button[text()='All']")
	private WebElement btn_All;
	@FindBy(xpath = "//a[text()='Search']")
	private WebElement btn_search;
	@FindBy(name = "phrase")
	private WebElement search_textbox;
	@FindBy(xpath = "//a[text()='Go']")
	private WebElement btn_Go;

	// tr/td[3][contains(text(),'Kostroma')]/preceding-sibling::td/div[@class='icheckbox_square-grey']

	public LocationsListPage() {
		PageFactory.initElements(driver, this);
	}

	public AddLocationPage Click_Add_btn() {
		Add_btn.click();
		return new AddLocationPage();
	}

	public boolean locationsHeader_isDisplayed() {
		return locations_label.isDisplayed();
	}

	public boolean exec_time_isDisplayed() {
		return exec_time.isDisplayed();
	}

	public boolean Add_btn_isDisplayed() {
		return Add_btn.isDisplayed();
	}

	public boolean print_btn_isDisplayed() {
		return print_btn.isDisplayed();
	}

	public boolean DeleteSelected_btn_isDisplayed() {
		return Delete_Selected_btn.isDisplayed();
	}

	public boolean exportIntocsv_btn_isDisplayed() {
		return export_into_csv_btn.isDisplayed();
	}

	public boolean Add_btn_isClickable() {
		return TestUtilities.isClickable(Add_btn);
	}

	public boolean DeleteSelected_btn_isClickable() {
		return TestUtilities.isClickable(Delete_Selected_btn);

	}

	public boolean print_btn_isClickable() {
		return TestUtilities.isClickable(print_btn);

	}

	public boolean memory_usage_isDisplayed() {
		return memory_usage.isDisplayed();
	}

	public boolean select_records(String... cities) {
		WebElement checkbox;
		boolean flag = false;
		for (String city : cities) {
			By locator = By.xpath("//tr/td[3][contains(text(),'" + city + "')]"
					+ "/preceding-sibling::td/div[@class='icheckbox_square-grey']");
			try {
				checkbox = driver.findElement(locator);
				if (!checkbox.isSelected()) {
					checkbox.click();
					flag = true;
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				flag = false;
			}

		}
		return flag;
	}

	// tr/td[3][contains(text(),'Kostroma')]/preceding-sibling::td/div[@class='icheckbox_square-grey']

	public boolean Click_DeleteSelected_btn_ClickCancel(String... cities) {
		select_records(cities);
		Delete_Selected_btn.click();
		driver.switchTo().alert().dismiss();
		return true;
	}

	public boolean Click_DeleteSelected_btn_ClickOK(String... cities) {
		select_records(cities);
		Delete_Selected_btn.click();
		driver.switchTo().alert().accept();
		return true;
	}

	public AddLocationPage EditLocation(String location) {
		By locator = By
				.xpath("//tr/td[3][contains(text(),'" + location + "')]/following-sibling::td/span/a[@title='Edit']");
		driver.findElement(locator).click();
		return new AddLocationPage();
	}

	public boolean deleteLocationByIcon_Click_OK(String location) {
		By locator = By
				.xpath("//tr/td[3][contains(text(),'" + location + "')]/following-sibling::td/span/a[@title='DELETE']");
		driver.findElement(locator).click();
		driver.switchTo().alert().accept();
		return true;
	}

	public boolean deleteLocationByIcon_Click_Cancel(String location) {
		By locator = By
				.xpath("//tr/td[3][contains(text(),'" + location + "')]/following-sibling::td/span/a[@title='DELETE']");
		driver.findElement(locator).click();
		driver.switchTo().alert().dismiss();
		return true;
	}

	public List<String> getLocationRecord(String City) {
		List<String> LocationRecord = new ArrayList<String>();
		btn_All.click();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int i = 1; i <= 4; i++) {
			By locator = By.xpath("//tr/td[3][contains(text(),'" + City + "')]/following-sibling::td[" + i + "]");
			try {
				if (i <= 3) {
					LocationRecord.add(driver.findElement(locator).getText());
				} else {

					if (driver.findElement(locator).getAttribute("class").equals("fa fa-check text-success"))
						LocationRecord.add("Enabled");
					else
						LocationRecord.add("Disabled");

				}

			} catch (NoSuchElementException e) {
				e.printStackTrace();

			}

		}
		return LocationRecord;
	}

}
