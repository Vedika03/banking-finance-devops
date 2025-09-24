import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

    private static WebDriver driver;
    private static String appUrl;

    @BeforeClass
    public static void setup() {
        appUrl = System.getProperty("app.url", "http://localhost:8081"); // Default if not set
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver"); // Update path
        driver = new ChromeDriver();
    }

    @Test
    public void testHomePage() {
        driver.get(appUrl);
        Assert.assertTrue(driver.getTitle().contains("FinanceMe"));
        System.out.println("APP_URL: " + appUrl);
    }

    @AfterClass
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
