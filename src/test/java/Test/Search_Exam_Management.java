package Test;

import Common.BaseTest;
import Page.ExamList;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

public class Exam_Management {

    public class QLKT16 extends BaseTest {
        @Test
        @Step("Search Exam By Year Only")
        public void searchExamByYearOnly() throws InterruptedException {
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
    }
    public class QLKT17 extends BaseTest {

        @Test
        @Step ("Search Exam By Grade Only")
        public void searchExamByGradeOnly() throws InterruptedException {
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
    }

}
