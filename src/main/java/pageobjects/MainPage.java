package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    WebDriver driver;
    // URL главной страницы сайта Stellar Burgers
    public final String url = "https://stellarburgers.nomoreparties.site";
    // локатор заголовка страницы "Соберите бургер"
    public final By header = By.xpath(".//h1");
    // локатор кнопки "Войти в аккаунт"
    public final By buttonLogInAccount = By.xpath(".//button[text()='Войти в аккаунт']");
    // локатор ссылки "Личный кабинет"
    public final By linkPersonalAccount = By.xpath(".//p[text()='Личный Кабинет']");
    // локатор активного раздела
    public final By activeLink = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span");
    // локатор ссылки "Булки"
    public final By buns = By.xpath(".//main/section[1]/div[1]/div[1]");
    // локатор ссылки "Соусы"
    public final By sauces = By.xpath(".//main/section[1]/div[1]/div[2]");
    // локатор ссылки "Начинки"
    public final By fillings = By.xpath(".//main/section[1]/div[1]/div[3]");
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void waitHeader() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }

    public void clickButtonLogInAccount() {
        WebElement element = driver.findElement(buttonLogInAccount);
        element.click();
    }

    public void clickLinkPersonalAccount() {
        WebElement element = driver.findElement(linkPersonalAccount);
        element.click();
    }

    public String getTextActiveLink() {
        WebElement element = driver.findElement(activeLink);
        return element.getText();
    }

    public void clickBuns() {
        WebElement element = driver.findElement(buns);
    }

    public void clickSauces() {
        WebElement element = driver.findElement(sauces);
        element.click();
    }

    public void clickFillings() {
        WebElement element = driver.findElement(fillings);
        element.click();
    }
}
