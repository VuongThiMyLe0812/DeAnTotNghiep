package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.*;

public class TestValidationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Icon để mở trang tìm kiếm
    private By TestvalidationIcon = By.xpath("//a[.//div[text()='設問評価承認']]");

    private By FillData = By.className("search-button");
    private By DetailValidationIcon = By.xpath(".//a[contains(text(),'詳細') or contains(@class,'detail')]");
    private By ShowResultsIcon = By.xpath("//a[contains(@class,'result-link') and normalize-space()='結果を見る']");
    private By ExportReportsEachPerson = By.xpath("//a[@class='export-button']");

    public TestValidationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Điều hướng vào trang tìm kiếm
    public void NavigateExamSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(TestvalidationIcon));
        driver.findElement(TestvalidationIcon).click();
    }

    // YEAR
    public void selectYear(String year) {
        WebElement yearDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("year-select"))
        );
        new Select(yearDropdown).selectByVisibleText(year);
    }

    // GRADE
    public void selectGrade(String grade) {
        WebElement gradeDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("grade"))
        );
        new Select(gradeDropdown).selectByVisibleText(grade);
    }

    // SUBJECT
    public void selectSubject(String subject) {
        WebElement subjectDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("subject"))
        );
        new Select(subjectDropdown).selectByVisibleText(subject);
    }

    // EXAM NAME
    public void selectExamName(String examName) {
        WebElement examNameDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("test"))
        );
        new Select(examNameDropdown).selectByVisibleText(examName);
    }

    // STATUS
    public void selectStatus(String status) {
        WebElement statusDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("status"))
        );
        new Select(statusDropdown).selectByVisibleText(status);
    }

    public void statusQuestionAnalysis(String status) {
        WebElement statusQuestionAnalysisDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("question_evaluation_status"))
        );
        new Select(statusQuestionAnalysisDropdown).selectByVisibleText(status);
    }
    public void fillExamSearch(String year, String grade, String subject, String examName, String status, String question_evaluation_status) {
        selectYear(year);
        selectGrade(grade);
        selectSubject(subject);
        selectExamName(examName);
        selectStatus(status);
        statusQuestionAnalysis(question_evaluation_status);
    }

    // Click nút Search
    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(FillData)).click();
    }


    public List<Map<String, String>> getSearchResults() {
        List<Map<String, String>> results = new ArrayList<>();

        List<WebElement> rows = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("table.data-table tbody tr")
                )
        );

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() < 2) continue;
            Map<String, String> data = new HashMap<>();
            data.put("No", cells.get(0).getText().trim());
            data.put("年度", cells.get(1).getText().trim());
            data.put("試験名", cells.get(2).getText().trim());
            data.put("学年", cells.get(3).getText().trim());
            data.put("科目", cells.get(4).getText().trim());
            data.put("ステータス", cells.get(6).getText().trim());
            data.put("設問評価分析ステータス", cells.get(9).getText().trim());
            results.add(data);
        }

        return results;
    }

    // download
    private By bulkDownloadBtn = By.id("g0080-bulk-download-btn");

    // Hàm click vào nút [詳細] theo row index
    public void clickDetailByRow(int rowIndex) {
        List<WebElement> rows = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("table.data-table tbody tr")
                )
        );
        WebElement row = rows.get(rowIndex);
        WebElement detailBtn = row.findElement(DetailValidationIcon);
        wait.until(ExpectedConditions.elementToBeClickable(detailBtn)).click();
    }

    // Hàm click vào nút download
    public void clickBulkDownload() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(bulkDownloadBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }


    public void clickShowResults() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(ShowResultsIcon));
        btn.click();
    }

    // Hàm click vào [帳票出力]
    public void clickExportReport() {
        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(ExportReportsEachPerson));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }




    // Hàm chờ file PDF mới nhất trong thư mục download
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
            Thread.sleep(1000); // chờ 1s rồi kiểm tra lại
        }
        return null;
    }


}