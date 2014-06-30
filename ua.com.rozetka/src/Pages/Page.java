package Pages;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

	protected WebDriver wdriver;

	private String getPrice;
	protected String parentWindowHandler;
	protected String subWindowHandler;
	private String priceWithoutSplash;

	int sumCounted;
	int totalSum = 0;

	private String baseUrl = "http://rozetka.com.ua/tablets/c130309/filter/preset=128/";// нужно

	public Page(WebDriver driver) {
		this.wdriver = driver;
	}

	public Page isElementPresent(WebElement element) {
		Assert.assertTrue(element + "  should be present",
				element.isDisplayed());
		return this;
	}

	public Page isElementEnabled(WebElement element) {
		Assert.assertTrue(element + "  should be present",
				element.isDisplayed());
		return this;
	}

	public Page moveToElement(WebElement element) {
		Actions actions = new Actions(wdriver);
		actions.moveToElement(element).build().perform();
		return this; 
	}

	public void waitForPageLoading() {
		wdriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}

	public Page waitForElementFindBy(WebElement element) {
		WebDriverWait wait = new WebDriverWait(wdriver, 15, 1000);
		wait.until(ExpectedConditions.visibilityOf(element));
		return this;
	}

	public Page waitForElementIsInvisible(By locator) {
		WebDriverWait wait = (new WebDriverWait(wdriver, 15, 1000));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		return this;
	}

	public Page waitForTextPresence(WebElement element, String text) {
		WebDriverWait wait = new WebDriverWait(wdriver, 15, 1000);
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
		return this;
	}

	public int gettingCorrectSum(String getPrice) {
		priceWithoutSplash = getPrice.replaceAll("[\\s]{1,}", "");
		Pattern p = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
		Matcher m = p.matcher(priceWithoutSplash);
		while (m.find()) {
			totalSum = Integer.parseInt(m.group());
		}
		return totalSum;
	}

	public void toPopup(WebDriver driver) {
		parentWindowHandler = wdriver.getWindowHandle();
		subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
	}

}