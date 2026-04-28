package Test;

import Common.BaseTest;
import Page.SubjectRegistrationPage;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubjectEdit extends BaseTest {
    @Step ("Verify Edit Subject")
    @Test
    public void QLMH09_verifyEditSubject() {
        SubjectRegistrationPage subjectPage = new SubjectRegistrationPage(driver);
        subjectPage.navigateToSubjectList();
        subjectPage.editSubjectFromAllPages(
                "aaaaaa",
                "J090",
                "外国語（中学）"
        );
        String actual = subjectPage.getCompletionMessage();
        Assert.assertEquals(actual, "教科情報を保存しました");
    }

    @Step("Edit BlankName")
    @Test
    public void QLMH10_Edit_BlankName() {
        SubjectRegistrationPage subjectPage = new SubjectRegistrationPage(driver);
        subjectPage.navigateToSubjectList();
        subjectPage.editSubjectClearNameFromAllPages(
                "aaaaaa",
                "J090",
                "外国語（中学）"
        );
        String actual = subjectPage.getErrorMessage();
        Assert.assertEquals(actual, "教科名の形式が正しくありません");
    }
}
