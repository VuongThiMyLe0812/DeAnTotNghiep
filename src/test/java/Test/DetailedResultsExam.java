package Test;
import Common.BaseTest;
import Page.ExamList;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;

public class DetailedResultsExam extends BaseTest {

    @Test
    @Step ("ClickDetail Open Detail Page")
    public void QLKT24_ClickDetail_OpenDetailPage() {
        ExamList examList = new ExamList(driver);
        examList.NavigateExamSearch();
        Assert.assertTrue(examList.hasRealData(), "Không có dữ liệu để test");
        String expectedExamName = examList.getExamNameByRow(0);
        examList.clickDetailByRow(0);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("detail"));
        Assert.assertTrue(driver.getCurrentUrl().contains("detail"),
                "Không chuyển sang trang detail");
        String pageText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(pageText.contains(expectedExamName),
                "Trang detail không hiển thị đúng bài thi đã chọn");
    }

    @Step("Xác minh tải file phân tích cá nhân")
    @Test
    public void QLKT_25_BulkDownload_IndividualAnalysis_Success() {
        ExamList examListPage = new ExamList(driver);
        examListPage.NavigateExamSearch();
        examListPage.clickDetailByRow(0);
        examListPage.clickBulkDownload();
        String downloadDir = "D:\\AUTO_DUANTOTNGHIEP\\PROJECT\\PROJECT\\downloads";
        File dir = new File(downloadDir);
        File downloadedFile = null;
        for (int i = 0; i < 60; i++) { // chờ tối đa 60 giây
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                downloadedFile = Arrays.stream(files)
                        .filter(File::isFile)
                        .max(Comparator.comparingLong(File::lastModified))
                        .orElse(null);
                if (downloadedFile != null && downloadedFile.exists()) {
                    break;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertNotNull(downloadedFile, "Không tìm thấy file tải xuống");
        System.out.println("File tải xuống: " + downloadedFile.getName());
        Assert.assertTrue(downloadedFile.length() > 0, "File tải xuống phải có dữ liệu");
}


    @Test
    @Step ("ClickViewResult Show Full Exam Detail")
    public void QLKT26_ClickViewResult_ShowFullExamDetail() {
        ExamList examList = new ExamList(driver);
        examList.NavigateExamSearch();
        Assert.assertTrue(examList.hasRealData(),
                "Không có dữ liệu để test");
        examList.clickDetailByRow(0);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("detail"));
        examList.clickViewResult();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        Assert.assertTrue(examList.isResultDetailDisplayed(),
                "Không hiển thị đầy đủ thông tin chi tiết bài thi");
    }
}



