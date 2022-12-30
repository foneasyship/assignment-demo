package com.example.test.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.framework.pages.AbstractPage;

/**
 * Page Object encapsulates the Directory page.
 */
public class DirectoryPage extends AbstractPage{
    protected WebDriver driver;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/header/div[1]/div[1]/span/h6")
    private WebElement pageTitle;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement employeeNameField;

    @FindBy(xpath = "//form/div[1]/div/div[3]/div/div[2]/div/div")
    private WebElement locationDropdown;
    
    @FindBy(xpath = "//button[contains(text(), 'Search')]")
	private WebElement searchBtn;

    @FindBys({@FindBy(xpath = "//div[@class='orangehrm-corporate-directory']//div[@class='oxd-grid-item oxd-grid-item--gutters']//p")})
    private List <WebElement> directoryCardHeaders;

    @FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
    private WebElement userDropdown;

    @FindBy(linkText = "Logout")
    private WebElement logout;

    public DirectoryPage(WebDriver driver){
        super(driver);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.textToBePresentInElement(pageTitle, "Directory"));
    }

    public DirectoryPage typeEmployeeName(String employeeName) {
        log.info(String.format("typeEmployeeName(%s)", employeeName));
        employeeNameField.click();
        employeeNameField.clear();
        employeeNameField.sendKeys(employeeName);
        return this;
    }

    public DirectoryPage selectEmployee(String employee) {
        log.info(String.format("selectEmployee(%s)", employee));
		locationDropdown.click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='listbox']")));
		driver.findElement(By.xpath("//div[@role='listbox']/*/span[contains(text(),'"+employee+"')]")).click();
		return this;
	}

    public DirectoryPage selectLocation(String location) {
        log.info(String.format("selectLocation(%s)", location));
		locationDropdown.click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='listbox']")));
		driver.findElement(By.xpath("//div[@role='listbox']/*/span[contains(text(),'"+location+"')]")).click();
		return this;
	}

    public DirectoryPage clickSearch() {
        log.info("clickSearch()");
		searchBtn.click();
		return this;	
    }

    public String[] getEmployeeNamesFromSearchResults() {
        log.info("getEmployeeNamesFromSearchResults()");
        String[] nameArr = new String[] {};
        for (int i=0; i<directoryCardHeaders.size(); i++) {
            nameArr[i] = directoryCardHeaders.get(i).getText();
        }
        return nameArr;
    }  

    public DirectoryPage clickUserDropdown() {
        log.info("clickUserDropdown()");
		userDropdown.click();
		return this;	
    }

    public DirectoryPage clickLogout() {
        log.info("clickLogout()");
		logout.click();
		return this;	
    }
}
