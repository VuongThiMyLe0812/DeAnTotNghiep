package Test;

import Page.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class Login {
        private WebDriver driver;
        private LoginPage loginPage;

        @BeforeMethod
        public void setUp() {
            // Khởi tạo WebDriver (ví dụ dùng ChromeDriver)
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://dev-ssi-cbt-analysis.com/G0040/classes/home.php"); // URL màn hình login của bạn

            loginPage = new LoginPage(driver);
        }

        @Test
        public void TC_LOGIN_001_DangNhapThanhCong() {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("Admin", "Admin@123");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement userName = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'Admin')]")
                    )
            );
        }

        @Test
        public void TC_LOGIN_002_DangNhapSaiTenDangNhap() {
            // Nhập sai tên đăng nhập, đúng mật khẩu
            loginPage.login("Admin123", "Admin@123");

            // Chờ thông báo lỗi hiển thị
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement errorMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'不正なログインです。再度ログインしてください。')]")
                    )
            );
            Assert.assertTrue(
                    errorMessage.isDisplayed(),
                    "Không hiển thị thông báo lỗi khi nhập sai tên đăng nhập"
            );
        }

        @Test
        public void TC_LOGIN_003_DangNhapSaiMatKhau() {
            loginPage.login("Admin", "Admin@999");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement errorMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'不正なログインです。再度ログインしてください。')]")
                    )
            );
            Assert.assertTrue(
                    errorMessage.isDisplayed(),
                    "Không hiển thị thông báo lỗi khi nhập sai mật khẩu"
            );
        }

        @Test
        public void TC_LOGIN_004_DangNhapSaiTenDangNhapVaMatKhau() {
            loginPage.login("WrongUser", "WrongPass123");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement errorMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'不正なログインです。再度ログインしてください。')]")
                    )
            );
            Assert.assertTrue(
                    errorMessage.isDisplayed(),
                    "Không hiển thị thông báo lỗi khi nhập sai tên đăng nhập và mật khẩu"
            );
        }

        @Test
        public void TC_LOGIN_005_DeTrongTenDangNhap() {
            loginPage.login("", "Admin@123");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement errorMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'不正なログインです。再度ログインしてください。')]")
                    )
            );
            Assert.assertTrue(
                    errorMessage.isDisplayed(),
                    "Không hiển thị thông báo lỗi khi để trống tên đăng nhập"
            );
        }

        @Test
        public void TC_LOGIN_006_DeTrongMatKhau() {
            loginPage.login("Admin", "");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement errorMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'不正なログインです。再度ログインしてください。')]")
                    )
            );
            Assert.assertTrue(
                    errorMessage.isDisplayed(),
                    "Không hiển thị thông báo lỗi khi để trống mật khẩu"
            );
        }

        @Test
        public void TC_LOGIN_007_DeTrongTenDangNhapVaMatKhau() {
            loginPage.login("", "");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement errorMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'不正なログインです。再度ログインしてください。')]")
                    )
            );
            Assert.assertTrue(
                    errorMessage.isDisplayed(),
                    "Không hiển thị thông báo lỗi khi để trống tên đăng nhập và mật khẩu"
            );
        }

        @AfterMethod
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }


