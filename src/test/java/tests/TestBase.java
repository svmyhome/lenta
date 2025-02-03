package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.Selenide.screenshot;
import static com.codeborne.selenide.Selenide.webdriver;
import static config.web.WebDriverConfig.localWebDriverConfig;
import static config.web.WebDriverConfig.remoteWebDriverConfig;
import static helpers.ProjectSettings.isRemoteStartWeb;
import static io.qameta.allure.Allure.addAttachment;
import static io.qameta.allure.Allure.attachment;

public class TestBase {

    @BeforeAll
    static void beforeAll() {

        if (isRemoteStartWeb) {
            remoteWebDriverConfig();
        } else {
            localWebDriverConfig();
        }

    }

    @BeforeEach
    public void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Финальный скриншот");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        if (isRemoteStartWeb) {
            Attach.addVideo();
        }
        Selenide.closeWebDriver();
    }
}
