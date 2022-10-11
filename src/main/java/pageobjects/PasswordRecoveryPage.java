package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasswordRecoveryPage {
    WebDriver driver;
    // URL страницы восстановления пароля сайта Stellar Burgers
    public final String url = "https://stellarburgers.nomoreparties.site/forgot-password";
    // локатор заголовка страницы "Восстановление пароля"
    public final By header = By.xpath(".//h2[text()='Восстановление пароля']");
    // локатор ссылки "Войти" внизу страницы
    public final By linkEnter = By.xpath(".//a[text()='Войти']");

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void waitHeader() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }

    public void clickLinkEnter() {
        WebElement element = driver.findElement(linkEnter);
        element.click();
    }
}
