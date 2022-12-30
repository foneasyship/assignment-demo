package com.example.test.cases;


import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class C001 {

    @Test
    public void testFirstCase() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        
        /* 
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername("Admin");
        loginPage.typePassword("admin123");
        DashboardPage dashboardPage = loginPage.clickLogin();
        DirectoryPage directoryPage = dashboardPage.clickDirectoryMenu();
        directoryPage.typeEmployeeName("ch");
        directoryPage.selectEmployee("Dominic Chase");
        directoryPage.selectLocation("USA");
        directoryPage.clickSearch();
        String[] employeeNames = directoryPage.getEmployeeNamesFromSearchResults();

        for (int i=0; i<employeeNames.length; i++) {
            assertTrue(employeeNames[i].contains("ch"));
        }
        */

        // Fill in username and password. Then, click Login button
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Click Directory menu item
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='app']/div[1]/div[1]/aside/nav/div[2]/ul/li[9]/a")));
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div[1]/aside/nav/div[2]/ul/li[9]/a")).click();
     
        // Key in "ch" to Employee name field
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type for hints...']")));
        WebElement employeeNameField = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
        employeeNameField.sendKeys("ch");

        // Wait for Employee suggestion list pops up and select "Dominic Chase" from the list
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='listbox']/div[@role='option']")));
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='listbox']/div/span")));
		driver.findElement(By.xpath("//div[@role='listbox']/div[2]/span")).click();

        // Click Location dropdown and select USA option
        driver.findElement(By.xpath("//form/div[1]/div/div[3]/div/div[2]/div/div")).click();
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='listbox']")));
		driver.findElement(By.xpath("//div[@role='listbox']/div[@role='option']/span[contains(text(),'USA')]")).click();
        
        // Click Search button
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Get all search results and verify each Employee name
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='orangehrm-corporate-directory']//div[@class='oxd-grid-item oxd-grid-item--gutters']")));
        List<WebElement> directoryCardHeaders = driver.findElements(By.xpath("//div[@class='orangehrm-corporate-directory']//div[@class='oxd-grid-item oxd-grid-item--gutters']//p[contains(@class, 'orangehrm-directory-card-header')]"));

        for (int i=0; i<directoryCardHeaders.size(); i++) {
            assertTrue(directoryCardHeaders.get(i).getText().toLowerCase().contains("ch"));
        }

        // Click User dropdown and click Logout
        driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();;
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}