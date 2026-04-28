package Test;
import Common.BaseTest;
import Page.TestValidationPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Step;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class DetailResultsTestValidation extends BaseTest {

    @Test
    @Step("Xác minh tải file ZIP phân tích cá nhân")
    public void QLQT28_BulkDownload_IndividualAnalysis_ZipFile() throws InterruptedException {
        TestValidationPage page = new TestValidationPage(driver);
        page.NavigateExamSearch();
        page.clickSearch();
        page.clickDetailByRow(0);
        page.clickBulkDownload();
        File dir = new File("D:\\AUTO_DUANTOTNGHIEP\\PROJECT\\PROJECT\\downloads");
        File downloadedFile = null;
        for (int i = 0; i < 60; i++) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                downloadedFile = Arrays.stream(files)
                        .filter(f -> f.isFile() && f.getName().endsWith(".zip"))
                        .max(Comparator.comparingLong(File::lastModified))
                        .orElse(null);
                if (downloadedFile != null && downloadedFile.exists()) {
                    break;
                }
            }
            Thread.sleep(1000);
        }
        Assert.assertNotNull(downloadedFile, "Không tìm thấy file ZIP tải xuống");
        System.out.println("File ZIP tải xuống: " + downloadedFile.getName());
        Assert.assertTrue(downloadedFile.length() > 0, "File ZIP tải xuống phải có dữ liệu");
    }


    @Test
    @Step("Xác minh tải file phân tích cá nhân qua nút [帳票出力]")
    public void QLQT30_VerifyExportReportDownloadsPdf() {
        TestValidationPage page = new TestValidationPage(driver);

        // Step 1: Truy cập vào hệ thống (đã login trong BaseTest)
        page.NavigateExamSearch();

        // Step 2: Vào menu chọn "試験結果表示"
        // page.clickSearch();

        // Step 3: Click vào button [詳細] của một row (ví dụ row đầu tiên)
        page.clickDetailByRow(0);

        // Step 4: Click vào button [結果を見る]
        page.clickShowResults();
        //System.out.println("Số nút export tìm thấy: " + driver.findElements(ExportReportsEachPerson).size());

        page.clickShowResults();
        //Thread.sleep(3000); // chờ 3 giây cho nút render
        page.clickExportReport();


        // Step 5: Click vào button [帳票出力]
        page.clickExportReport();

        // Step 6: Kiểm tra file PDF tải xuống
        String downloadDir = "D:\\AUTO_DUANTOTNGHIEP\\PROJECT\\PROJECT\\downloads";
        File dir = new File(downloadDir);

        File downloadedFile = null;
        for (int i = 0; i < 60; i++) { // chờ tối đa 60 giây
            File[] files = dir.listFiles((d, name) -> name.endsWith(".pdf"));
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
        Assert.assertNotNull(downloadedFile, "Không tìm thấy file PDF tải xuống");
        System.out.println("File tải xuống: " + downloadedFile.getName());
        Assert.assertTrue(downloadedFile.length() > 0, "File PDF tải xuống phải có dữ liệu");
    }
}

