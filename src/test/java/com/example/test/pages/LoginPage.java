package com.example.test.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.framework.pages.AbstractPage;

/**
 * Page Object encapsulates the Login page.
 */
public class LoginPage extends AbstractPage{
    protected WebDriver driver;

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
	private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
	private WebElement loginBtn;

    public LoginPage(WebDriver driver){
        super(driver);
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
    }

    public LoginPage typeUsername(String username) {
        log.info(String.format("fillUsername(%s)", username));
        usernameField.click();
        usernameField.clear();
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
		log.info(String.format("fillPassword(%s)", password));
		passwordField.click();
		passwordField.clear();
		passwordField.sendKeys(password);
		return this;
	}

    // The login page allows the user to submit the login form
    public DashboardPage clickLogin() {
        log.info("clickLogin()");
		loginBtn.click();
		return new DashboardPage(driver);	
    }
}
