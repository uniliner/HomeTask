package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;

public class FirstPage {
	public static String url = "http://the-internet.herokuapp.com/dynamic_loading/2";
	
	public static By startButton = ByCssSelector.cssSelector("#start > button");
	
	public static By finishTextElem = ByCssSelector.cssSelector("#finish > h4");
	
	public static String finishText = "Hello World!";
	public static String badFinishText = "Hey World!";
	
	public static int startTimeout = 6000;
}
