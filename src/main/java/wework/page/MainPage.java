package wework.page;


import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import sun.applet.Main;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainPage extends BasePage {
    public static String cookiePath;

    public MainPage() {
        super();
        initPage("https://work.weixin.qq.com/wework_admin/frame");
        cookiePath = "/tmp/cookies.txt";
//        cookiePath = Objects.requireNonNull(
//                this.getClass().getClassLoader().getResource("cookies.txt")).getPath();
    }

    public void weWorkSetCookies(String cookiePath) {
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
                setCookie(cookie);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void weWorkGetCookies(String cookiePath) {
        Set<Cookie> cookies = getCookies();
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

    public ContactPage toContact() {
        clickBy(By.cssSelector("#menu_contacts .frame_nav_item_title"));
        return new ContactPage();
    }
}
