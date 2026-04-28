////package Common;
////
////import Page.LoginPage;  // ✅ SỬA import
////import io.github.bonigarcia.wdm.WebDriverManager;
////import org.openqa.selenium.WebDriver;
////import org.openqa.selenium.chrome.ChromeDriver;
////import org.testng.annotations.AfterMethod;
////import org.testng.annotations.BeforeMethod;
////
////public class BaseTest {
////    protected WebDriver driver;
////    protected LoginPage loginPage;
////
////    protected static final String BASE_URL = "https://dev-ssi-cbt-analysis.com/G0040/classes/home.php";
////    protected static final String USERNAME = "Admin";
////    protected static final String PASSWORD = "Admin123$%^";
////
////    public WebDriver getDriver() {
////        return driver;
////    }
////
////    @BeforeMethod
////    public void setUp() {
////        WebDriverManager.chromedriver().setup();
////        driver = new ChromeDriver();
////        driver.manage().window().maximize();
////        driver.get(BASE_URL);
////        loginPage = new LoginPage(driver);
////        loginPage.login(USERNAME, PASSWORD);
////    }
////
////    @AfterMethod
////    public void tearDown() {
////        if (driver != null) {
////            driver.quit();
////        }
////    }
////}



package Common;
import Page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected WebDriverWait wait;

    protected static final String BASE_URL =
            "https://dev-ssi-cbt-analysis.com/G0040/classes/home.php";
    protected static final String USERNAME = "Admin";
    protected static final String PASSWORD = "Admin123$%^";
    public WebDriver getDriver() {
        return driver;
    }
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", "D:\\AUTO_DUANTOTNGHIEP\\PROJECT\\PROJECT\\downloads"); // thư mục bạn muốn
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options); // dùng options thay vì mặc định
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
