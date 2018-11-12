import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class HomePage extends BasePage {
   private  WebDriver webDriver;

   @FindBy(xpath = "//li[@id='profile-nav-item']")
   private WebElement profileNavItem;

    @FindBy(xpath = "//input[contains(@aria-owns, 'results')]")
    private WebElement searchField;

    @FindBy(xpath = "//a[@data-control-name='nav.settings_signout']")
    private WebElement signOutButton;


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



public  SearchPage search(String searchTerm) throws InterruptedException {
    searchField.sendKeys(searchTerm);
    searchField.sendKeys(Keys.ENTER);
    sleep(3000);
    return  new SearchPage(webDriver);
}
public boolean isPageLoaded(){
        return false;
}
}
