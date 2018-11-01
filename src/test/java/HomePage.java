import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class HomePage {
   private  WebDriver webDriver;

   @FindBy(xpath = "//li[@id='profile-nav-item']")
   private WebElement profileNavItem;

    @FindBy(xpath = "//*[@role = 'combobox']")
    private WebElement searchField;


    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

     public boolean isHomePageLoaded () {
         return webDriver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                 && webDriver.getTitle().equals("LinkedIn")
                 && isProfileNavItemDisplayed();
     }

    public boolean isProfileNavItemDisplayed(){return profileNavItem.isDisplayed();}

    public <T> T basicSearchTest(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (webDriver.getCurrentUrl().contains("search/results")) {
            return (T) new SearchPage(webDriver);
        }
        else
            return (T) new HomePage(webDriver);
    }


}
