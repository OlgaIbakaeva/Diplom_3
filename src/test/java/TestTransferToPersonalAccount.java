import config.Config;
import pageobjects.MainPage;
import pageobjects.LoginPage;
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
public class TestTransferToPersonalAccount {
    private WebDriver driver;
    private MainPage mainPage;
    private final String browser;

    public TestTransferToPersonalAccount(String browser) {
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
    @DisplayName("Переход в личный кабинет")
    @Description("Проверка перехода в 'Личный кабинет' с главной страницы")
    public void transferPersonalAccountIsTrue() {
        LoginPage loginPage = new LoginPage(driver);
        mainPage.clickLinkPersonalAccount();
        loginPage.waitHeader();
        assertEquals(loginPage.getUrl(), driver.getCurrentUrl());
    }
}
