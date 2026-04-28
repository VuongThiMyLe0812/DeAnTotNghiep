package Test;

import Common.BaseTest;
import Page.SubjectRegistrationPage;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubjectDelete extends BaseTest {
    //    @Step("Verify Delete Subject Success")
//    @Test
//    public void QLMH11_verifyDeleteSubject() {
//        SubjectRegistrationPage subjectPage = new SubjectRegistrationPage(driver);
//        subjectPage.navigateToSubjectList();
//        subjectPage.deleteSubjectFromAllPages(
//                "aaaaaa",
//                "J090",
//                "外国語（中学）"
//        );
//        String actual = subjectPage.getDeleteSuccessMessage();
//        Assert.assertEquals(actual, "教科を削除しました");
//    }
//}
    @Step("Verify Delete Subject Success")
    @Test
    public void QLMH11_verifyDeleteSubject() {
        SubjectRegistrationPage subjectPage = new SubjectRegistrationPage(driver);

        String schoolName = "aaaaaa";
        String subjectCode = "J090";
        String subjectName = "外国語（中学）";
        subjectPage.navigateToSubjectList();

        subjectPage.deleteSubjectFromAllPages(
                schoolName,
                subjectCode,
                subjectName
        );
        String actual = subjectPage.getDeleteSuccessMessage();
        Assert.assertEquals(actual, "教科を削除しました");
        Assert.assertFalse(
                subjectPage.isSubjectStillDisplayed(schoolName, subjectCode, subjectName),
                "Dữ liệu vẫn còn hiển thị trong bảng sau khi xóa"
        );
    }
}

