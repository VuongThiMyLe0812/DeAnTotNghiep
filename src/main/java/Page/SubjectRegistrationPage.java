package Page;  // ✅ SỬA

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class SubjectRegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

   private By settingIcon = By.xpath("//a[contains(@href,'master_maintenance.php')]");
   private By subjectLocator = By.id("subject-master-link");
   private By registerButton = By.xpath("//div[@class='left-actions']/a");


    private By schoolName(String name) {
        return By.xpath("//select[@name='school_cd_select']/option[normalize-space()='" + name + "']");
    }

    private By topicID(String topic) {
        return By.xpath("//select[@name='subject_cd']/option[normalize-space()='" + topic + "']");
    }


    private By submitButton = By.className("submit-button");
    private By yesButton = By.xpath("//button[normalize-space()='はい']");
    By completionMessage = By.xpath("//div[@id='completionDialog']//div[@class='completion-message']");
    By duplicateMsg = By.xpath("//div[@class='error-modal-dialog-message']");
    By blankMsg = By.xpath("//div[@class='error-modal-dialog-messages']");


    public SubjectRegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void naviagateRegisterSubjectForn(){
        wait.until(ExpectedConditions.elementToBeClickable(settingIcon));
        driver.findElement(settingIcon).click();

        wait.until(ExpectedConditions.elementToBeClickable(subjectLocator));
        driver.findElement(subjectLocator).click();

        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        driver.findElement(registerButton).click();
    }


    public void fillSubjectForm(String schoolText, String subjectText) {

        // ===== SCHOOL =====
        WebElement schoolDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("school_cd_select"))
        );

        // click để trigger UI (nếu cần)
        schoolDropdown.click();

        Select schoolSelect = new Select(schoolDropdown);
        schoolSelect.selectByVisibleText(schoolText); // vd: "School 01_update"


        // ===== SUBJECT =====
        WebElement subjectDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("subject_cd"))
        );

        subjectDropdown.click(); // trigger load subject

        // wait option xuất hiện theo subjectText
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//select[@name='subject_cd']/option[contains(text(),'" + subjectText + "')]")
        ));

        Select subjectSelect = new Select(subjectDropdown);
        subjectSelect.selectByVisibleText(subjectText);


        // ===== SUBMIT =====
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

        // ===== POPUP =====
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
    }
    public void fillSchoolEmpty(String subjectText) {

        // ===== SUBJECT =====
        WebElement subjectDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("subject_cd"))
        );

        subjectDropdown.click(); // trigger load subject

        // wait option xuất hiện theo subjectText
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//select[@name='subject_cd']/option[contains(text(),'" + subjectText + "')]")
        ));

        Select subjectSelect = new Select(subjectDropdown);
        subjectSelect.selectByVisibleText(subjectText);


        // ===== SUBMIT =====
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

        // ===== POPUP =====
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
    }

    public String getCompletionMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(completionMessage)
        ).getText().trim();
    }

    public String getDuplicateMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(duplicateMsg)
        ).getText().trim();
    }

    public String getBlankMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(blankMsg)
        ).getText().trim();
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public void clickYesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
    }

}