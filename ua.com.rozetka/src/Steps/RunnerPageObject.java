package Steps;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import Pages.*;

public class RunnerPageObject {

	protected WebDriver driver;
	private static String login = "bot312@i.ua";
	private static String password = "test123456";
	private String shippingOneItem = "35 грн";
	private String shippingMoreThan1500 = "бесплатно";
	private String shippingMoreThan20000 = "подарок";

	private String baseUrl = "http://rozetka.com.ua";

	private ProductPage productPage;
	private CatalogPage catalogPage;
	private AfterByingPage afterLoginPage;
	private HomePage homePage;
	private ShoppingCartPage shoppingCartPage;

	//

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}

//	@Test
//	public void shoppingCartOneItem() throws Exception {
//		homePage = PageFactory.initElements(driver, HomePage.class);
//		shoppingCartPage = homePage.loginSystem(login, password);
//		productPage = shoppingCartPage.cleaningTheCart();
//		catalogPage = productPage.openCatalogPage();
//		shoppingCartPage = catalogPage.buyOneItem();
//		afterLoginPage =shoppingCartPage.buyingProduct();
//		afterLoginPage.checkShippingPrice(shippingOneItem);
//
//	}

	@Test
	public void shopimgCartMoreThan1500() throws Exception {
		homePage = PageFactory.initElements(driver, HomePage.class);
		shoppingCartPage = homePage.loginSystem(login, password);
		productPage = shoppingCartPage.cleaningTheCart();
		catalogPage = productPage.openCatalogPage();
		shoppingCartPage = catalogPage.sumMoreThan(1500);
		afterLoginPage = shoppingCartPage.buyingProduct();
		afterLoginPage.checkShippingPrice(shippingMoreThan1500);
	}

//	@Test
//	public void shopimgCartMoreThan20000() throws Exception {
//		homePage = PageFactory.initElements(driver, HomePage.class);
//		shoppingCartPage = homePage.loginSystem(login, password);
//		productPage = shoppingCartPage.cleaningTheCart();
//		catalogPage = productPage.openCatalogOfHugeProductPage();
//		shoppingCartPage = catalogPage.sumMoreThan(20000);
//		afterLoginPage =shoppingCartPage.buyingProduct();
//		afterLoginPage.checkShippingPrice(shippingMoreThan20000);
//	}


	@After
	public void tearDown() throws Exception {
		driver.close();
	}
}
