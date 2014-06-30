package Steps;


import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.support.PageFactory;

import Base.webdriverActions;
import Pages.*; 
public class RozetkaSteps extends webdriverActions {
	public RozetkaSteps (){
		super(); 
	}
	
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

	@Given("main page rozetka.com.ua")
	public void givenMainPage() {
		_driver.get(baseUrl);
		_driver.manage().window().maximize();
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
	}

	@When("user has entered to the rozetka.com.ua")
	public void loginSystem() throws InterruptedException {
		shoppingCartPage = homePage.loginSystem(login, password);
	}
    
	@When ("shopping cart is cleared")
	public void cleaningTheCart(){ 
		productPage=shoppingCartPage.cleaningTheCart();
	}
	
	@When("user goes to the nessessary products page for choosing")
	public void goingToTheProductPage() throws InterruptedException  {
		catalogPage = productPage.openCatalogPage();
		catalogPage.waitForPageLoading();
	} 
	
	@When("user goes to the nessessary huge products page for choosing")
	public void goingToTheHugeProductPage() throws InterruptedException  {
		catalogPage = productPage.openCatalogOfHugeProductPage();
		catalogPage.waitForPageLoading();
	} 
	
	@When("total sum in shopping cart is less than 1500 grn")
	public void totalSumLessThan1500() throws InterruptedException  {
		shoppingCartPage = catalogPage.buyOneItem();
	}
	
	@When("total sum in shopping cart is more than 1500 grn")
	public void totalSumMoreThan1500() throws InterruptedException {
		shoppingCartPage = catalogPage.sumMoreThan(1500);
	}
	
	@When("total sum in shopping cart is more than 20000 grn")
	public void totalSumMoreThan20000() throws InterruptedException {
		shoppingCartPage = catalogPage.sumMoreThan(20000);
	}

	@Then("shipping price is 35 grn")
	public void gettingSumLess1500() {
		afterLoginPage =shoppingCartPage.buyingProduct();
		afterLoginPage.checkShippingPrice(shippingOneItem);
	}
	
	@Then("shipping price is 0 grn")
	public void gettingSumMoreThan1500() {
		afterLoginPage = shoppingCartPage.buyingProduct();
		afterLoginPage.checkShippingPrice(shippingMoreThan1500);
	}
	
	@Then("gift is added to the shopping cart")
	public void gettingSumMoreThan20000() {
		afterLoginPage = shoppingCartPage.buyingProduct();
		afterLoginPage.checkGiftPresence(shippingMoreThan1500);
	}

}
