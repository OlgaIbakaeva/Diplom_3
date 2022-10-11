package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    // URL страницы регистрации сайта Stellar Burgers
    public final String url = "https://stellarburgers.nomoreparties.site/login";
    // локатор заголовка страницы "Вход"
    public final By header = By.xpath(".//h2[text()='Вход']");

    public final By linkConstructor = By.xpath(".//p[text()='Конструктор']");
    // локатор логотипа Stellar Burgers
    public final By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");
    // локатор кнопки "Выход" в профайле
    public final By buttonExit = By.xpath(".//button[text()='Выход']");
    // локатор поля ввода "Email"
    public final By email = By.xpath(".//input[@name='name']");
    // локатор поля ввода "Пароль"
    public final By password = By.xpath(".//input[@name='Пароль']");
    // локатор ссылки "Войти" внизу страницы
    public final By buttonEnter = By.xpath(".//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void waitHeader() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }

    public void clickLinkConstructor() {
        WebElement element = driver.findElement(linkConstructor);
        element.click();
    }

    public void clickLogo() {
        WebElement element = driver.findElement(logo);
        element.click();
    }

    public void clickButtonExit() {
        WebElement element = driver.findElement(buttonExit);
        element.click();
    }

    public void sendKeysEmail(String strEmail) {
        WebElement element = driver.findElement(email);
        element.sendKeys(strEmail);
    }

    public void sendKeysPassword(String strPassword) {
        WebElement element = driver.findElement(password);
        element.sendKeys(strPassword);
    }

    public void waitButtonExit() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(buttonExit));
    }
    public void clickButtonEnter() {
        WebElement element = driver.findElement(buttonEnter);
        element.click();
    }
}
