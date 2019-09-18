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



public class AddCurrencyPage extends ParentBase {

	@FindBy(xpath = "//a[text()='Save & Return'")
	private WebElement Save_and_Return_btn;
	@FindBy(xpath = "//a[text()='Save & New'")
	private WebElement Save_and_New_btn;
	@FindBy(xpath = "//a[text()='Save & Edit'")
	private WebElement Save_and_Edit_btn;
	@FindBy(xpath = "//a[text()='Return'")
	private WebElement Return_btn;
	// a[text()='Save & New']
	// a[text()='Save & Edit']
	// a[text()='Return']
	@FindAll(@FindBy(xpath = "//input[@type='text']"))
	private List<WebElement> list;

	@FindBy(xpath = "//div[@class='xcrud-message error']")
	private WebElement error_msg;
	@FindBy(xpath = "//span[contains(text(),'Execution time')]")
	private WebElement exec_time;

	@FindBy(xpath = "//span[contains(text(),'Memory')]")
	private WebElement memory_usage;

	/*
	 * @FindBy(xpath = "//select[@data-type='select']") private WebElement
	 * Active;
	 */
	Select active_dropdown = new Select(driver.findElement(By.xpath("//select[@data-type='select']")));

	// select[@data-type='select']
	// input[@type='text']

	// input/attribute::readonly

	public AddCurrencyPage() {
		PageFactory.initElements(driver, this);

	}

	public CurrenciesManagementPage Click_Return_btn() {
		Return_btn.click();
		return new CurrenciesManagementPage();
	}
	
	public boolean exec_time_isDisplayed(){
		return exec_time.isDisplayed();
	}
	
	public boolean memory_usage_isDisplayed(){
		return memory_usage.isDisplayed();
	}

	/*
	 * Trying to check the combinations in the below over loaded methods when
	 * mandatory values are not given
	 * 
	 */
	/*
	 * public CurrenciesManagementPage Save_and_return_Currency(String Name,
	 * String Code, float Rate) { String rate = String.valueOf(Rate); // String
	 * active = getValueOfActiveDropdown(Active); list.get(2).clear();
	 * list.get(2).sendKeys(Name); list.get(4).clear();
	 * list.get(4).sendKeys(Code); list.get(5).clear();
	 * list.get(5).sendKeys(rate);
	 * 
	 * Save_and_Return_btn.click(); return new CurrenciesManagementPage();
	 * 
	 * }
	 */

	public CurrenciesManagementPage Save_and_return_Currency(String Name, String Symbol, String Code, String Rate,
			String Active) {
		// String rate = String.valueOf(Rate.intValue());
		// String active = getValueOfActiveDropdown(Active);
		/*
		 * list.get(2).clear(); list.get(2).sendKeys(Name); list.get(3).clear();
		 * list.get(3).sendKeys(Symbol); list.get(4).clear();
		 * list.get(4).sendKeys(Code); list.get(5).clear();
		 * list.get(5).sendKeys(Rate); // this.Active.sendKeys(active);
		 * active_dropdown.selectByVisibleText(Active);
		 */
		setData(Name, Symbol, Code, Rate, Active);
		Save_and_Return_btn.click();

		if (Name.isEmpty() && Code.isEmpty() && Rate.isEmpty()) {
			String err_msg = error_msg.getText();
			System.out.println(err_msg);
			return null;
		} else
			return new CurrenciesManagementPage();
	}

	public void Save_and_New(String Name, String Symbol, String Code, String Rate, String Active) {
		setData(Name, Symbol, Code, Rate, Active);
		Save_and_New_btn.click();

		if (Name.isEmpty() && Code.isEmpty() && Rate.isEmpty()) {
			String err_msg = error_msg.getText();
			System.out.println(err_msg);

		}

	}

	/*
	 * public void Save_and_return_Currency(String Name) {
	 * list.get(2).sendKeys(Name); Save_and_Return_btn.click(); }
	 * 
	 * public void Save_and_return_Currency(String Name, String Code) {
	 * list.get(2).clear(); list.get(2).sendKeys(Name); list.get(4).clear();
	 * list.get(4).sendKeys(Code); list.get(5).clear();
	 * Save_and_Return_btn.click(); }
	 */

	/*
	 * public String getValueOfActiveDropdown(boolean Active) { if (Active ==
	 * true) { return new String("Yes"); } else return new String("No");
	 * 
	 * }
	 */
	public List<String> getData() {
		List<String> ls_getData = new ArrayList<String>();
		for (int i = 2; i < 5; i++) {
			ls_getData.add(list.get(i).getText());
		}
		ls_getData.add(active_dropdown.getFirstSelectedOption().getText());
		return ls_getData;
	}

	public List<String> setData(String Name, String Symbol, String Code, String Rate, String Active) {
		List<String> ls = new ArrayList<String>();
		list.get(2).clear();
		list.get(2).sendKeys(Name);
		ls.add(Name);
		list.get(3).clear();
		list.get(3).sendKeys(Symbol);
		ls.add(Symbol);
		list.get(4).clear();
		list.get(4).sendKeys(Code);
		ls.add(Code);
		list.get(5).clear();
		list.get(5).sendKeys(Rate);
		ls.add(Rate);
		// this.Active.sendKeys(active);
		active_dropdown.selectByVisibleText(Active);
		ls.add(Active);
		
		return ls;
		
	}

	public String displayErrorMessage() {
		return error_msg.getText();
	}

}