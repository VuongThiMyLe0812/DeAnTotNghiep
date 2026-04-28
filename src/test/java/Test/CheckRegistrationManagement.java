package Test;

import Common.BaseTest;
import Page.DataCheckRegistrationManagementPage;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class CheckRegistrationManagement extends BaseTest {
    @Test
    @Step("CheckRegistrationNoExcel")
    public void DKDL_09_Upload_Invalid_File_Show_Error() {
        DataCheckRegistrationManagementPage page =
                new DataCheckRegistrationManagementPage(driver);
        String invalidFilePath = "D:\\BAOCAOTOTNGHIEP\\a.docx";
        page.openCheckRegistrationPage();
        page.uploadFile(invalidFilePath);
        page.clickRegistration();
        Assert.assertTrue(page.isConfirmationDisplayed(),
                "Không hiển thị popup xác nhận");
        page.clickYesButton();
        Assert.assertTrue(page.isErrorMessageDisplayed(),
                "Không hiển thị thông báo lỗi");
        String actualMessage = page.getErrorMessageText();
        Assert.assertTrue(actualMessage.contains("ファイルの拡張子が不正です"),
                "Sai nội dung thông báo lỗi");
    }

    @Test
    @Step("CheckRegistrationSuccess")
    public void DKDL_10_Upload_Valid_Excel_Register_Success() {
        DataCheckRegistrationManagementPage page =
                new DataCheckRegistrationManagementPage(driver);
        String validFilePath = "D:\\BAOCAOTOTNGHIEP\\乖離度登録フォーマット.xlsx";
        page.openCheckRegistrationPage();
        page.uploadFile(validFilePath);
        page.clickRegistration();
        Assert.assertTrue(page.isConfirmationDisplayed(),
                "Không hiển thị popup xác nhận");
        page.clickYesButton();
        Assert.assertFalse(page.isErrorMessageDisplaye(),
                "Hệ thống hiển thị lỗi khi upload file hợp lệ");
    }

    @Test
    @Step("CheckRegistrationEmptyExcel")
    public void DKDL_11_Upload_Empty_Excel_Show_Error() {
        DataCheckRegistrationManagementPage page =
                new DataCheckRegistrationManagementPage(driver);
        String filePath = "D:\\BAOCAOTOTNGHIEP\\b.xlsx";
        page.clickCheckRegistrationIcon();
        page.uploadFile(filePath);
        page.clickRegistrationButton();
        page.clickYesButton();

        String actualMessage = page.getErrorMessage();
        System.out.println("Actual message: " + actualMessage);

        Assert.assertTrue(
                actualMessage.contains("ファイルが不正です。内容を確認してください。"),
                "Sai nội dung thông báo lỗi"
        );
    }

    DataCheckRegistrationManagementPage page;

    @BeforeMethod
    public void setUpPage() {
        page = new DataCheckRegistrationManagementPage(driver);
    }

    @Step("Click Register without file and verify error message")
    @Test
    public void DKDL_12_Click_Register_Without_File_Show_Error() {
        page.clickCheckRegistrationIcon();
        page.clickRegistrationButton();
        Assert.assertTrue(
                page.isConfirmationDisplayed(),
                "Không hiển thị popup xác nhận"
        );
        page.clickYesButton();
        String actualMessage = page.getErrorMessageText();
        System.out.println("Actual message: " + actualMessage);
        Assert.assertEquals(
                actualMessage.trim(),
                "ファイルを選択してください。",
                "Sai nội dung thông báo lỗi"
        );
    }

    @Test
    @Step("Download file")
    public void DKDL16_testDownloadFormat() {
        DataCheckRegistrationManagementPage page = new DataCheckRegistrationManagementPage(driver);
        page.openCheckRegistrationPage();
        page.clickDownloadFormatButton();
        Assert.assertTrue(page.isSuccessPopupDisplayed(), "Popup thành công phải hiển thị");
        String message = page.getSuccessMessageText();
        Assert.assertEquals(message, "処理が正常に完了しました", "Thông báo phải đúng nội dung");
        page.clickCloseSuccessPopup();
        String downloadDir = "D:\\AUTO_DUANTOTNGHIEP\\PROJECT\\PROJECT\\downloads";
        String expectedFileName = "DeviationFormat.xlsx"; // đổi tên file thực tế
        File downloadedFile = new File(downloadDir, expectedFileName);
        boolean fileExists = false;
        for (int i = 0; i < 20; i++) {
            if (downloadedFile.exists()) {
                fileExists = true;
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}






