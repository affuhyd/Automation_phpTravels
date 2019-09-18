package PageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Base.ParentBase;
import Utilities.TestUtilities;

public class CurrenciesManagementPage extends ParentBase {
	@FindBy(xpath = "//div[text()='Currencies Management']")
	private WebElement Currency_Mgmt_header;
	@FindBy(xpath = "//label[text()='Currency API Keys']")
	private WebElement Currency_APIKeys_label;
	@FindBy(name = "key")
	private WebElement Currency_APIKeys;
	@FindBy(xpath = "//label[text()='Cronjob URL']")
	private WebElement Cronjob_url_label;
	@FindBy(id="link")
	private WebElement Cronjob_url;
	@FindBy(xpath = "//button[contains(text(),'Copy')]")
	private WebElement Copy_btn;
	@FindBy(xpath = "//a[contains(text(),'Refresh')]")
	private WebElement Refresh_btn;
	@FindBy(xpath = "//a[contains(text(),'Add') and @data-task='create']")
	private WebElement Add_btn;
	@FindBy(xpath = "//a[@data-task = 'print']")
	private WebElement print_btn;
	@FindBy(xpath = "//a[@data-task = 'csv']")
	private WebElement export_into_csv_btn;
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

	@FindBy(xpath = "//tr/th[contains(text(),'Name')]")
	private WebElement Name_header;
	@FindBy(xpath = "//tr/th[contains(text(),'Symbol')]")
	private WebElement Symbol_header;
	@FindBy(xpath = "//tr/th[contains(text(),'Code')]")
	private WebElement Code_header;
	@FindBy(xpath = "//tr/th[contains(text(),'Rate')]")
	private WebElement Rate_header;
	@FindBy(xpath = "//tr/th[contains(text(),'Active')]")
	private WebElement Active_header;
	@FindBy(xpath = "//tr/th[contains(text(),'Default')]")
	private WebElement Default_header;

	@FindBy(xpath = "//span[contains(text(),'Execution time')]")
	private WebElement exec_time;

	@FindBy(xpath = "//span[contains(text(),'Memory')]")
	private WebElement memory_usage;

	Select AllFields_dropdown = new Select(driver.findElement(By.name("column")));

	// a[text()='Search']
	public CurrenciesManagementPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean Currency_Mgmt_header_isDisplayed() {
		return Currency_Mgmt_header.isDisplayed();
	}

	public boolean Currency_APIKeys_label_isDisplayed() {
		return Currency_APIKeys_label.isDisplayed();
	}

	public boolean Currency_APIKeys_isEnabled() {
		return Currency_APIKeys.isEnabled();
	}

	public boolean Cronjob_url_label_isDisplayed() {
		return Cronjob_url_label.isDisplayed();
	}

	public boolean Cronjob_url_isEnabled() {
		return Cronjob_url.isEnabled();
	}

	public boolean Copy_btn_isClickable() {
		return TestUtilities.isClickable(Copy_btn);
	}

	public boolean Refresh_button_isClickable() {
		return TestUtilities.isClickable(Refresh_btn);
	}

	public boolean Add_btn_isClickable() {
		return TestUtilities.isClickable(Add_btn);
	}

	public void Click_All_btn() {
		btn_All.click();
	}

	public boolean print_btn_isClickable() {
		return TestUtilities.isClickable(print_btn);
	}

	public boolean exportIntoCsv_btn_isClickable() {
		return TestUtilities.isClickable(export_into_csv_btn);
	}

	public boolean btn25_isClickable() {
		return TestUtilities.isClickable(btn_25);
	}

	public boolean btn50_isClickable() {
		return TestUtilities.isClickable(btn_50);
	}

	public boolean btn100_isClickable() {
		return TestUtilities.isClickable(btn_100);
	}

	public boolean btnAll_isClickable() {
		return TestUtilities.isClickable(btn_All);
	}

	public boolean btnSearch_isClickable() {
		return TestUtilities.isClickable(btn_search);
	}

/*	public void Click_All_btn() {
		btn_All.click();
	}

	public void Click_All_btn() {
		btn_All.click();*/


	public AddCurrencyPage Click_Add_btn() {
		Add_btn.click();
		return new AddCurrencyPage();
	}

	public ViewCurrencyPage Click_View_btn(String Code) {
		btn_All.click();
		By locator = By
				.xpath("//tr/td[5][contains(text(),'" + Code + "')]/following-sibling::td/span/a[@title='View']");
		driver.findElement(locator).click();
		return new ViewCurrencyPage();
	}

	// tr/td[3][contains(text(),'Kostroma')]/following-sibling::td/span/a[@title='Remove']

	public AddCurrencyPage Click_Edit_btn(String Code) {
		btn_All.click();
		By locator = By
				.xpath("//tr/td[5][contains(text(),'" + Code + "')]/following-sibling::td/span/a[@title='Edit']");
		driver.findElement(locator).click();
		return new AddCurrencyPage();
	}

	public void DeleteCurrencyRecord_Click_OK_btn(String Code) {
		btn_All.click();
		By locator = By
				.xpath("//tr/td[5][contains(text(),'" + Code + "')]/following-sibling::td/span/a[@title='Remove']");
		driver.findElement(locator).click();
		driver.switchTo().alert().accept();

	}

	public void DeleteCurrencyRecord_Click_Cancel_btn(String Code) {
		btn_All.click();
		By locator = By
				.xpath("//tr/td[5][contains(text(),'" + Code + "')]/following-sibling::td/span/a[@title='Remove']");
		driver.findElement(locator).click();
		driver.switchTo().alert().dismiss();

	}

	// tr/td[5][contains(text(),'USD')]/following-sibling::td/span/a[@title='Remove']

	public boolean selectCurrencyAsDefault(String Code) {
		btn_All.click();
		By locator = By.xpath("//tr/td[5][contains(text(),'" + Code + "')]/following-sibling::td/span/i");
		driver.findElement(locator).click();
		driver.switchTo().alert().accept();
		
		return true;
	}

	public boolean selectCurrencyAsDefault_Cancel(String Code) {
		btn_All.click();
		By locator = By.xpath("//tr/td[5][contains(text(),'" + Code + "')]/following-sibling::td/span/i");
		driver.findElement(locator).click();
		driver.switchTo().alert().dismiss();
		return true;
	}

	public void searchByAllFields(String text){
		btn_search.click();
		search_textbox.sendKeys(text);
		btn_Go.click();
		
	}
	
	public void searchByName(String text){
		btn_search.click();
		search_textbox.sendKeys(text);
		AllFields_dropdown.selectByVisibleText("Name");
		btn_Go.click();
		
	}
	public void searchByCode(String text){
		btn_search.click();
		search_textbox.sendKeys(text);
		AllFields_dropdown.selectByVisibleText("Code");
		btn_Go.click();
		
	}
	public void searchByRate(String text){
		btn_search.click();
		search_textbox.sendKeys(text);
		AllFields_dropdown.selectByVisibleText("Rate");
		btn_Go.click();
		
	}
	public void searchByActive(String text){
		btn_search.click();
		search_textbox.sendKeys(text);
		AllFields_dropdown.selectByVisibleText("Active");
		btn_Go.click();
		
	}
	
	public void searchBySymbol(String text){
		btn_search.click();
		search_textbox.sendKeys(text);
		AllFields_dropdown.selectByVisibleText("Symbol");
		btn_Go.click();
		
	}
	
	public boolean exec_time_isDisplayed(){
		return exec_time.isDisplayed();
	}
	
	public boolean memory_usage_isDisplayed(){
		return memory_usage.isDisplayed();
	}
	public List<String> sort_Records_ByNameHeader(){
		Name_header.click();
		List<WebElement> record = driver.findElements(By.xpath("//tr/td[3]"));
		List<String> ls = null;
		for(int i=0; i<record.size();i++){
			 ls.add(record.get(i).getText());
		}
		
	return ls;
	}
	
	public List<String> sort_Records_BySymbolHeader(){
		Symbol_header.click();
		List<WebElement> record = driver.findElements(By.xpath("//tr/td[4]"));
		List<String> ls = new ArrayList<String>();
		for(int i=0; i<record.size();i++){
			 ls.add(record.get(i).getText());
		}
		
	return ls;
	}
	
	public List<String> sort_Records_ByCodeHeader(){
		Code_header.click();
		List<WebElement> record = driver.findElements(By.xpath("//tr/td[5]"));
		List<String> ls = new ArrayList<String>();
		for(int i=0; i<record.size();i++){
			 ls.add(record.get(i).getText());
		}
		
	return ls;
	}
	
	public List<String> sort_Records_ByRateHeader(){
		Rate_header.click();
		List<WebElement> record = driver.findElements(By.xpath("//tr/td[6]"));
		List<String> ls = new ArrayList<String>();
		for(int i=0; i<record.size();i++){
			 ls.add(record.get(i).getText());
		}
		
	return ls;
	}
	
	public List<String> sort_Records_ByActiveHeader(){
		Active_header.click();
		//driver.manage().timeouts().pageLoadTimeout(TestUtilities.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			//TestUtilities.dynamicFluentWait();
		List<WebElement> record = new ArrayList<WebElement>();
		TestUtilities.avoid_StaleElementReferenceException(driver, record);
		record = driver.findElements(By.xpath("//tr/td[7]"));
		List<String> ls = new ArrayList<String>();
		for(int i=0; i<record.size();i++){
			 ls.add(record.get(i).getText());
		}
		
	return ls;
	}
	
	public List<String> getCurrencyRecord(String Code) {
		List<String> CurrencyRecord = new ArrayList<String>();
		btn_All.click();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int i = 3; i <= 7; i++) {
			By locator = By.xpath("//tr/td[5][contains(text(),'" + Code + "')]/parent::tr/td[" + i + "]");
			try {

				CurrencyRecord.add(driver.findElement(locator).getText());

			} catch (NoSuchElementException e) {
				e.printStackTrace();

			}

		}
		return CurrencyRecord;
	}

}
