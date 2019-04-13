package Tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {
	static String getCurrDir() throws IOException {
		return new java.io.File( "." ).getCanonicalPath();
	}
	
	void isAjaxComplete(WebDriver browser) {
		JavascriptExecutor js = (JavascriptExecutor)browser;
		js.executeScript("");
	}
	
	static void highlightWrongText(WebDriver browser, WebElement finishTextElem) {
		// USED: https://www.softwaretestingmaterial.com/highlight-element-using-selenium/
		JavascriptExecutor js = (JavascriptExecutor)browser;
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", finishTextElem);
	}
	
	static void loadAjaxHandler(WebDriver browser) throws IOException {
		String script = Utils.readFileContents(Paths.get(getCurrDir(), "resources", "ajaxCompleteHandler.js").toString());
		JavascriptExecutor js = (JavascriptExecutor)browser;
		js.executeScript(script);
	}
	
	private static String readFileContents(String filePath) throws IOException {
		// USED: https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
	    StringBuilder contentBuilder = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
	    {
	 
	        String sCurrentLine;
	        while ((sCurrentLine = br.readLine()) != null)
	        {
	            contentBuilder.append(sCurrentLine).append("\n");
	        }
	    }
	    return contentBuilder.toString();
	}
}
