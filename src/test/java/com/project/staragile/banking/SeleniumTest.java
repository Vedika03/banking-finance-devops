import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // run without GUI
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
    }

    @Test
    public void testPolicyPage() throws IOException {
        // Hit the running container (update to 8081 if running without Docker)
        driver.get("http://localhost:8084/accounts/viewPolicy/ACC2001");

        // Get page source
        String pageSource = driver.getPageSource();
        System.out.println("Page source: " + pageSource);

        // Save screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filename = "target/screenshot-" + System.currentTimeMillis() + ".png";
        Files.copy(screenshot.toPath(), Paths.get(filename));

        // Assert that response contains account number
        assertTrue(pageSource.contains("ACC2001"), "Page should contain account number ACC2001");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
