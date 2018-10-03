package com.mycompany.seleniummavenjenkins;

import java.awt.Robot;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
/**
 *
 * @author ManuelV
 */
public class Selenium {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    
    @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    System.setProperty("webdriver.gecko.driver","Browser//geckodriver.exe");
    driver = new FirefoxDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testPrueba() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='selenium'])[1]/following::b[1]")).click();
    driver.findElement(By.id("lst-ib")).clear();
    driver.findElement(By.id("lst-ib")).sendKeys("selenium");
    driver.findElement(By.linkText("Selenium WebDriver")).click();
    driver.findElement(By.id("container")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='supports the Selenium project'])[1]/following::h2[1]")).click();
    try {
      assertEquals(driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='supports the Selenium project'])[1]/following::h2[1]")).getText(), "Selenium WebDriver");
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
    
    public static void main(String[] args) throws Exception {
        Selenium selenium = new Selenium();
        selenium.setUp();
        selenium.testPrueba();
        selenium.tearDown();
    }    
}