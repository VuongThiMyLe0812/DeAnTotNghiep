package Test;

import Common.BaseTest;
import Page.ExamList;
import Page.TestValidationPage;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

public class Filter_TestValidation extends BaseTest {

    @Test
    @Step("Fill Test Validation By Year Only")
    public void QLQT18_FillTestValidationByYearOnly() throws InterruptedException {
        TestValidationPage examList = new TestValidationPage(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("2025");
        Thread.sleep(1000);
        examList.clickSearch();

        List<Map<String, String>> results = examList.getSearchResults();
        Assert.assertTrue(results.size() > 0, "Không có dữ liệu cho năm 2025");
        for (Map<String, String> row : results) {
            String actualYear = row.get("年度");
            if (actualYear == null || actualYear.trim().isEmpty()) continue;
            actualYear = actualYear.trim();
            Assert.assertEquals(actualYear, "2025",
                    "Sai 年度 tại row: " + row);
        }
    }
    @Test
    @Step("Fill Test Validation By Year Only")
    public void QLQT18_01_FillTestValidationByYearOnly() throws InterruptedException {
        TestValidationPage examList = new TestValidationPage(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("202");
        Thread.sleep(1000);
        examList.clickSearch();

        List<Map<String, String>> results = examList.getSearchResults();
        Assert.assertTrue(results.size() > 0, "Không có dữ liệu cho năm 2026");
        for (Map<String, String> row : results) {
            String actualYear = row.get("年度");
            if (actualYear == null || actualYear.trim().isEmpty()) continue;
            actualYear = actualYear.trim();
            Assert.assertEquals(actualYear, "2026",
                    "Sai 年度 tại row: " + row);
        }
    }
    @Test
    @Step("Fill Test Validation By Year Only")
    public void QLQT18_02_FillTestValidationByYearOnly() throws InterruptedException {
        TestValidationPage examList = new TestValidationPage(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("2027");
        Thread.sleep(1000);
        examList.clickSearch();

        List<Map<String, String>> results = examList.getSearchResults();
        Assert.assertTrue(results.size() > 0, "Không có dữ liệu cho năm 2027");
        for (Map<String, String> row : results) {
            String actualYear = row.get("年度");
            if (actualYear == null || actualYear.trim().isEmpty()) continue;
            actualYear = actualYear.trim();
            Assert.assertEquals(actualYear, "2027",
                    "Sai 年度 tại row: " + row);
        }
    }

    @Test
    @Step("Fill Test Validation By Grade Only")
    public void QLQT19_FillTestValidationByGradeOnly() throws InterruptedException {
        TestValidationPage examList = new TestValidationPage (driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("");
        Thread.sleep(1000);
        examList.selectGrade("中学校10年");
        Thread.sleep(1000);
        examList.clickSearch();

        List<Map<String, String>> results = examList.getSearchResults();
        Assert.assertTrue(results.size() > 0, "Không có dữ liệu cho 中学校10年");
        for (Map<String, String> row : results) {
            String actualGrade = row.get("学年");
            if (actualGrade == null || actualGrade.trim().isEmpty()) continue;
            actualGrade = actualGrade.trim();
            Assert.assertEquals(actualGrade, "中学校10年",
                    "Sai 学年 tại row: " + row);
        }
    }

    @Test
    @Step ("Fill Test Validation By Subject Only")
    public void QLQT20_FillTestValidationBySubjectOnly() throws InterruptedException {
        TestValidationPage examList = new TestValidationPage(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("");
        Thread.sleep(1000);
        examList.selectSubject("数学");
        Thread.sleep(1000);
        examList.clickSearch();

        List<Map<String, String>> results = examList.getSearchResults();
        Assert.assertTrue(results.size() > 0, "Không có dữ liệu cho 数学");
        for (Map<String, String> row : results) {
            String actualSubject = row.get("科目");
            if (actualSubject == null || actualSubject.trim().isEmpty()) continue;
            actualSubject = actualSubject.trim();
            Assert.assertEquals(actualSubject, "数学",
                    "Sai 科目 tại row: " + row);
        }
    }

    @Test
    @Step ("Fill Test Validation By Test Only")
    public void QLQT21_FillTestValidationByTestOnly() throws InterruptedException {
        TestValidationPage examList = new TestValidationPage (driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("");
        Thread.sleep(1000);
        examList.selectExamName("2025_2春スタンダード（国語）");
        Thread.sleep(1000);
        examList.clickSearch();

        List<Map<String, String>> results = examList.getSearchResults();
        Assert.assertTrue(results.size() > 0, "Không có dữ liệu cho 2025_2春スタンダード（国語）");
        for (Map<String, String> row : results) {
            String actualTest = row.get("試験名");
            if (actualTest == null || actualTest.trim().isEmpty()) continue;
            actualTest = actualTest.trim();
            Assert.assertEquals(actualTest, "2025_2春スタンダード（国語）",
                    "Sai 試験名 tại row: " + row);
        }
    }

    @Test
    @Step ("Fill Test Validation By Status Only")
    public void QLQT22_FillTestValidationByStatusOnly() throws InterruptedException {
        TestValidationPage examList = new TestValidationPage(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("");
        Thread.sleep(1000);
        examList.selectStatus("分析中(1次分析済み)");
        Thread.sleep(1000);
        examList.clickSearch();

        List<Map<String, String>> results = examList.getSearchResults();
        Assert.assertTrue(results.size() > 0, "Không có dữ liệu cho 分析中(1次分析済み)");
        for (Map<String, String> row : results) {
            String actualStatus = row.get("ステータス");
            if (actualStatus == null || actualStatus.trim().isEmpty()) continue;
            actualStatus = actualStatus.trim();
            Assert.assertEquals(actualStatus, "分析中(1次分析済み)",
                    "Sai ステータス tại row: " + row);
        }
    }

    @Test
    @Step ("Fill Test Validation By Status Question Analysis Only")
    public void QLQT23_FillTestValidationBystatusQuestionAnalysisOnly() throws InterruptedException {
        TestValidationPage examList = new TestValidationPage(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("");
        Thread.sleep(1000);
        examList.statusQuestionAnalysis("システム管理者確認中");
        Thread.sleep(1000);
        examList.clickSearch();
        List<Map<String, String>> results = examList.getSearchResults();
        Assert.assertTrue(results.size() > 0, "Không có dữ liệu cho システム管理者確認中");
        for (Map<String, String> row : results) {
            String actualStatus = row.get("\t\n" +
                    "設問評価分析ステータス");
            if (actualStatus == null || actualStatus.trim().isEmpty()) continue;
            actualStatus = actualStatus.trim();
            Assert.assertEquals(actualStatus, "システム管理者確認中",
                    "Sai \t\n" +
                            "設問評価分析ステータス tại row: " + row);
        }
    }

    @Test
    @Step ("Fill Test Validation By Status Question Analysis Only")
    public void QLQT23_01_FillTestValidationBystatusQuestionAnalysisOnly() throws InterruptedException {
        TestValidationPage examList = new TestValidationPage(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("");
        Thread.sleep(1000);
        examList.statusQuestionAnalysis("分析中");
        Thread.sleep(1000);
        examList.clickSearch();
        List<Map<String, String>> results = examList.getSearchResults();
        Assert.assertTrue(results.size() > 0, "Không có dữ liệu cho 分析中");
        for (Map<String, String> row : results) {
            String actualStatus = row.get("\t\n" +
                    "設問評価分析ステータス");
            if (actualStatus == null || actualStatus.trim().isEmpty()) continue;
            actualStatus = actualStatus.trim();
            Assert.assertEquals(actualStatus, "分析中",
                    "Sai \t\n" +
                            "設問評価分析ステータス tại row: " + row);
        }
    }
    @Test
    @Step ("Fill Test Validation By Status Question Analysis Only")
    public void QLQT23_02_FillTestValidationBystatusQuestionAnalysisOnly() throws InterruptedException {
        TestValidationPage examList = new TestValidationPage(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("");
        Thread.sleep(1000);
        examList.statusQuestionAnalysis("業務管理者確認中");
        Thread.sleep(1000);
        examList.clickSearch();
        List<Map<String, String>> results = examList.getSearchResults();
        Assert.assertTrue(results.size() > 0, "Không có dữ liệu cho 業務管理者確認中");
        for (Map<String, String> row : results) {
            String actualStatus = row.get("\t\n" +
                    "設問評価分析ステータス");
            if (actualStatus == null || actualStatus.trim().isEmpty()) continue;
            actualStatus = actualStatus.trim();
            Assert.assertEquals(actualStatus, "業務管理者確認中",
                    "Sai \t\n" +
                            "設問評価分析ステータス tại row: " + row);
        }
    }
    @Test
    @Step ("Fill Test Validation By Status Question Analysis Only")
    public void QLQT23_03_FillTestValidationBystatusQuestionAnalysisOnly() throws InterruptedException {
        TestValidationPage examList = new TestValidationPage(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("");
        Thread.sleep(1000);
        examList.statusQuestionAnalysis("承認済み");
        Thread.sleep(1000);
        examList.clickSearch();
        List<Map<String, String>> results = examList.getSearchResults();
        Assert.assertTrue(results.size() > 0, "Không có dữ liệu cho 承認済み");
        for (Map<String, String> row : results) {
            String actualStatus = row.get("\t\n" +
                    "設問評価分析ステータス");
            if (actualStatus == null || actualStatus.trim().isEmpty()) continue;
            actualStatus = actualStatus.trim();
            Assert.assertEquals(actualStatus, "承認済み",
                    "Sai \t\n" +
                            "設問評価分析ステータス tại row: " + row);
        }
    }
    @Test
    @Step ("Fill Test Validation By Status Question Analysis Only")
    public void QLQT23_04_FillTestValidationBystatusQuestionAnalysisOnly() throws InterruptedException {
        TestValidationPage examList = new TestValidationPage(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("");
        Thread.sleep(1000);
        examList.statusQuestionAnalysis("分析エラー");
        Thread.sleep(1000);
        examList.clickSearch();
        List<Map<String, String>> results = examList.getSearchResults();
        Assert.assertTrue(results.size() > 0, "Không có dữ liệu cho 分析エラー");
        for (Map<String, String> row : results) {
            String actualStatus = row.get("\t\n" +
                    "設問評価分析ステータス");
            if (actualStatus == null || actualStatus.trim().isEmpty()) continue;
            actualStatus = actualStatus.trim();
            Assert.assertEquals(actualStatus, "分析エラー",
                    "Sai \t\n" +
                            "設問評価分析ステータス tại row: " + row);
        }
    }

    @Test
    @Step("Fill Test Validation By Grade and Subject")
    public void QLQT24_FillTestValidationByGradeAndSubject() throws InterruptedException {
        TestValidationPage examList = new TestValidationPage(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectGrade("中学校2年");
        Thread.sleep(1000);
        examList.selectSubject("数学");
        Thread.sleep(1000);
        examList.clickSearch();
        //  Lấy kết quả tìm kiếm
        List<Map<String, String>> results = examList.getSearchResults();
        //  Xác minh có dữ liệu trả về
        Assert.assertTrue(results.size() > 0, "Không có dữ liệu cho bộ lọc đã chọn");
        // Kiểm tra từng dòng kết quả khớp với bộ lọc
        for (Map<String, String> row : results) {
            String actualGrade = row.get("学年");
            String actualSubject = row.get("科目");

            if (actualGrade != null && !actualGrade.trim().isEmpty()) {
                Assert.assertEquals(actualGrade.trim(), "中学校2年",
                        "Sai giá trị 学年 tại row: " + row);
            }

            if (actualSubject != null && !actualSubject.trim().isEmpty()) {
                Assert.assertEquals(actualSubject.trim(), "数学",
                        "Sai giá trị 科目 tại row: " + row);
            }
        }
    }

    @Test
    @Step("Fill Test Validation By Grade and Subject - No Data")
    public void QLQT25_FillTestValidationByGradeAndSubject_NoData() throws InterruptedException {
        TestValidationPage examList = new TestValidationPage(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectGrade("中学校1年");
        Thread.sleep(1000);
        examList.selectSubject("国語");
        Thread.sleep(1000);
        examList.clickSearch();
        List<Map<String, String>> results = examList.getSearchResults();
        Assert.assertTrue(results.isEmpty(), "条件に該当する試験結果がありません");
    }
}