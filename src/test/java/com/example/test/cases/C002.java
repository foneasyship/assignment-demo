package com.example.test.cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class C002 {
    @Test
    public void testSecondCase() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");

        // Fill in username and password. Then, click Login button
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Click Admin menu item
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='app']/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a")));
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a")).click();

        // Click Job
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='app']/div[1]/div[1]/header/div[2]/nav/ul/li[2]")));
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div[1]/header/div[2]/nav/ul/li[2]")).click();

        // Click Pay Grades
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[2]/a")).click();
     
        // Click Add button
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button' and contains(., 'Add')]")));
        driver.findElement(By.xpath("//button[@type='button' and contains(., 'Add')]")).click();

        // Type Grade 8 in the Name field
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/input")));
		driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/input")).sendKeys("Grade 8");

        // Click Save button
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        
        // Verify Successfully added message
        // new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//p"), "Successfully Saved"));
		// assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Successfully Saved')]")).isDisplayed());
        
        // Click Add button in Assigned Currencies
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button' and contains(., 'Add')]")));
        driver.findElement(By.xpath("//button[@type='button' and contains(., 'Add')]")).click();

        // Click Currency dropdown
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div[2]/form/div[1]/div/div/div/div[2]/div/div")));
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div[2]/form/div[1]/div/div/div/div[2]/div/div")).click();
        // Set text Indian Rupees in the Currency field
        driver.findElement(By.xpath("//div[@role='listbox']/div[@role='option']/span[contains(text(),'INR - Indian Rupee')]")).click();
        
        // Type minimum salary as 30000
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div[2]/form/div[2]/div/div[1]/div/div[2]/input")).sendKeys("30000");

        // Type maximum salary 100000
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div[2]/form/div[2]/div/div[2]/div/div[2]/input")).sendKeys("100000");

        // Click Save button
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div[2]/form/div[3]/button[2]")).click();

        // Verify Successfully saved message
        // new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//p"), "Successfully Saved"));
		// assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Successfully Saved')]")).isDisplayed());

        // Verify all the above given input
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-card']/div/div[2]/div")));
        assertEquals("Indian Rupee", driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[2]/div")).getText());
        assertEquals("30000.00", driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[3]/div")).getText());
        assertEquals("100000.00", driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[4]/div")).getText());
        
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
