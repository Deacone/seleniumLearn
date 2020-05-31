package wework.page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public static ChromeDriver driver;
    public static WebDriverWait wait;

    public BasePage() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, 10);
//        driver = new ChromeDriver();
    }

    public void initPage(String url) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
    }

    public void clickBy(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    public void sendKeys(By by, String content) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(content);
    }

    public void clearKeys(By by) {
        driver.findElement(by).clear();
    }

    public Set<Cookie> getCookies() {
        return driver.manage().getCookies();
    }

    public void setCookie(Cookie cookie) {
        driver.manage().addCookie(cookie);
    }

    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    public void quit() {
        driver.quit();
    }

    public String getElementText(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        return driver.findElement(by).getText();
    }

    public void alertAccept() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
    }

    public void selectorList(By by) {
        wait.until(ExpectedConditions.elementToBeSelected(by));
        driver.findElement(by).isSelected();
    }

}
