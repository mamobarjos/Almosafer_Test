/*Scenario to automate:

		Navigate to URL https://www.almosafeer.com/en

		Once the website is loaded, make below assertions:

		Default language is correct (EN).
		Default currency is correct (SAR).
		Contact numbers are correct (check yourself).
		Verify “qitaf” logo is displayed in footer.
		Hotels search tab is NOT selected by default.
		Flight departure date is set to "today+1day" by default.
		Flight return date is set to "today+2days" by default.
		Make any other assertions that you want to do.
		Use random method to change language (sometime keep AR, sometime change to EN):

		Make assertion that language is updated as you chose.
		Switch to hotel search tab:

		In location field, type value:
		➤ If current-lang is EN, then randomly type from (Dubai, Jeddah, Riyadh).
		➤ If current-lang is AR, then randomly type from (دبي، جدة، رياض).
		Click on first result from autocomplete list.
		Randomly select “1 room, 2 adults, 0 children” or “1 room, 1 adult, 0 children” option.

		Click on search hotels button.

		On new page (which is “Search results page”):

		Wait for loading fully complete (wait for loading bar or API).
		Important: do some assertions.*/
package mypackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases extends Parameters {
	// can use this or extends Parameters
	// Parameters object = new Parameters();

	@BeforeTest
	public void MySetup() {
		ConfigurationToAcces();
	}

	@Test(priority = 1, description = "assertion the defult languge is correct (EN)", enabled = false)
	public void CheckTheDefultLanguge() {

		// assertion the defult languge is correct (EN)
		// driver.findElement(By.tagName("html")).getDomAttribute("lang");
		// asseration if the html tag have lang attribute en for defualt
		System.out.println(driver.findElement(By.tagName("html")).getDomAttribute("lang"));
		String ActualLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");
		Assert.assertEquals(ActualLanguage, ExpectedLanguage);
	}

	@Test(priority = 2, description = "Check The Defult Currency", enabled = false)
	public void CheckTheDefultCurrency() {
		// use cssselector we have class with space
		String ActualCurrency = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO")).getText();
		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
	}

	@Test(priority = 3, description = "Check mobile number", enabled = false)
	public void CheckMobileNumber() {
		String ActualMobileNumber = driver.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(ActualMobileNumber, ExpectedMobileNumber);
	}

	@Test(priority = 4, description = "Check The Logo of Qitaf ", enabled = false)
	public void CheckQitafLogo() {
		WebElement TheFooter = driver.findElement(By.cssSelector(".sc-dEfkYy.byExGa"));
		WebElement TheContaniar = TheFooter.findElement(By.cssSelector(".sc-ghsgMZ.hIElfs"));
		WebElement QitafLogo = TheContaniar.findElement(By.tagName("svg"));
		boolean ActualQitaflogoDisplay = QitafLogo.isDisplayed();

		Assert.assertEquals(ActualQitaflogoDisplay, ExpectedQitafLogoDisplay);
	}

	@Test(priority = 5, description = "Check Hotel Tap Is Selected", enabled = false)
	public void HotelTapIsNotSelected() {
		WebElement HotelTap = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualAriaSelected = HotelTap.getDomAttribute("aria-selected");
		Assert.assertEquals(ActualAriaSelected, ExpectedAriaSelected);
	}

	@Test(priority = 6, description = "Chek The Depature Date is set today + Day by defult", enabled = false)
	public void CheckDepatureDate() {
		// LocalDate date = LocalDate.now();
		// System.out.println(date);
		// int Today =date.getDayOfMonth();
		// System.out.println(Today);
		// int Tomorrow =date.plusDays(1).getDayOfMonth();
		// System.out.println(Tomorrow);
		List<WebElement> Dates = driver.findElements(By.cssSelector(".sc-fvLVrH.hNjEjT"));
		// System.out.println(Dates.size());
		String ActualDepaturDate = Dates.get(0).getText();
		System.out.println(ActualDepaturDate);
		String Tomorrow = Integer.toString(date.plusDays(1).getDayOfMonth());
		System.out.println(Tomorrow);
		Assert.assertEquals(ActualDepaturDate, Tomorrow);
	}

	@Test(priority = 7, description = "Check Return Date is Set To day + 2day by defult", enabled = false)
	public void CheckReturnDate() {
		List<WebElement> Dates = driver.findElements(By.cssSelector(".sc-fvLVrH.hNjEjT"));
		String ActualReturnDate = Dates.get(1).getText();
		System.out.println(ActualReturnDate);
		String ExpectedReturnDate = Integer.toString(date.plusDays(2).getDayOfMonth());
		System.out.println(ExpectedReturnDate);
		Assert.assertEquals(ActualReturnDate, ExpectedReturnDate);
	}

	/*
	 * @Test(priority=8,description="Randomly Check Website Languge",enabled=false)
	 * public void RandomlyCheckWebsiteLanguge () {
	 * driver.get(WebSites[RandomIndexForTheWebSItes]);
	 * if(driver.getCurrentUrl().contains("en")) { String ActualLanguage =
	 * driver.findElement(By.tagName("html")).getDomAttribute("lang");
	 * Assert.assertEquals(ActualLanguage, ExpectedEnglishLanguage); } else { String
	 * ActualLanguage =
	 * driver.findElement(By.tagName("html")).getDomAttribute("lang");
	 * Assert.assertEquals(ActualLanguage, ExpectedArabicLanguage); } }
	 */
	// طريقة حل اخرى
	@Test(priority = 8, description = "Randomly Check Website Languge", enabled = false)
	public void RandomlyCheckWebsiteLanguge() {
		driver.get(WebSites[RandomIndexForTheWebSItes]);
		WebElement headerforthelanguage = driver.findElement(By.xpath("//a[@data-testid='Header__LanguageSwitch']"));
		if (headerforthelanguage.getText().equals("العربية")) {
			String ActualLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");
			Assert.assertEquals(ActualLanguage, ExpectedEnglishLanguage);
		} else {
			String ActualLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");
			Assert.assertEquals(ActualLanguage, ExpectedArabicLanguage);
		}

	}

	@Test(priority=9,description="Switch to hotel search tab if arbic type arabic cities randomly if english type english citeies randomly click first complete list",enabled=true)
	public void ClickFirstCompliteList() {
	driver.get(WebSites[RandomIndexForTheWebSItes]);
	WebElement HotelTap = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
	HotelTap.click();
    WebElement SearchTap = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
    WebElement headerforthelanguage = driver.findElement(By.xpath("//a[@data-testid='Header__LanguageSwitch']"));
    //if website is English
    if(headerforthelanguage.getText().equals("العربية")) {
    	SearchTap.sendKeys(EnglishCities[RandomIndexForEnglishCities]);

    	
	    
	}
    else {
    	SearchTap.sendKeys(ArabicCities[RandomIndexForArabicCities]);
    }
	SearchTap.sendKeys(Keys.chord(Keys.ENTER));

//    WebElement ListOfCiteies =driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
//    ListOfCiteies.findElements(By.tagName("li")).get(1).click();  
}
	//you must enabled test 9
	@Test(priority=10,description="Randomly select “1 room, 2 adults, 0 children” or “1 room, 1 adult, 0 children” option then click search", enabled=true)
	public void RandomlySelect () throws InterruptedException {
		WebElement NumberofClient =driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
		Select myselector= new Select(NumberofClient);
		int RandomIndex = rand.nextInt(2);
		myselector.selectByIndex(RandomIndex);
		//Search click
		WebElement SearchButton = driver.findElement(By.cssSelector(".sc-1vkdpp9-5.btwWVk"));
		SearchButton.click();
		//wait for loading bar or API
		Thread.sleep(5000); //wait for loading result
		WebElement Result = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));
		System.out.println(Result.getText()+ "@@@@@@@@@@@@");
		// assertions
		boolean ActualResult = Result.getText().contains("stays")||Result.getText().contains("مكان");
			Assert.assertEquals(ActualResult, ExpectedResult);
	}
}

