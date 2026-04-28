package Page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterExamResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By CheckRegistrationIcon = By.xpath("//a[.//div[text()='試験結果登録']]");
    private By fileInput = By.id("file-upload");
    private By Registeationbutton = By.id("submitBtn");

    // popup confirm
    private By completionMess = By.xpath("//div[contains(@class,'confirmation-message')]");
    private By yesButon = By.xpath("//button[normalize-space()='はい']");

    // lỗi sau khi submit
    private By noCompletionMessage = By.xpath("//p[contains(@class,'error-message-text')]");

    public RegisterExamResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void openRegistrationPage() {
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
}
