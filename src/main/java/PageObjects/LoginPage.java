package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import Base.ParentBase;

public class LoginPage extends ParentBase {


	@FindBy(name="email")
	private WebElement username;
	@FindBy(name="password")
	private WebElement password;
	@FindBy(xpath="//button[@type='submit']")
	private WebElement login_btn;
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	public AdminConsole ClickLoginButton(){
		
		username.sendKeys(prop.getProperty("username"));
		password.sendKeys(prop.getProperty("password"));
		login_btn.click();
		return new AdminConsole();
	}
	

}
