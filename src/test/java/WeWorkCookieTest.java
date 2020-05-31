import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WeWorkCookieTest {
    private static WebDriver driver;
    private static String cookiePath;

    @BeforeAll
    public static void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        driver = new ChromeDriver(chromeOptions);
//        driver = new ChromeDriver();
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cookiePath = Objects.requireNonNull(
                WeWorkCookieTest.class.getClassLoader().getResource("cookies.txt")).getPath();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    void weWorkGetCookies() {
        Set<Cookie> cookies = driver.manage().getCookies();
        try {
            FileWriter fileWriter = new FileWriter(cookiePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Cookie cookie : cookies) {
                String writerContent = cookie.getName() + ";" +
                        cookie.getValue() + ";" +
                        cookie.getDomain() + ";" +
                        cookie.getPath() + ";" +
                        cookie.getExpiry() + ";" +
                        cookie.isSecure();
                bufferedWriter.write(writerContent);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void weWorkSetCookies() {
        try {
            FileReader fileReader = new FileReader(cookiePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String lineContent;
            while (null != (lineContent = bufferedReader.readLine())) {
                StringTokenizer stringTokenizer = new StringTokenizer(lineContent, ";");
                String name = stringTokenizer.nextToken();
                String value = stringTokenizer.nextToken();
                String domain = stringTokenizer.nextToken();
                String path = stringTokenizer.nextToken();

                // expiry 的解析
                String expiryStr = stringTokenizer.nextToken();
                Date expiry = null;
                if (!expiryStr.equals("null")) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    expiry = simpleDateFormat.parse(expiryStr);
                }

                boolean isSecure = Boolean.parseBoolean(stringTokenizer.nextToken());
                Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure);
                driver.manage().addCookie(cookie);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void toContact() {
        driver.findElement(By.cssSelector("#menu_contacts span")).click();
        driver.findElement(By.cssSelector("#menu_index span")).click();
    }

    @Test
    void DateParse() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

        Date date1 = simpleDateFormat.parse("2019-05-04 20:09:00 CST");
        Date date2 = simpleDateFormat2.parse("Fri Aug 28 18:08:30 CST 2015");

        System.out.println(date1 + "\n" + date2);

    }
}
