package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegPage {
    WebDriver driver;
    // URL страницы регистрации сайта Stellar Burgers
    public final String url = "https://stellarburgers.nomoreparties.site/register";
    // локатор заголовка страницы "Регистрация"
    public final By header = By.xpath(".//h2[text()='Регистрация']");
    // локатор поля ввода "Имя"
    public final By name = By.xpath(".//fieldset[1]//input[@name='name']");
    // локатор поля ввода "Email"
    public final By email = By.xpath(".//fieldset[2]//input[@name='name']");
    // локатор поля ввода "Пароль"
    public final By password = By.xpath(".//input[@name='Пароль']");
    // локатор кнопки "Зарегистрироваться"
    public final By buttonReg = By.xpath(".//button[text()='Зарегистрироваться']");
    // локатор сообщения об ошибке пароля
    public final By errorPassword = By.xpath(".//p[text()='Некорректный пароль']");
    // локатор ссылки "Войти" внизу страницы
    public final By linkEnter = By.xpath(".//a[text()='Войти']");

    public RegPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void waitHeader() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }

    public void sendKeysName(String strName) {
        WebElement element = driver.findElement(name);
        element.sendKeys(strName);
    }

    public void sendKeysEmail(String strEmail) {
        WebElement element = driver.findElement(email);
        element.sendKeys(strEmail);
    }

    public void sendKeysPassword(String strPassword) {
        WebElement element = driver.findElement(password);
        element.sendKeys(strPassword);
    }

    public void clickButtonReg() {
        WebElement element = driver.findElement(buttonReg);
        element.click();
    }

    public void waitErrorMessage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(errorPassword));
    }

    public String getErrorMessage() {
        WebElement element = driver.findElement(errorPassword);
        return element.getText();
    }

    public void clickLinkEnter() {
        WebElement element = driver.findElement(linkEnter);
        element.click();
    }
}
