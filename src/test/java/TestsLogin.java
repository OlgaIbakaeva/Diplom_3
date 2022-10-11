import config.Config;
import pageobjects.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestsLogin {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private final String browser;

    public TestsLogin(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getBrowser() {
        return new Object[][] {
                {"Google Chrome"},
                {"Yandex Browser"},
        };
    }

    @Before
    public void setUp() {
        switch (browser) {
            case "Google Chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "Yandex Browser":
                WebDriverManager.chromedriver().driverVersion("104.0.5112.20").setup();
                driver = new ChromeDriver(new ChromeOptions().setBinary(new Config().getYandexBinaryPath()));
                break;
            default:
                System.out.println("Для такого браузера тестирование не предусмотрено");
        }
        mainPage = new MainPage(driver);
        driver.get(mainPage.getUrl());
        driver.manage().window().maximize();
        mainPage.waitHeader();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Вход: проверка входа по кнопке «Войти в аккаунт» на главной странице")
    @Description("Переходим по указанной кнопке на страницу логина с заголовком 'Вход'")
    public void buttonLogInAccountOnMainPageIsTrue() {
        loginPage = new LoginPage(driver);
        mainPage.clickButtonLogInAccount();
        loginPage.waitHeader();
        assertEquals(loginPage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход: проверка входа через ссылку «Личный кабинет» на главной странице")
    @Description("Переходим по указанной ссылке на страницу логина с заголовком 'Вход'")
    public void linkPersonalAccountOnMainPageIsTrue() {
        loginPage = new LoginPage(driver);
        mainPage.clickLinkPersonalAccount();
        loginPage.waitHeader();
        assertEquals(loginPage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход: проверка входа через кнопку 'Войти' в форме регистрации")
    @Description("Переходим по указанной ссылке на страницу логина с заголовком 'Вход'")
    public void linkEnterOnRegPageIsTrue() {
        loginPage = new LoginPage(driver);
        RegPage regPage = new RegPage(driver);
        driver.get(regPage.getUrl());
        regPage.waitHeader();
        regPage.clickLinkEnter();
        loginPage.waitHeader();
        assertEquals(loginPage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход: проверка входа через кнопку 'Войти' в форме восстановления пароля")
    @Description("Переходим по указанной ссылке на страницу логина с заголовком 'Вход'")

    public void linkEnterOnPasswordRecoveryPageIsTrue() {
        loginPage = new LoginPage(driver);
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        driver.get(passwordRecoveryPage.getUrl());
        passwordRecoveryPage.waitHeader();
        passwordRecoveryPage.clickLinkEnter();
        loginPage.waitHeader();
        assertEquals(loginPage.getUrl(), driver.getCurrentUrl());
    }
}
