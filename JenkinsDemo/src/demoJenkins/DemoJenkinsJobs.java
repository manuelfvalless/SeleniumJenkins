package demoJenkins;

import org.testng.annotations.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import java.awt.Robot;
import java.awt.event.InputEvent;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class DemoJenkinsJobs {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		driver = new HtmlUnitDriver(true);
		baseUrl = "http://54.235.81.157/carteratest/seclogin.aspx";
	}

	@Test
	public void testJenkins() throws Exception {
		Robot robot = new Robot();
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
		java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
		driver.get(baseUrl);
		Thread.sleep(5000);
		driver.findElement(By.id("vUSUARIONOMBRE")).clear();
		driver.findElement(By.id("vUSUARIONOMBRE")).sendKeys("mvalles");
		Thread.sleep(1000);
		driver.findElement(By.id("vUSUARIOPASSWORD")).clear();
		driver.findElement(By.id("vUSUARIOPASSWORD")).sendKeys("Test01.");
		Thread.sleep(1000);
		driver.findElement(By.name("BTNENTER")).click();
		Thread.sleep(10000);
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Maestros'])[1]/following::span[1]"))
				.click();
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		Thread.sleep(1000);
		driver.findElement(By.linkText("Alta Crédito Manual")).click();
		Thread.sleep(3000);
		try {
			assertEquals(driver.findElement(By.id("TEXTBLOCKTITLE")).getText(), "Importar Crédito");
			System.out.println("Texto encontrado: Importar Crédito");
			System.out.println(driver.getTitle());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
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
