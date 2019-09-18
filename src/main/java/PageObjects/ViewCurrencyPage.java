package PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.ParentBase;



public class ViewCurrencyPage extends ParentBase {

	@FindBy(xpath = "//div/a[text()='Return']")
	private WebElement return_btn;

	@FindAll(@FindBy(xpath = "//td[@class='col-sm-9']"))
	private List<WebElement> record;

	public ViewCurrencyPage() {
		PageFactory.initElements(driver, this);
	}

	public CurrenciesManagementPage Click_Return_btn() {
		return_btn.click();
		return new CurrenciesManagementPage();
	}

	public List<String> getRecordValues() {
		
		List<String> ls = new ArrayList<String>();
		for (int i = 0; i < record.size(); i++) {
			ls.add(record.get(i).getText());
		}
		return ls;
	}
}
