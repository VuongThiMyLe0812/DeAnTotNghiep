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

public class ExamList {
    private WebDriver driver;
    private WebDriverWait wait;

    // Icon để mở trang tìm kiếm
    private By ExamListIcon = By.xpath("//a[@class='menu-card' and @href='https://dev-ssi-cbt-analysis.com/G0070/classes/quiz_list.php?question_evaluation_display_flag=0']");

    // Nút Search
    private By Search = By.className("search-button");

    public ExamList(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Điều hướng vào trang tìm kiếm
    public void NavigateExamSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(ExamListIcon));
        driver.findElement(ExamListIcon).click();
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
    public void fillExamSearch(String year, String grade, String subject, String examName, String status) {
        selectYear(year);
        selectGrade(grade);
        selectSubject(subject);
        selectExamName(examName);
        selectStatus(status);
    }

    // Click nút Search
    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(Search)).click();
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

            results.add(data);
        }

        return results;
    }

    // Hàm reset form (nếu có nút reset)
    public void resetSearchForm() {
        WebElement resetButton = driver.findElement(By.className("reset-button"));
        resetButton.click();
    }


    // ===== SAFE VERSION =====
    public boolean isNoDataMessageDisplayedSafe() {
        List<WebElement> elements = driver.findElements(
                By.xpath("//td[contains(text(),'条件に該当する試験結果がありません')]")
        );

        return elements.size() > 0 && elements.get(0).isDisplayed();
    }

    public boolean hasRealData() {
        List<Map<String, String>> results = getSearchResults();

        for (Map<String, String> row : results) {
            String examName = row.get("試験名");
            if (examName != null && !examName.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
    public void clickDetailByRow(int rowIndex) {
        List<WebElement> rows = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("table.data-table tbody tr")
                )
        );

        WebElement row = rows.get(rowIndex);

        WebElement detailBtn = row.findElement(
                By.xpath(".//a[contains(text(),'詳細') or contains(@class,'detail')]")
        );

        wait.until(ExpectedConditions.elementToBeClickable(detailBtn)).click();
    }

    // Lấy 試験名 theo row để verify sau khi click
    public String getExamNameByRow(int rowIndex) {
        List<WebElement> rows = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("table.data-table tbody tr")
                )
        );

        return rows.get(rowIndex)
                .findElements(By.tagName("td"))
                .get(2) // column 試験名
                .getText()
                .trim();
    }

//download



    private By downloadBtn = By.id("g0080-bulk-download-btn");
    private By viewResultBtn = By.className("result-link");
    private By errorToast = By.xpath("//*[contains(text(),'Something went wrong')]");

    public void clickBulkDownload() {
        // Chờ overlay biến mất
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("sidebarOverlay")));

        // Tìm nút
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(downloadBtn));

        // Scroll vào view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);

        // Chờ nút có thể click
        wait.until(ExpectedConditions.elementToBeClickable(btn));

        // Click bằng JS để tránh bị che
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public boolean isErrorToastDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return shortWait.until(
                    ExpectedConditions.visibilityOfElementLocated(errorToast)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickViewResult() {
        wait.until(ExpectedConditions.elementToBeClickable(viewResultBtn)).click();
    }
    public boolean isResultDetailDisplayed() {
        String bodyText = driver.findElement(By.tagName("body")).getText();

        return bodyText.contains("氏名")
                || bodyText.contains("得点")
                || bodyText.contains("結果");
    }
}
