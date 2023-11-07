package com.automationexercise.LearnAutomationFramework.Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automationexercise.LearnAutomationFramework.Base.BaseClass;

public class HomePage extends BaseClass {

	public HomePage() {
		PageFactory.initElements(wd, this);
	}
	@FindBy(css = "h4[class='panel-title'] a")
	WebElement womenBtn;
	
//	@FindBy(css = "div#Women>div>ul>li:nth-of-type(2)")
	@FindBy(xpath = "//a[text()='Tops ']")
	WebElement topBtn;
	
	public void clickWomenProductBtn(){
		wait.until(ExpectedConditions.elementToBeClickable(womenBtn));
		womenBtn.click();
	}
	
	public void clickTopProductBtn(){
		wait.until(ExpectedConditions.elementToBeClickable(topBtn));
		topBtn.click();
	}
}
