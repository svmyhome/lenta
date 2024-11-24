import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.OutputType;
import pages.CatalogPanel;
import pages.LoginPage;
import pages.MainPage;
import pages.SearchPage;

import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.Selenide.screenshot;
import static com.codeborne.selenide.Selenide.webdriver;
import static io.qameta.allure.Allure.addAttachment;
import static io.qameta.allure.Allure.attachment;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1200";
        Configuration.baseUrl = "https://lenta.com/";
        SelenideLogger.addListener("allure", new AllureSelenide());

//        Configuration.pageLoadStrategy = "eager";
//        Configuration.holdBrowserOpen = true;
    }
    @AfterEach
    void afterEach() {
        attachment("Source", webdriver().driver().source());
        addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshot(OutputType.BYTES)), "png");
        addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
        addAttachment("Website Link", "https://github.com"); // добавляем прикрепление ссылки на сайт
        System.out.println("Close WEBDRIVER !!!");
        Selenide.closeWebDriver();
    }
}
