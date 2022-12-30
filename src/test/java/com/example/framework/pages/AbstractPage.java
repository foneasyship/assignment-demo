package com.example.framework.pages;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author fon
 *
 */
public abstract class AbstractPage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor executor;
	protected final Logger log = Logger.getLogger(getClass());

	public AbstractPage(WebDriver driver) {
		this.driver = driver;
		this.executor = (JavascriptExecutor) driver;
		this.wait = new WebDriverWait(driver, Duration.ofMinutes(15));

		// Initialise Elements
		PageFactory.initElements(driver, this);
	}

	protected boolean isElementPresent(WebElement element) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		}
	}

	protected boolean isElementPresent(By by) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		}
	}

	protected void scrollIntoView(WebElement element) {
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	protected void click(WebElement element) {
		executor.executeScript("arguments[0].click();", element);
	}

	protected void focus(WebElement element) {
		Actions builder = new Actions(driver);
		if ("input".equals(element.getTagName())) {		
			builder.moveToElement(element).click().perform();
		} else {
			builder.moveToElement(element).build().perform();
		}
	}

	public void openNewTab() {
		executor.executeScript("window.open()");
	}

	public void closePage() {
		driver.close();
	}

}