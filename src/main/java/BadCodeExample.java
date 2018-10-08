import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static java.lang.Thread.sleep;


public class BadCodeExample {

    public static void main(String args[]) throws InterruptedException {
        String searchTern = "Selenium";
        System.out.println("Hello world!!");
        WebDriver webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://www.google.com.ua/");
        webDriver.get("https://google.co.in");
        webDriver.manage().window().maximize();
        WebElement Searchbox = webDriver.findElement(By.id("lst-ib"));
        //WebElement Searchbox = webDriver.findElement(By.name("q"));
        //WebElement Searchbox = webDriver.findElement(By.className("gsfi"));
        //WebElement Searchbox = webDriver.findElement(By.xpath("//input[@id='lst-ib']"));
        //WebElement Searchbox = webDriver.findElement(By.xpath("//input[@title='Search']"));
        Searchbox.sendKeys("Selenium");
        Actions action = new Actions(webDriver);
        action.sendKeys(Keys.ENTER).build().perform();

        sleep(3000);
        List<WebElement> searchResults = webDriver.findElements(By.xpath("//div[ @class= 'srg']/div[@class= 'g']"));
        System.out.println("Search results count: " + searchResults.size());

        for (WebElement searchResult : searchResults) {
            String searchResultText = searchResult.getText();
            System.out.print(searchResult.getText());
            if (searchResultText.toLowerCase().contains(searchTern.toLowerCase())) {
                System.out.print("SearchTern" + searchTern + "was found");
            } else {
                System.out.print("SearchTern" + searchTern + "was NOT found");
            }


        }

    }
}
