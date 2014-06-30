package Pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AfterByingPage extends Page {

	protected WebDriver wdriver;

	public AfterByingPage(WebDriver driver) {
		super(driver);
		this.wdriver = driver;
	}

	@FindBy(xpath = "//a[contains (text(),'Курьер')]")
	private WebElement courier;

	@FindBy(xpath = "//div[@class='clearfix bill-i'][2]")
	private WebElement shippingPrice;

	@FindBy(xpath = "//div/aside[@id='total_block']")
	private WebElement infoBlock;

	public AfterByingPage checkShippingPrice(String shipping) {
		waitForElementFindBy(courier);
		courier.click();
		waitForElementFindBy(shippingPrice);
		assertEquals(shippingPrice.getText(), shipping);
		return PageFactory.initElements(wdriver, AfterByingPage.class);
	}

	public AfterByingPage checkGiftPresence(String shipping) {
		waitForElementFindBy(courier);
		courier.click();
		waitForElementFindBy(infoBlock);
		assertEquals(shippingPrice.getText(), shipping);
		return PageFactory.initElements(wdriver, AfterByingPage.class);
	}
}