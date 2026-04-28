package Test;

import Common.BaseTest;
import Page.ExamList;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

public class Search_Exam_Management extends BaseTest {

    @Test
    @Step("Search Exam By Year Only")
    public void QLKT16_01_SearchExamByYearOnly() throws InterruptedException {
        ExamList examList = new ExamList(driver);
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
    @Step("Search Exam By Year Only")
    public void QLKT16_02_SearchExamByYearOnly() throws InterruptedException {
        ExamList examList = new ExamList(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("2026");
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
    @Step("Search Exam By Year Only")
    public void QLKT16_03_SearchExamByYearOnly() throws InterruptedException {
        ExamList examList = new ExamList(driver);
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
    @Step("Search Exam By Grade Only")
    public void QLKT17_SearchExamByGradeOnly() throws InterruptedException {
        ExamList examList = new ExamList(driver);
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
    @Step ("Search Exam By Subject Only")
    public void QLKT18_01_SearchExamBySubjectOnly() throws InterruptedException {
        ExamList examList = new ExamList(driver);
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
    @Step ("Search Exam By Subject Only")
    public void QLKT18_02_SearchExamBySubjectOnly() throws InterruptedException {
        ExamList examList = new ExamList(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("");
        Thread.sleep(1000);
        examList.selectSubject("国語");
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
    @Step ("Search Exam By Subject Only")
    public void QLKT18_03_SearchExamBySubjectOnly() throws InterruptedException {
        ExamList examList = new ExamList(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("");
        Thread.sleep(1000);
        examList.selectSubject("理科");
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
    @Step ("Search Exam By Subject Only")
    public void QLKT18_04_SearchExamBySubjectOnly() throws InterruptedException {
        ExamList examList = new ExamList(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("");
        Thread.sleep(1000);
        examList.selectSubject("英語");
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
    @Step ("Search Exam By Test Only")
    public void QLKT19_SearchExamByTestOnly() throws InterruptedException {
        ExamList examList = new ExamList(driver);
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
    @Step ("Search Exam By Status Only")
    public void QLKT20_01_searchExamByStatusOnly() throws InterruptedException {
        ExamList examList = new ExamList(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("");
        Thread.sleep(1000);
        examList.selectStatus("未分析");
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
    @Step ("Search Exam By Status Only")
    public void QLKT20_02_searchExamByStatusOnly() throws InterruptedException {
        ExamList examList = new ExamList(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("");
        Thread.sleep(1000);
        examList.selectStatus("分析中");
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
    @Step ("Search Exam By Status Only")
    public void QLKT20_03_searchExamByStatusOnly() throws InterruptedException {
        ExamList examList = new ExamList(driver);
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
    @Step ("Search Exam By Status Only")
    public void QLKT20_04_searchExamByStatusOnly() throws InterruptedException {
        ExamList examList = new ExamList(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("");
        Thread.sleep(1000);
        examList.selectStatus("英分析済み");
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
    @Step ("Search Exam By Status Only")
    public void QLKT20_05_searchExamByStatusOnly() throws InterruptedException {
        ExamList examList = new ExamList(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("");
        Thread.sleep(1000);
        examList.selectStatus("分析エラー");
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
    @Step ("Search MultipleConditions")
    public void QLKT21_SearchMultipleConditions() throws InterruptedException {
        ExamList examList = new ExamList(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("2026");
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
    @Step("Search Exam By Condition")
    public void QLKT22_SearchExamByCondition() throws InterruptedException {
        ExamList examList = new ExamList(driver);
        Thread.sleep(1000);
        examList.NavigateExamSearch();
        Thread.sleep(1000);
        examList.selectYear("2027");
        Thread.sleep(1000);
        examList.selectGrade("中学校A-1年");
        Thread.sleep(1000);
        examList.clickSearch();
        Assert.assertTrue(
                examList.isNoDataMessageDisplayedSafe(),
                "Expected không có dữ liệu nhưng vẫn có data"
        );
    }

    @Test
    @Step("Search Exam Without Any Condition")
    public void QLKT23_SearchExamWithoutCondition() {
        ExamList examList = new ExamList(driver);
        examList.NavigateExamSearch();
        examList.clickSearch();
        Assert.assertFalse(
                examList.isNoDataMessageDisplayedSafe(),
                "Expected có dữ liệu nhưng lại hiển thị 'no data'"
        );
    }
}






