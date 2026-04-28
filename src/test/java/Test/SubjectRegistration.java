package Test;
import Common.BaseTest;
import Page.SubjectRegistrationPage;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class SubjectRegistration extends BaseTest {
    @Test
    @Step("Register Subject Successfully")
    public void QLMH12_RegisterSubjectSuccessfully() throws InterruptedException {
        SubjectRegistrationPage subjectPage = new SubjectRegistrationPage(driver);
        subjectPage.naviagateRegisterSubjectForn();
        Thread.sleep(1000);
        subjectPage.fillSubjectForm("aaaaaa", "外国語（中学）");
        Thread.sleep(3000);
        String actualMsg = subjectPage.getCompletionMessage();
        Assert.assertEquals(
                actualMsg,
                "教科情報を保存しました",
                "Message không đúng!"
        );
    }

    @Test
    @Step("Empty Space")
    public void QLMH13_EmptySpace() throws InterruptedException {
        SubjectRegistrationPage subjectPage = new SubjectRegistrationPage(driver);
        subjectPage.naviagateRegisterSubjectForn();
        Thread.sleep(1000);
        subjectPage.clickSubmit();
        subjectPage.clickYesButton();
        String actualMsg = subjectPage.getBlankMessage();
        System.out.println("Actual message: [" + actualMsg + "]");
        Assert.assertTrue(actualMsg.contains("教科コードは必須項目です"), "Không thấy thông báo thiếu mã môn!");
        Assert.assertTrue(actualMsg.contains("教科名の形式が正しくありません"), "Không thấy thông báo lỗi định dạng tên môn!");
        Assert.assertTrue(actualMsg.contains("学校名は必須項目です"), "Không thấy thông báo thiếu tên trường!");
    }

    @Test
    @Step("Empty Subject Code")
    public void QLMH14_verifyErrorWhenRequiredFieldMissing() {
        SubjectRegistrationPage subjectPage = new SubjectRegistrationPage(driver);
        subjectPage.naviagateRegisterSubjectForn();
        subjectPage.fillOnlySchool("School0001_updatename");
        subjectPage.clickSubmit();
        subjectPage.clickYesButton();
        String actualMessage = subjectPage.getBlankMessage();
        String expectedMessage = "教科コードは必須項目です\n" +
                "教科名の形式が正しくありません";
        Assert.assertEquals(actualMessage, expectedMessage,
                "Hệ thống không hiển thị đúng lỗi khi thiếu trường bắt buộc");
    }

    @Test
    @Step("Register With Duplication Subject")
    public void QLMH15_RegisterWithDuplicateSubject1() throws InterruptedException {
        SubjectRegistrationPage subjectPage = new SubjectRegistrationPage(driver);
        subjectPage.naviagateRegisterSubjectForn();
        Thread.sleep(1000);
        subjectPage.fillSubjectForm("School_A", "社会（中学）");
        String actualMsg = subjectPage.getDuplicateMessage();
        Assert.assertEquals(
                actualMsg,
                "この教科コードはすでに登録されています",
                "Message không đúng!"
        );
    }

    @Step("Export Subject Excel")
    @Test
    public void QLMH16_ExportExcel() throws InterruptedException {
        SubjectRegistrationPage subjectPage = new SubjectRegistrationPage(driver);
        String downloadPath = System.getProperty("user.dir") + "\\downloads";
        subjectPage.navigateToSubjectList();
        subjectPage.clickExportExcel();
        File downloadedFile = subjectPage.waitForLatestExcelFile(downloadPath, 10);
        Assert.assertNotNull(downloadedFile, "Không tìm thấy file Excel tải xuống");
        Assert.assertTrue(downloadedFile.exists(), "File Excel chưa được tải");
    }
}
