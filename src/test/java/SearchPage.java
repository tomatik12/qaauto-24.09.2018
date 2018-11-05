import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

import java.util.List;

public class SearchPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//li[contains(@class, 'search-result__occluded-item')]")
    private List<WebElement> searchResultList;

    @FindBy(xpath = "//h3[@class='search-results__total t-14 t-black--light t-normal pl5 pt4 clear-both']")
    private WebElement searchBar;

    public SearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isSearchPageLoaded() {
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/search/results")
                && webDriver.getTitle().contains("|Search|LinkedIn'")
                && searchBar.isDisplayed();
    }

    public int getSearchResultsCount() {
        return searchResultList.size();
    }


    public List<String> getSearchResults() throws InterruptedException {

        List<String> searchResultStringList = new ArrayList<String>();
        for (WebElement searchResult : searchResultList) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", searchResult);
            Thread.sleep(500);
            String searchResultText = searchResult.getText();
            searchResultStringList.add(searchResultText);
        }
        return searchResultStringList;
    }
}
