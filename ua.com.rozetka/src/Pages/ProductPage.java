package Pages;

//страница перехода в каталог нужных товаровж

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends Page {

	private String baseUrl = "http://rozetka.com.ua";

	public ProductPage(WebDriver driver) {
		super(driver);
		this.wdriver = driver;
	}

	@FindBy(xpath = ".//a[contains(text(),'Планшеты')]")
	private WebElement tables;

	@FindBy(xpath = ".//li[4]//li[1]/a[@class='m-cat-subl-i-link']")
	private WebElement smallestPrice;

	@FindBy(xpath = ".//a[contains(text(),'СУБД')]")
	private WebElement DataBases;

	@FindBy(xpath = "//td[@id='computers-notebooks']/div/a[1]")
	private WebElement PC;

	public CatalogPage openCatalogPage() throws InterruptedException {
		try {
			waitForElementFindBy(tables);
			tables.click();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			waitForElementFindBy(smallestPrice);
			smallestPrice.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PageFactory.initElements(wdriver, CatalogPage.class);
	}

	public CatalogPage openCatalogOfHugeProductPage()
			throws InterruptedException {
		wdriver.get(baseUrl);
		try {
			moveToElement(PC);
			waitForElementFindBy(DataBases);
			DataBases.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PageFactory.initElements(wdriver, CatalogPage.class);
	}

}
