package demoJenkins;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

public class Script {

	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		driver = new HtmlUnitDriver();
		baseUrl = "http://54.235.81.157/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testCartera2() throws Exception {
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
		java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
		driver.get(baseUrl + "/carteratest/seclogin.aspx");
		driver.findElement(By.id("vUSUARIONOMBRE")).clear();
		driver.findElement(By.id("vUSUARIONOMBRE")).sendKeys("mvalles");
		driver.findElement(By.id("vUSUARIOPASSWORD")).clear();
		driver.findElement(By.id("vUSUARIOPASSWORD")).sendKeys("Test01.");
		driver.findElement(By.name("BTNENTER")).click();
		driver.findElement(By.xpath("//span/a")).click();
		driver.findElement(By.id("vINFOCLIENTE1_0001")).click();
		try {
			assertEquals(driver.getTitle(), "Informacion Contacto");
			System.out.println(driver.getTitle());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("gxp0_cls")).click();
		driver.findElement(By.id("IMAGE2_MPAGE")).click();
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

}
