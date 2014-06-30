package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage extends Page {

	protected WebDriver wdriver;

	private String baseUrl = "http://rozetka.com.ua";
	private static String cartURL = "http://rozetka.com.ua/goclever_terra_70w/p388754/#cart";
	
	private By placeOrd = By.xpath("//button[@id = 'popup-checkout']");
	private String getPrice;

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
		this.wdriver = driver;
	}

	@FindBy(id = "reciever_name")
	private WebElement receiverName;

	@FindBy(id = "suggest_locality")
	private WebElement receiverLocality;

	@FindBy(css = "li[name=\"0\"]")
	private WebElement city;

	@FindBy(id = "reciever_phone")
	private WebElement receiverPhone;

	@FindBy(xpath = "//button[contains(text(),'Далее')]")
	private WebElement submitButton;

	@FindBy(xpath = "//div[@name='total']//div[@class='uah']")
	private WebElement sumOfOrder;

	@FindBy(xpath = "//button[@id = 'popup-checkout']")
	private WebElement placeAnOrder;

	@FindBy(xpath = "//div[@class='cell delete']/a/img")
	private WebElement deleteButton;

	@FindBy(xpath = "//div[@id='cart-popup']/div/a/img")
	private WebElement closeButton;

	@FindBy(xpath = "//div/h1[@class='personal-info-title']")
	private WebElement settings;

	@FindBy(xpath = "//div[@id='cart-popup']/div")
	private WebElement cartBlock;
	
	@FindBy(xpath = "//span[contains(text(),'График работы')]")
	private WebElement schedule; 

	@FindBy (xpath = "//input[@name='close_button']")
	private WebElement OKbutton; 
	
	public AfterByingPage buyingProduct() {
		toPopup(wdriver);
		try {
			waitForElementFindBy(placeAnOrder);
			placeAnOrder.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			waitForElementFindBy(receiverName);
			receiverName.clear();
			receiverName.sendKeys("Зоя");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			waitForElementFindBy(receiverLocality);
			receiverLocality.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			waitForElementFindBy(city);
			city.click();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			waitForElementFindBy(receiverPhone);
			receiverPhone.clear();
			receiverPhone.sendKeys("0634444444");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			waitForElementFindBy(submitButton);
			submitButton.click();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return PageFactory.initElements(wdriver, AfterByingPage.class);
	}

	public ProductPage cleaningTheCart() {
		toPopup(wdriver);

		outer: while (true) {
			waitForElementFindBy(cartBlock);
			if (wdriver.getPageSource().contains("Корзина пуста")) {
				wdriver.get(baseUrl);
				return PageFactory.initElements(wdriver, ProductPage.class);
			} else {
				try {
					waitForElementFindBy(deleteButton);
					deleteButton.click();
				} catch (Exception e) {
					e.printStackTrace();
				}
				wdriver.switchTo().window(parentWindowHandler);
				wdriver.get(cartURL);
				toPopup(wdriver);
				continue outer;
			}
		}
	}
}
