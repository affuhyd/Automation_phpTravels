package PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Base.ParentBase;



public class AddLocationPage extends ParentBase {

	@FindBy(id = "s2id_autogen1")
	private WebElement Country_dropdown;
	@FindBy(xpath = "//select[@name='country']")
	private WebElement country_selection_textbox;

	@FindBy(name = "city")
	private WebElement Location;
	@FindBy(name="longitude")
	private WebElement longitude;
	@FindBy(name="latitude")
	private WebElement latitude;

	@FindBy(xpath = "//div[@class='pac-item'][1]")
	private WebElement select_Location;
	// div[@class='pac-item'][1]

	Select country = new Select(country_selection_textbox);

	Select status = new Select(driver.findElement(By.xpath("//select[@name='status']")));
	// *[@id="GENERAL"]/div/label/text()

	@FindAll(@FindBy(xpath = "//div[@class='col-md-4']/input[@type='text' and @placeholder='Name']"))
	private List<WebElement> language_textboxes;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submit_btn;
	// div/label/*[text()='Russian']
	// input[@type='text']

	public AddLocationPage() {
		PageFactory.initElements(driver, this);
	}

	public LocationsListPage ClickSubmit_btn() {
		submit_btn.click();
		return new LocationsListPage();
	}

	/*
	 * city, country, latitude, longitude, status
	 * 
	 */
	public List<String> setData(String Country, String Location, String Status, String Russian, String Farsi,
			String German, String French, String Turkish, String Arabic, String Spanish) {

		List<String> ls = new ArrayList<String>();
		if (Country.isEmpty() && Location.isEmpty()) {
			return ls;
		} else {
			Country_dropdown.click();
			if (country_selection_textbox.isDisplayed()) {
				country.selectByVisibleText(Country);
				
			}
			this.Location.sendKeys(Location);
			if (select_Location.isDisplayed()) {
				select_Location.click();
				ls.add(Location);
				ls.add(Country);
				ls.add(latitude.getText());
				ls.add(longitude.getText());
			}
			this.status.selectByVisibleText(Status);
			ls.add(Status);

			language_textboxes.get(0).clear();
			language_textboxes.get(0).sendKeys(Russian);
			ls.add(Russian);
			language_textboxes.get(0).clear();
			language_textboxes.get(1).sendKeys(Farsi);
			ls.add(Farsi);
			language_textboxes.get(0).clear();
			language_textboxes.get(2).sendKeys(German);
			ls.add(German);
			language_textboxes.get(0).clear();
			language_textboxes.get(3).sendKeys(French);
			ls.add(French);
			language_textboxes.get(0).clear();
			language_textboxes.get(4).sendKeys(Turkish);
			ls.add(Turkish);
			language_textboxes.get(0).clear();
			language_textboxes.get(5).sendKeys(Arabic);
			ls.add(Arabic);
			language_textboxes.get(0).clear();
			language_textboxes.get(6).sendKeys(Spanish);
			ls.add(Spanish);
		}
		return ls;
	}
}
