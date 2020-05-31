package wework.testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import wework.page.MainPage;

class GetCookiesTest {
    private static MainPage mainPage;

    @BeforeAll
    public static void setUp() {
        mainPage = new MainPage();
    }

    @Test
    void testGetCookies() {
        mainPage.weWorkGetCookies(MainPage.cookiePath);
    }

    @AfterAll
    public static void tearDown() {
        mainPage.quit();
    }

}