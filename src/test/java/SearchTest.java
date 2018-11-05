import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static java.lang.Thread.sleep;

public class SearchTest {

    WebDriver webDriver;
    LoginPage loginPage;
    String userEmail = "melnyktoma.92@gmail.com";
    String userPassword = "malyavo4ka";

    @BeforeMethod
    public void beforeMethod() {

        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com/");
        loginPage = new LoginPage(webDriver);
    }

    @AfterMethod
    public void afterMethod () {

        webDriver.quit();
    }

    /**
     * PreConditions:
     * - Open new Browser.
     * - Navigate to linkedin.com
     * <p>
     * Scenario:
     * - Verify that Login page is loaded.
     * - Login with valid credentials.
     * - Verify that Home page is loaded.
     * - Enter 'searchTerm' into search field and press RETURN key.
     * - Verify that Search page is loaded.
     * - Verify 10 searchResults displayed on page.
     * - Verify each result Item contains 'searchTerm'.
     * PostConditions:
     * - Close Browser.
     */


        @Test
        public void basicSearchTest() throws InterruptedException {
            String searchTerm = "HR";
            Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded.");
            HomePage homePage = loginPage.login(userEmail, userPassword);
            Assert.assertTrue(homePage.isHomePageLoaded(),"The home page is not loaded.");
            SearchPage searchPage = homePage.search(searchTerm);
            Assert.assertTrue(searchPage.isSearchPageLoaded(), "Search Page is not loaded.");
            Assert.assertEquals(searchPage.getSearchResultsCount(), 10, "SearchResults count is wrong.");


            List<String> searchResultList = searchPage.getSearchResults();

            for (String searchResult : searchResultList) {
                Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()),
                        "SearchTerm" +searchTerm+"not found in" +searchResult);
}


        }
}