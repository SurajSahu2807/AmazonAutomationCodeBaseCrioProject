package org.aptests;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    WebDriver driver;
     public TestCases() throws MalformedURLException {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        
        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

   // TestCase01 :- To Verify the Url of the HomePage

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        String url="https://www.amazon.in";
        // Navigate to the Amazon India WebSite
        driver.get(url);
        String Currenturl = driver.getCurrentUrl();
        // To Verify that the url contains to the expected title (e.g., "amazon")
        if(Currenturl.contains("amazon")){
            System.out.println("Yes , it contains amazon in the currentUrl");
        }else{
            System.out.println("No , it doesn't contains \"amazon\" in the currentUrl");
        }
        System.out.println("end Test case: testCase01");
    }

    // TestCase02 :- Verify the Search Functionality
    public void testCase02(){
        System.out.println("Start testcase : testCase02");
        // locate the Search-Bar
        driver.findElement(By.id("twotabsearchtextbox")).click();
        // Enter a search term (e.g., "laptop") into the search bar
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("laptop");
        driver.findElement(By.xpath("(//input[@type='submit'])[1]")).click();
        List<WebElement> products = driver.findElements(By.xpath(
                "//*[@id='search']/div[1]/div[1]/div/span[1]/div[1]/div/div/div/div/div/div/div[2]/div/div/div[1]/h2/a/span"));
       // To Verify that the resulting page contains the search term in at least one of the product titles or descriptions
            for(WebElement ww : products){
            if(ww.getText().contains("Laptop")){
                    System.out.println("The product Name Contains \"Laptop\" word");
                    break;
            }
        }   
        System.out.println("end Test case: testCase02");
    }

    // TestCase03 :- Verify the Navigation Menu
    public void testcase03(){
        // Navigating to the homepage
        driver.navigate().back();
        System.out.println("Start testcase : testCase03");
        // Locate the Navigation Menu
        List<WebElement> NavigationMenu = driver.findElements(By.xpath("//div[@id='nav-xshop']/a"));
        for(WebElement nnn : NavigationMenu){
            System.out.println(nnn.getText());
            // To Verify that the resulting page corresponds to the clicked category (the page url includes "electronics")
            if(nnn.getText().equalsIgnoreCase("Electronics")){
                System.out.println("Clicking on the electronics option");
                nnn.click();
                break;
            }
        }
        System.out.println(driver.getCurrentUrl());
        System.out.println("Current page is Electronics page : " + driver.getCurrentUrl().contains("electronics"));
        System.out.println("end Test case: testCase03");
    }
}
