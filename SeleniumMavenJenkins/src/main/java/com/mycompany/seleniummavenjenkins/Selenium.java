package com.mycompany.seleniummavenjenkins;


import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

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
    //System.setProperty("phantomjs.binary.path","Browser//phantomjs.exe");
    driver = new FirefoxDriver();
    //driver =new PhantomJSDriver();
    baseUrl = "http://54.235.81.157/carteratest/seclogin.aspx";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testPrueba() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.id("vUSUARIONOMBRE")).clear();
    driver.findElement(By.id("vUSUARIONOMBRE")).sendKeys("mvalles");
    driver.findElement(By.id("vUSUARIOPASSWORD")).clear();
    driver.findElement(By.id("vUSUARIOPASSWORD")).sendKeys("Test01.");
    driver.findElement(By.name("BTNENTER")).click();
    driver.findElement(By.linkText("Administración Medios de Pago")).click();
    driver.findElement(By.id("vVERBITACORA_0001")).click();
    try {
      assertEquals(driver.findElement(By.id("span_W0016W0037BITACORAMANDATOSPACPATACCION_0001")).getText(), "Activación");
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