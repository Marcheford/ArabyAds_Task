package ArabyAds.Quiz;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void Scenario1()
    {
    	System.setProperty("webdriver.chrome.driver","G:\\Testing\\Driver\\chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
        String baseUrl = "https://www.saucedemo.com/";
        String loginName = "standard_user";
        String password = "secret_sauce";
        String firstName = "Ahmed";
        String lastName = "Hesham";
        String zipCode = "1234";
        driver.get(baseUrl);
        driver.manage().window().maximize();
        
        
		driver.findElement(By.xpath("//input[contains(@id, 'user-name')]")).sendKeys(loginName);
		driver.findElement(By.xpath("//input[contains(@id, 'password')]")).sendKeys(password);
		driver.findElement(By.xpath("//input[contains(@id, 'login-button')]")).click();
		
		
        Select drpFilters = new Select(driver.findElement(By.xpath("//select[contains(@data-test, 'product_sort_container')]")));
        
        drpFilters.selectByVisibleText("Price (low to high)");
        
        int listSize = driver.findElements(By.xpath("//div[contains(@id,'inventory_container') and contains(@class,'inventory_container')]/div/div")).size();
        Boolean ifItemFiltered = true;
		for(int i = 1; i <listSize ; i ++) {
			Float  elementPrice ;
			Float  nextElementPrice ;

			elementPrice = Float.parseFloat(driver.findElement(By.xpath("//div[contains(@id,'inventory_container') and contains(@class,'inventory_container')]/div/div["+i+"]//div[contains(@class,'inventory_item_price')]")).getText().replaceAll("[$,]", ""));
			nextElementPrice = Float.parseFloat(driver.findElement(By.xpath("//div[contains(@id,'inventory_container') and contains(@class,'inventory_container')]/div/div["+(i+1)+"]//div[contains(@class,'inventory_item_price')]")).getText().replaceAll("[$,]", ""));

			if(nextElementPrice<elementPrice) {
				ifItemFiltered = false;
				break;
			}


		}
	       assertTrue("Item is not filtered", ifItemFiltered);
        String firstItemName = driver.findElement(By.xpath("//div[contains(@id,'inventory_container') and contains(@class,'inventory_container')]/div/div[1]//div[contains(@class,'inventory_item_name')]")).getText();
        driver.findElement(By.xpath("//div[contains(@id,'inventory_container') and contains(@class,'inventory_container')]/div/div[1]//button[contains(@data-test,'add-to-cart')]")).click();
        driver.findElement(By.xpath("//a[contains(@class,'shopping_cart_link')]")).click();
        
        
        String itemAddedName = driver.findElement(By.xpath("//div[contains(@class,'inventory_item_name')]")).getText();
        assertEquals(firstItemName, itemAddedName);
        //assert if the item selected is the one that added into the cart
        driver.findElement(By.xpath("//button[contains(@id,'checkout')]")).click();
        
        
        WebElement continueBtn = driver.findElement(By.xpath("//input[contains(@id,'continue')]"));
        continueBtn.click();
        String errorMSG = driver.findElement(By.xpath("//h3[contains(@data-test,'error')]")).getText();
        assertEquals("Error: First Name is required", errorMSG);
        //assert the error msg if other fields are empty

        driver.findElement(By.xpath("//input[contains(@id,'first-name')]")).sendKeys(firstName);
        continueBtn.click();
        errorMSG = driver.findElement(By.xpath("//h3[contains(@data-test,'error')]")).getText();
        assertEquals("Error: Last Name is required", errorMSG);
        //assert the error msg if other fields are empty

        driver.findElement(By.xpath("//input[contains(@id, 'last-name')]")).sendKeys(lastName);
        continueBtn.click();
        errorMSG = driver.findElement(By.xpath("//h3[contains(@data-test,'error')]")).getText();
        assertEquals("Error: Postal Code is required", errorMSG);
        //assert the error msg if other fields are empty
 
        driver.findElement(By.xpath("//input[contains(@id, 'postal-code')]")).sendKeys(zipCode);
        continueBtn.click();
        
        
        driver.findElement(By.xpath("//button[contains(@id,'finish')]")).click();
        String successMSG = driver.findElement(By.xpath("//div[contains(@class,'header_secondary_container')]//span")).getText();
        assertEquals("CHECKOUT: COMPLETE!", successMSG);
        
        driver.close();
    }
    
    @Test
    public void Scenario2()
    {
    	System.setProperty("webdriver.chrome.driver","G:\\Testing\\Driver\\chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
        String baseUrl = "https://www.saucedemo.com/";
        String loginName = "locked_out_user";
        String password = "secret_sauce";
       
        driver.get(baseUrl);
        driver.manage().window().maximize();

        
		driver.findElement(By.xpath("//input[contains(@id, 'user-name')]")).sendKeys(loginName);
		driver.findElement(By.xpath("//input[contains(@id, 'password')]")).sendKeys(password);
		driver.findElement(By.xpath("//input[contains(@id, 'login-button')]")).click();
		
        String errorMSG = driver.findElement(By.xpath("//h3[contains(@data-test,'error')]")).getText();
        assertEquals("Epic sadface: Sorry, this user has been locked out.", errorMSG);
        //asserting the error message of the locked user

        driver.close();

    }

    
    @Test
    public void Scenario3()
    {
    	System.setProperty("webdriver.chrome.driver","G:\\Testing\\Driver\\chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
        String baseUrl = "https://www.saucedemo.com/";
        String loginName = "problem_user";
        String password = "secret_sauce";
       
        driver.get(baseUrl);
        driver.manage().window().maximize();

        
		driver.findElement(By.xpath("//input[contains(@id, 'user-name')]")).sendKeys(loginName);
		driver.findElement(By.xpath("//input[contains(@id, 'password')]")).sendKeys(password);
		driver.findElement(By.xpath("//input[contains(@id, 'login-button')]")).click();

        Select drpFilters = new Select(driver.findElement(By.xpath("//select[contains(@data-test, 'product_sort_container')]")));
        drpFilters.selectByVisibleText("Price (low to high)");
        
        int listSize = driver.findElements(By.xpath("//div[contains(@id,'inventory_container') and contains(@class,'inventory_container')]/div/div")).size();
        Boolean ifItemFiltered = true;
		for(int i = 1; i <listSize ; i ++) {
			Float  elementPrice ;
			Float  nextElementPrice ;

			elementPrice = Float.parseFloat(driver.findElement(By.xpath("//div[contains(@id,'inventory_container') and contains(@class,'inventory_container')]/div/div["+i+"]//div[contains(@class,'inventory_item_price')]")).getText().replaceAll("[$,]", ""));
			nextElementPrice = Float.parseFloat(driver.findElement(By.xpath("//div[contains(@id,'inventory_container') and contains(@class,'inventory_container')]/div/div["+(i+1)+"]//div[contains(@class,'inventory_item_price')]")).getText().replaceAll("[$,]", ""));

			if(nextElementPrice<elementPrice) {
				ifItemFiltered = false;
				break;
			}


		}
	       assertTrue("Item is not filtered", ifItemFiltered);
	       //asserting if the items are filtered or not
	       
	        driver.close();

    }
    
    @Test
    public void Scenario4()
    {
    	System.setProperty("webdriver.chrome.driver","G:\\Testing\\Driver\\chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
        String baseUrl = "https://www.saucedemo.com/";
        String loginName = "performance_glitch_user";
        String password = "secret_sauce";
        String firstName = "Ahmed";
        String lastName = "Hesham";
        String zipCode = "1234";
        driver.get(baseUrl);
        driver.manage().window().maximize();

    	WebDriverWait wait=new WebDriverWait(driver, 5);
        //using wait method to wait until element visiblity 
        
		driver.findElement(By.xpath("//input[contains(@id, 'user-name')]")).sendKeys(loginName);
		driver.findElement(By.xpath("//input[contains(@id, 'password')]")).sendKeys(password);
		driver.findElement(By.xpath("//input[contains(@id, 'login-button')]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//select[contains(@data-test, 'product_sort_container')]")));

        Select drpFilters = new Select(driver.findElement(By.xpath("//select[contains(@data-test, 'product_sort_container')]")));
        
        drpFilters.selectByVisibleText("Price (low to high)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//span[contains(@class,'active_option') and contains(text(),'Price (low to high)')]")));

        int listSize = driver.findElements(By.xpath("//div[contains(@id,'inventory_container') and contains(@class,'inventory_container')]/div/div")).size();
        Boolean ifItemFiltered = true;
		for(int i = 1; i <listSize ; i ++) {
			Float  elementPrice ;
			Float  nextElementPrice ;

			elementPrice = Float.parseFloat(driver.findElement(By.xpath("//div[contains(@id,'inventory_container') and contains(@class,'inventory_container')]/div/div["+i+"]//div[contains(@class,'inventory_item_price')]")).getText().replaceAll("[$,]", ""));
			nextElementPrice = Float.parseFloat(driver.findElement(By.xpath("//div[contains(@id,'inventory_container') and contains(@class,'inventory_container')]/div/div["+(i+1)+"]//div[contains(@class,'inventory_item_price')]")).getText().replaceAll("[$,]", ""));

			if(nextElementPrice<elementPrice) {
				ifItemFiltered = false;
				break;
			}


		}
	       assertTrue("Item is not filtered", ifItemFiltered);
        String firstItemName = driver.findElement(By.xpath("//div[contains(@id,'inventory_container') and contains(@class,'inventory_container')]/div/div[1]//div[contains(@class,'inventory_item_name')]")).getText();
        driver.findElement(By.xpath("//div[contains(@id,'inventory_container') and contains(@class,'inventory_container')]/div/div[1]//button[contains(@data-test,'add-to-cart')]")).click();
        driver.findElement(By.xpath("//a[contains(@class,'shopping_cart_link')]")).click();
        
        
        String itemAddedName = driver.findElement(By.xpath("//div[contains(@class,'inventory_item_name')]")).getText();
        assertEquals(firstItemName, itemAddedName);
        //assert if the item selected is the one that added into the cart
        driver.findElement(By.xpath("//button[contains(@id,'checkout')]")).click();
        
        
        WebElement continueBtn = driver.findElement(By.xpath("//input[contains(@id,'continue')]"));
        continueBtn.click();
        String errorMSG = driver.findElement(By.xpath("//h3[contains(@data-test,'error')]")).getText();
        assertEquals("Error: First Name is required", errorMSG);
        //assert the error msg if other fields are empty

        driver.findElement(By.xpath("//input[contains(@id,'first-name')]")).sendKeys(firstName);
        continueBtn.click();
        errorMSG = driver.findElement(By.xpath("//h3[contains(@data-test,'error')]")).getText();
        assertEquals("Error: Last Name is required", errorMSG);
        //assert the error msg if other fields are empty

        driver.findElement(By.xpath("//input[contains(@id, 'last-name')]")).sendKeys(lastName);
        continueBtn.click();
        errorMSG = driver.findElement(By.xpath("//h3[contains(@data-test,'error')]")).getText();
        assertEquals("Error: Postal Code is required", errorMSG);
        //assert the error msg if other fields are empty
 
        driver.findElement(By.xpath("//input[contains(@id, 'postal-code')]")).sendKeys(zipCode);
        continueBtn.click();
        
        
        driver.findElement(By.xpath("//button[contains(@id,'finish')]")).click();
        String successMSG = driver.findElement(By.xpath("//div[contains(@class,'header_secondary_container')]//span")).getText();
        assertEquals("CHECKOUT: COMPLETE!", successMSG);
        
        driver.close();

    }
}