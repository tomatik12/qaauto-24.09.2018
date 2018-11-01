import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//li[contains(@class, 'search-result__occluded-item')]")
    private List<WebElement> expectedResult;

    @FindBy(xpath = "//h3[@class='search-results__total t-14 t-black--light t-normal pl5 pt4 clear-both']")
    private WebElement itemOfSearch;

    public SearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isSearchPageLoaded() {
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/search/results")
                && itemOfSearch.isDisplayed();
    }

    public int getCountOfResultItem() {
        return expectedResult.size();
    }
    public boolean searchListTerm(String term) {
        boolean found = false;
        for(WebElement webElement : expectedResult)
        { if(webElement.getText().toLowerCase().contains(term.toLowerCase()))
            { found = true; }
            if(found == false){
                break; }
        }
        return found;
    }

}
