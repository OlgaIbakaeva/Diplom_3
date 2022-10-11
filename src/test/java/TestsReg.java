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
public class TestsReg {
    private WebDriver driver;
    private RegPage regPage;
    private String name;
    private String email;
    private String password;
    private final String browser;

    public TestsReg(String browser) {
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
    @DisplayName("Регистрация: проверка успешной регистрации")
    @Description("Создаем рандомно пользователя с валидными данными. Проверяем регистрацию путем перехода на страницу 'Вход'")
    public void successfulRegWhenCorrectUserData() {
        LoginPage loginPage = new LoginPage(driver);
        name = RandomStringUtils.randomAlphabetic(10);
        email = RandomStringUtils.randomAlphanumeric(10)+"@mail.ru";
        password = RandomStringUtils.randomAlphanumeric(6);
        regPage.sendKeysName(name);
        regPage.sendKeysEmail(email);
        regPage.sendKeysPassword(password);
        regPage.clickButtonReg();
        loginPage.waitHeader();
        assertEquals(loginPage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Регистрация: проверка ошибки для некорректного пароля")
    @Description("Создаем рандомно пользователя с невалидным паролем. Проверяем сообщение об ошибке")
    public void isNotRegWhenInvalidUserPassword() {
        name = RandomStringUtils.randomAlphabetic(10);
        email = RandomStringUtils.randomAlphanumeric(10)+"@mail.ru";
        password = RandomStringUtils.randomAlphanumeric(1);
        regPage.sendKeysName(name);
        regPage.sendKeysEmail(email);
        regPage.sendKeysPassword(password);
        regPage.clickButtonReg();
        regPage.waitErrorMessage();
        assertEquals("Некорректный пароль", regPage.getErrorMessage());
    }
}
