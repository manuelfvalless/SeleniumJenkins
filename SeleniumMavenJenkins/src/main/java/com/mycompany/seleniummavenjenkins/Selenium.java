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
    //System.setProperty("webdriver.gecko.driver","Browser//geckodriver.exe");
    //System.setProperty("webdriver.gecko.driver","Browser//geckodriver.exe");
    //driver = new FirefoxDriver();
    //driver =new PhantomJSDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testPrueba() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.id("lst-ib")).clear();
    driver.findElement(By.id("lst-ib")).sendKeys("seleniumhq");
    driver.findElement(By.xpath("//*[@id=\"hplogo\"]")).click();
    driver.findElement(By.name("btnK")).click();
    driver.findElement(By.xpath("//*[@id=\"rso\"]/div/div/div[1]/div/div/h3/a")).click();
    try {
      assertEquals(driver.findElement(By.xpath("//*[@id=\"mainContent\"]/h2[1]")).getText(), "What is Selenium?");
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