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
public class TestsTransferFromPersonalAccountToConstructor {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private final String browser;

    public TestsTransferFromPersonalAccountToConstructor(String browser) {
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
        loginPage = new LoginPage(driver);
        driver.get(loginPage.getUrl());
        driver.manage().window().maximize();
        loginPage.waitHeader();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на ссылку «Конструктор»")
    @Description("Проверка перехода по указанной ссылке на главную страницу с конструктором")
    public void transferOnLinkConstructorIsTrue() {
        mainPage = new MainPage(driver);
        loginPage.clickLinkConstructor();
        mainPage.waitHeader();
        assertEquals(mainPage.getUrl()+"/", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип")
    @Description("Проверка перехода по клику на логотип на главную страницу с конструктором")
    public void transferOnLogoIsTrue() {
        mainPage = new MainPage(driver);
        loginPage.clickLogo();
        mainPage.waitHeader();
        assertEquals(mainPage.getUrl()+"/", driver.getCurrentUrl());
    }
}
