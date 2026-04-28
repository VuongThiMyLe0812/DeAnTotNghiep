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

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

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

    // Hàm chờ file PDF mới nhất
    public File waitForLatestPdfFile(String downloadDir, int timeoutSeconds) throws InterruptedException {
        File dir = new File(downloadDir);
        File latestFile = null;
        for (int i = 0; i < timeoutSeconds; i++) {
            File[] files = dir.listFiles((d, name) -> name.endsWith(".pdf"));
            if (files != null && files.length > 0) {
                latestFile = Arrays.stream(files)
                        .max(Comparator.comparingLong(File::lastModified))
                        .orElse(null);
                if (latestFile != null && latestFile.exists()) {
                    return latestFile;
                }
            }
            Thread.sleep(1000);
        }
        return null;
    }
    public void fillOnlySchool(String schoolText) {
        WebElement schoolDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("school_cd_select"))
        );

        schoolDropdown.click();

        Select schoolSelect = new Select(schoolDropdown);
        schoolSelect.selectByVisibleText(schoolText);
    }
    //xóa
    public void navigateSubjectPage() {
        wait.until(ExpectedConditions.elementToBeClickable(settingIcon)).click();
        wait.until(ExpectedConditions.elementToBeClickable(subjectLocator)).click();
    }
    public String getDeleteSuccessMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='completionDialog']//div[@class='completion-message']")
                )
        ).getText().trim();
    }
    public void openRegisterForm() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }
    public void navigateToSubjectList() {
        wait.until(ExpectedConditions.elementToBeClickable(settingIcon)).click();
        wait.until(ExpectedConditions.elementToBeClickable(subjectLocator)).click();
    }
    public void deleteSubjectFromAllPages(String schoolName, String subjectCode, String subjectName) {
        while (true) {

            String rowXpath =
                    "//table//tr[" +
                            "td[contains(normalize-space(.),'" + schoolName + "')] and " +
                            "td[contains(normalize-space(.),'" + subjectCode + "')] and " +
                            "td[contains(normalize-space(.),'" + subjectName + "')]" +
                            "]";

            if (!driver.findElements(By.xpath(rowXpath)).isEmpty()) {

                WebElement deleteBtn = driver.findElement(
                        By.xpath(rowXpath + "//a[contains(@class,'delete')]")
                );

                wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
                clickYesButton();
                return;
            }

            // nút next
            java.util.List<WebElement> nextButtons =
                    driver.findElements(By.xpath("//a[@title='次のページ']"));

            if (nextButtons.isEmpty()) {
                break;
            }

            WebElement nextBtn = nextButtons.get(0);

            if (!nextBtn.isDisplayed() || !nextBtn.isEnabled()) {
                break;
            }

            nextBtn.click();

            // chờ bảng load lại
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
        }

        throw new RuntimeException("Không tìm thấy dữ liệu cần xóa trong tất cả phân trang.");
    }
    public boolean isSubjectStillDisplayed(String schoolName, String subjectCode, String subjectName) {
        try {
            By rowLocator = By.xpath("//table//tr[td[contains(normalize-space(),'" + schoolName + "')]"
                    + " and td[contains(normalize-space(),'" + subjectCode + "')]"
                    + " and td[contains(normalize-space(),'" + subjectName + "')]]");

            return driver.findElement(rowLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // chỉnh sửa
    public void editSubjectFromAllPages(String schoolName, String subjectCode, String subjectName) {
        while (true) {

            String rowXpath =
                    "//table//tr[" +
                            "td[contains(normalize-space(.),'" + schoolName + "')] and " +
                            "td[contains(normalize-space(.),'" + subjectCode + "')] and " +
                            "td[contains(normalize-space(.),'" + subjectName + "')]" +
                            "]";

            // nếu tìm thấy dòng
            if (!driver.findElements(By.xpath(rowXpath)).isEmpty()) {

                // click nút edit cùng dòng
                WebElement editBtn = driver.findElement(
                        By.xpath(rowXpath + "//a[contains(@class,'edit')]")
                );

                wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();

                // tick checkbox 有効
                WebElement statusCheckbox = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[@type='checkbox' and @name='status' and @value='1']")
                        )
                );

                if (!statusCheckbox.isSelected()) {
                    statusCheckbox.click();
                }

                // click button 登録
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@type='submit' and contains(@class,'submit-button')]")
                )).click();

                // popup xác nhận
                clickYesButton();

                return;
            }

            // tìm nút trang tiếp
            java.util.List<WebElement> nextButtons =
                    driver.findElements(By.xpath("//a[@title='次のページ']"));

            if (nextButtons.isEmpty()) {
                break;
            }

            WebElement nextBtn = nextButtons.get(0);

            if (!nextBtn.isDisplayed() || !nextBtn.isEnabled()) {
                break;
            }

            nextBtn.click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
        }

        throw new RuntimeException("Không tìm thấy dữ liệu cần chỉnh sửa.");
    }

    //chỉnh sửa bỏ trống
    private By confirmYesButton = By.xpath("//div[@id='confirmDialog']//button[normalize-space()='はい']");
    public void clickConfirmYes() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmYesButton)).click();
    }
    private By errorMessage = By.xpath("//div[@class='error-modal-dialog-message']");

    public String getErrorMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).getText().trim();
    }
    public void editSubjectClearNameFromAllPages(String schoolName, String subjectCode, String subjectName) {
        while (true) {

            String rowXpath =
                    "//table//tr[" +
                            "td[contains(normalize-space(.),'" + schoolName + "')] and " +
                            "td[contains(normalize-space(.),'" + subjectCode + "')] and " +
                            "td[contains(normalize-space(.),'" + subjectName + "')]" +
                            "]";

            if (!driver.findElements(By.xpath(rowXpath)).isEmpty()) {

                // click button edit
                WebElement editBtn = driver.findElement(
                        By.xpath(rowXpath + "//a[contains(@class,'edit')]")
                );
                wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();

                // clear textbox 教科名
                WebElement nameInput = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//input[@type='text' and @name='name']")
                        )
                );
                nameInput.clear();

                // click 更新
                wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

                // popup → click はい
                clickConfirmYe();

                return;
            }

            java.util.List<WebElement> nextButtons =
                    driver.findElements(By.xpath("//a[@title='次のページ']"));

            if (nextButtons.isEmpty()) {
                break;
            }

            WebElement nextBtn = nextButtons.get(0);

            if (!nextBtn.isDisplayed() || !nextBtn.isEnabled()) {
                break;
            }

            nextBtn.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
        }

        throw new RuntimeException("Không tìm thấy dữ liệu cần chỉnh sửa.");
    }
    private By confirmYesButto = By.xpath("//button[@class='confirm-button' and normalize-space()='はい']");
    public void clickConfirmYe() {
        WebElement yesBtn = wait.until(
                ExpectedConditions.elementToBeClickable(confirmYesButto)
        );
        yesBtn.click();
    }
    public String getEditErrorMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='error-modal-dialog-message']")
                )
        ).getText().trim();
    }

    //download file
    private By exportExcelButton = By.id("exportBtn");
    public void clickExportExcel() {
        wait.until(ExpectedConditions.elementToBeClickable(exportExcelButton)).click();
    }
    public File waitForLatestExcelFile(String downloadDir, int timeoutSeconds) throws InterruptedException {
        File dir = new File(downloadDir);
        File latestFile = null;

        for (int i = 0; i < timeoutSeconds; i++) {
            File[] files = dir.listFiles((d, name) ->
                    name.endsWith(".xlsx") || name.endsWith(".xls")
            );

            if (files != null && files.length > 0) {
                latestFile = Arrays.stream(files)
                        .max(Comparator.comparingLong(File::lastModified))
                        .orElse(null);

                if (latestFile != null && latestFile.exists()) {
                    return latestFile;
                }
            }

            Thread.sleep(1000);
        }

        return null;
    }

}