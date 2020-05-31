package wework.testcase;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import wework.page.ContactPage;
import wework.page.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactTest {
    private static ContactPage contactPage;
    private static MainPage mainPage;
    private static String mobile;
    private static String department;
    private static String tagName;
    private static String tagUser;

    @BeforeAll
    public static void setUp() {
        mainPage = new MainPage();
        mainPage.weWorkSetCookies(MainPage.cookiePath);
        contactPage = mainPage.toContact();
        mobile = "13211112229";
        department = "Python";
        tagName = "领导";
        tagUser = "仅创建人（及同管理组）";
    }

    @Test
    @Order(1)
    @DisplayName("添加成员")
    void addMember() {
        contactPage.addMember("zhangsan", "Mr.zhang", "8", mobile);
    }

    @Test
    @Order(2)
    @DisplayName("搜索成员")
    void searchMember() {
        contactPage.search(mobile);
        assertEquals(mobile, contactPage.getElementText(
                By.cssSelector(".member_display_item_Phone > .member_display_item_right")));
    }

    @Test
    @Order(3)
    @DisplayName("删除成员")
    void deleteMember() {
        contactPage.search(mobile);
        contactPage.deleteMember();
    }

    @Test
    @Order(4)
    @DisplayName("添加部门")
    void addDepartment() {
        contactPage.addDepartment(department);
    }

    @Test
    @Order(5)
    @DisplayName("搜索部门")
    void searchDepartment() {
        contactPage.search(department);
        assertEquals(department, contactPage.getElementText(
                By.xpath(String.format("(//a[contains(text(), '%s')])[1]", department))));
        contactPage.clearSearch();
    }

    @Test
    @Order(5)
    @DisplayName("删除部门")
    void deleteDepartment() {
        contactPage.deleteDepartment(department);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/tags.csv", numLinesToSkip = 1)
    @Order(6)
    @DisplayName("添加标签,删除标签")
    void addTag(String tagName, String tagUser) {
        contactPage.addTag(tagName, tagUser);
        contactPage.deleteTag(tagName);
    }


    @AfterAll
    public static void tearDown() {
        mainPage.quit();
    }

}