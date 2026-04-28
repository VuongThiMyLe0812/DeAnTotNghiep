package Page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;

public class DataCheckRegistrationManagementPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By CheckRegistrationIcon = By.xpath("//a[.//div[text()='乖離度登録']]");
    private By fileInput = By.id("file-upload");
    private By Registeationbutton = By.id("submitBtn");

    // popup confirm
    private By completionMess = By.xpath("//div[contains(@class,'confirmation-message')]");
    private By yesButon = By.xpath("//button[normalize-space()='はい']");

    // lỗi sau khi submit
    private By noCompletionMessage = By.xpath("//p[contains(@class,'error-message-text')]");

    public DataCheckRegistrationManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void openCheckRegistrationPage() {
        wait.until(ExpectedConditions.elementToBeClickable(CheckRegistrationIcon)).click();
    }

    public void uploadFile(String filePath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(fileInput))
                .sendKeys(filePath);
    }

    public void clickRegistration() {
        wait.until(ExpectedConditions.elementToBeClickable(Registeationbutton)).click();
    }

    public boolean isConfirmationDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completionMess))
                .isDisplayed();
    }

    public void clickYesButton() {
        WebElement yesBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(yesButon)
        );

        wait.until(ExpectedConditions.elementToBeClickable(yesBtn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", yesBtn);

        //wait.until(ExpectedConditions.elementToBeClickable(yesButon)).click();
    }

    public boolean isErrorMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(noCompletionMessage))
                .isDisplayed();
    }

    public String getErrorMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(noCompletionMessage))
                .getText();
    }

    //excel rỗng
    public void clickCheckRegistrationIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(CheckRegistrationIcon)).click();
    }

    public void clickRegistrationButton() {
        wait.until(ExpectedConditions.elementToBeClickable(Registeationbutton)).click();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(noCompletionMessage))
                .getText()
                .trim();
    }

    //không cos file
    public boolean isErrorMessageDisplaye() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(noCompletionMessage))
                    .isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


    //lỗi khi chưa chọn file


    public String confirmAndGetErrorMessage() {
        WebElement yesBtn = wait.until(
                ExpectedConditions.elementToBeClickable(yesButon)
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", yesBtn);

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(noCompletionMessage)
        );

        return error.getAttribute("textContent").trim();
    }

    //dowload
// button download format
    private By downloadFormatBtn = By.id("downloadFormatBtn");

    // popup success sau khi download
    private By successPopup = By.xpath("//div[contains(@class,'success-dialog')]");
    private By successMessageText = By.cssSelector("p.success-message-text");
    private By closeSuccessBtn = By.xpath("//button[normalize-space()='閉じる']");

    // click vào button download format
    public void clickDownloadFormatButton() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadFormatBtn)).click();
    }

    // kiểm tra popup success hiển thị
    public boolean isSuccessPopupDisplayed() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(
                successMessageText,
                "処理が正常に完了しました"
        ));
    }


    // lấy nội dung message trong popup
    public String getSuccessMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageText))
                .getText()
                .trim();
    }

    // click nút đóng popup
    public void clickCloseSuccessPopup() {
        WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(closeSuccessBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);
    }
    public File waitForLatestDownloadedFile(String downloadDir, int timeoutSeconds) {
        File dir = new File(downloadDir);
        File latestFile = null;

        for (int i = 0; i < timeoutSeconds; i++) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                latestFile = Arrays.stream(files)
                        .filter(File::isFile)
                        .max(Comparator.comparingLong(File::lastModified))
                        .orElse(null);
                if (latestFile != null && latestFile.exists()) {
                    return latestFile;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}


