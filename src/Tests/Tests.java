package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

import PageObjects.FirstPage;

class Tests {
	WebDriver browser;
	WebElement startButtonElem;

	@BeforeAll
	public static void beforeAll() throws IOException {
		// set the chrome driver path
		Utils.getCurrDir();
		String chromeDriverPath = Paths.get(Utils.getCurrDir(), "resources", "chromedriver.exe").toString();
		
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
	}
	
	@BeforeEach
	void beforeEach() {
		// USED: https://saucelabs.com/resources/articles/how-to-configure-webdriver-selenium-for-java-in-eclipse-on-windows
		
		browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(FirstPage.startTimeout, TimeUnit.MILLISECONDS);
		
		// browse to the test page
		browser.get(FirstPage.url);
	}
	
	@AfterEach
	void afterEach() {
		browser.close();
	}
	
	@Test
	void firstTest() throws IOException {
		
		// NOTE: the ajaxcomplete event will not fire since there are no actual ajax requests in the test page !!
		Utils.loadAjaxHandler(browser);
		
		// find and click the start button
		startButtonElem = browser.findElement(FirstPage.startButton);
		startButtonElem.click();
		
		// find and validate the finish text
		WebElement finishTextElem = browser.findElement(FirstPage.finishTextElem);
		
		assertTrue(finishTextElem.getText().contentEquals(FirstPage.finishText));
	}
	
	@Test
	void secondTest() throws IOException {
		// find and click the start button
		startButtonElem = browser.findElement(FirstPage.startButton);
		startButtonElem.click();
		
		// find and validate the finish text
		WebElement finishTextElem = browser.findElement(FirstPage.finishTextElem);
		
		boolean success = finishTextElem.getText().contentEquals(FirstPage.badFinishText);
		
		if(success) {
			// ... not defined by test case
		} else {
			// highlight wrong message
			Utils.highlightWrongText(browser, finishTextElem);
			
			// USED: https://stackoverflow.com/questions/3422262/how-to-take-screenshot-with-selenium-webdriver
			
			File srcFile = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);
			File targetFile = new File(Paths.get(Utils.getCurrDir(), "result", srcFile.getName()).toString());
			Files.createParentDirs(targetFile);
			Files.copy(srcFile, targetFile);
			System.out.println("Find failure screenshot at: " + targetFile.toString());
			fail();
		}
	}
}