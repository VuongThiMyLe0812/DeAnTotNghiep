package Test;

import Common.BaseTest;
import Page.DataCheckRegistrationManagementPage;
import Page.RegisterExamResultsPage;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterExamResults extends BaseTest {
        @Test
        @Step("Upload file không phải Excel")
        public void DKKQ_09_Upload_Invalid_File_Show_Error() {
            RegisterExamResultsPage page = new RegisterExamResultsPage(driver);
            String invalidFilePath = "D:\\BAOCAOTOTNGHIEP\\a.docx";
            page.openRegistrationPage();
            page.uploadFile(invalidFilePath);
            page.clickRegistration();
            page.clickYesButton();
            String actualMessage = page.getErrorMessageText();
            Assert.assertTrue(actualMessage.contains("ファイルの拡張子が不正です"),
                    "Sai nội dung thông báo lỗi");
        }

        @Test
        @Step("Upload file Excel rỗng")
        public void DKKQ_10_Upload_Empty_Excel_Show_Error() {
            RegisterExamResultsPage page = new RegisterExamResultsPage(driver);
            String emptyExcelFilePath = "D:\\BAOCAOTOTNGHIEP\\b.xlsx";
            page.openRegistrationPage();
            page.uploadFile(emptyExcelFilePath);
            page.clickRegistration();
            page.clickYesButton();
            String actualMessage = page.getErrorMessage();
            System.out.println("Actual message: " + actualMessage);
            Assert.assertTrue(
                    actualMessage.contains("ファイルが不正です。内容を確認してください。"),
                    "Sai nội dung thông báo lỗi"
            );
        }

    @Test
    @Step("Đăng ký mà không chọn file")
    public void DKKQ_11_No_File_Upload_Show_Error() {
        RegisterExamResultsPage page = new RegisterExamResultsPage(driver);
        page.openRegistrationPage();
        page.clickRegistration();
        Assert.assertTrue(page.isConfirmationDisplayed(),
                "Không hiển thị popup xác nhận");
        page.clickYesButton();
        Assert.assertTrue(page.isErrorMessageDisplayed(),
                "Không hiển thị thông báo lỗi");
        String actualMessage = page.getErrorMessageText();
        Assert.assertTrue(actualMessage.contains("ファイルを選択してください。"),
                "Sai nội dung thông báo lỗi");
    }
}



