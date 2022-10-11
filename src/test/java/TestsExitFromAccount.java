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
import org.apache.commons.lang3.RandomStringUtils;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestsExitFromAccount {
    private WebDriver driver;
    private RegPage regPage;
    private final String browser;

    public TestsExitFromAccount(String browser) {
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
        regPage = new RegPage(driver);
        driver.get(regPage.getUrl());
        driver.manage().window().maximize();
        regPage.waitHeader();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверка выхода по кнопке «Выход» в личном кабинете")
    public void exitFromAccountIsTrue() {
        // регистрация рандомного пользователя и переход на страницу логина
        LoginPage loginPage = new LoginPage(driver);
        String name = RandomStringUtils.randomAlphabetic(10);
        String email = RandomStringUtils.randomAlphanumeric(10)+"@mail.ru";
        String password = RandomStringUtils.randomAlphanumeric(6);
        regPage.sendKeysName(name);
        regPage.sendKeysEmail(email);
        regPage.sendKeysPassword(password);
        regPage.clickButtonReg();
        loginPage.waitHeader();
        // авторизация пользователя  и переход на главную страницу
        MainPage mainPage = new MainPage(driver);
        loginPage.sendKeysEmail(email);
        loginPage.sendKeysPassword(password);
        loginPage.clickButtonEnter();
        // переход в личный кабинет
        mainPage.clickLinkPersonalAccount();
        // переход в профайл, клик на проверяемой кнопке "Выход"
        loginPage.waitButtonExit();
        loginPage.clickButtonExit();
        // проверка возврата на страницу логина
        loginPage.waitHeader();
        assertEquals(loginPage.getUrl(), driver.getCurrentUrl());
    }
}
