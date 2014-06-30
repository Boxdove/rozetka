package Pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogPage extends Page {

	private String getPrice;
	private String priceWithoutSplash;
	private String baseUrl;

	int sumCounted;
	int totalSum = 0;

	protected WebDriver wdriver;

	private By placeAnOrderButton = By
			.xpath("//button[@id = 'popup-checkout']))).clear()");

	public CatalogPage(WebDriver driver) {
		super(driver);
		this.wdriver = driver;
	}

	@FindBy(name = "topurchasesfromcatalog")
	private WebElement buyButton;

	@FindBy(name = "topurchasesfromcatalog")
	List<WebElement> buyButtons;

	@FindBy(xpath = "//button[@id = 'popup-checkout']")
	private WebElement placeAnOrder;

	@FindBy(xpath = "//div[@name='total']//div[@class='uah']")
	private WebElement price;

	// добавление одного продукта
	public ShoppingCartPage buyOneItem() throws InterruptedException {
		baseUrl = "http://rozetka.com.ua";
		try {
			waitForElementFindBy(buyButton);
			buyButton.click();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return PageFactory.initElements(wdriver, ShoppingCartPage.class);
	}

	// больше определенной суммы
	public ShoppingCartPage sumMoreThan(int sum) throws InterruptedException {
		String baseUrl = wdriver.getCurrentUrl();
		waitForElementFindBy(buyButton);
		int numElem = buyButtons.size();
		if (numElem == 0) {
			System.out.println("кнопок нет");
		} else {
			a: while (true) {
				try {
					waitForElementFindBy(buyButton);
					buyButton.click();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					waitForElementFindBy(price);
				} catch (Exception e) {
					e.printStackTrace();
				}
				getPrice = price.getText();
				gettingCorrectSum(getPrice);
				priceWithoutSplash = getPrice.replaceAll("[\\s]{1,}", "");
				Pattern p = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
				Matcher m = p.matcher(priceWithoutSplash);
				while (m.find()) {
					totalSum = Integer.parseInt(m.group());
					System.out.print(totalSum);
					System.out.println();
				}
				if (totalSum >= sum) {
					System.out.println("sum more than we pointed");
					String parentWindowHandler = wdriver.getWindowHandle();
					String subWindowHandler = null;

					Set<String> handles = wdriver.getWindowHandles();
					Iterator<String> iterator = handles.iterator();
					while (iterator.hasNext()) {
						subWindowHandler = iterator.next();
					}
					return PageFactory.initElements(wdriver, ShoppingCartPage.class);
				} else {
					wdriver.get(baseUrl);
					continue a;
				}
			}
		}
		return PageFactory.initElements(wdriver, ShoppingCartPage.class);

	}
	
}
