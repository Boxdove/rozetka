package Pages;

//страница логина 

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends Page {

	private static String baseUrl = "http://rozetka.com.ua";
	private static String cartURL = "http://rozetka.com.ua/goclever_terra_70w/p388754/#cart";

	public HomePage(WebDriver driver) {
		super(driver);
		this.wdriver = driver;
	}

	@FindBy(xpath = "//a[@name='signin']")
	private WebElement signIn;

	@FindBy(xpath = "//div/input[@name='login']")
	private WebElement loginEnter;

	@FindBy(xpath = "//div/input[@name='password']")
	private WebElement parolEnter;

	@FindBy(xpath = "//div/button[@type='submit']")
	private WebElement submit;

	@FindBy(xpath = "//div/a[@name='signout']")
	private WebElement signOut;

	@FindBy(xpath = "//div[@id='cart_block96280']/div/a")
	private WebElement goToCart;

	public ShoppingCartPage loginSystem(String login, String password)
			throws InterruptedException {

		waitForElementFindBy(signIn);
		signIn.click();
		waitForElementFindBy(loginEnter);
		loginEnter.clear();
		loginEnter.sendKeys(login);
		waitForElementFindBy(parolEnter);
		parolEnter.clear();
		parolEnter.sendKeys(password);
		waitForElementFindBy(submit);
		submit.click();
		waitForElementFindBy(signOut);
		wdriver.get(cartURL);

		return PageFactory.initElements(wdriver, ShoppingCartPage.class);
	}

}
