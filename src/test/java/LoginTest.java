import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTest {




    /**
     * Preconditions:
     * - Open FF Browser.
     *
     * Scenario:
     * - Navigate to https://www.linkedin.com/.
     * - Verify that Login page is loaded.
     * - Enter userEmail into userEmail field.
     * - Enter userPassword  into userPassword field.
     * - Click on signIn button.
     * - Verify that Home page is loaded.
     *
     * PostCondition:
     * - Close FF browser.
     */
    @Test
    public void successfulLoginTest() {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com/");
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/", "Home page URL is wrong");
        webDriver.findElement(By.id("login-email")).sendKeys("melnyktoma.92@gmail.com");
        webDriver.findElement(By.id("login-password")).sendKeys("malyavo4ka");
        webDriver.findElement(By.id("login-submit")).click();
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/", "Home page URL is wrong");
        webDriver.quit();
    }
}
