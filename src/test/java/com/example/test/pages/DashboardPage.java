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
 * Page Object encapsulates the Dashboard page.
 */
public class DashboardPage extends AbstractPage{
    protected WebDriver driver;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/header/div[1]/div[1]/span/h6")
    private WebElement pageTitle;

    @FindBy(xpath = "//a[@class='oxd-main-menu-item']//span[text()='Directory']")
    private WebElement directoryMenu;

    public DashboardPage(WebDriver driver) {
        super(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='app']/div[1]/div[1]/aside/nav/div[2]/ul/li[9]/a")));
    }

    public DirectoryPage clickDirectoryMenu() {
        log.info("clickDirectoryMenu()");
		directoryMenu.click();
		return new DirectoryPage(driver);	
    }
}
