package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.CreateMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static drivers.CreateWebDriver.webDriverConfig;
import static helpers.ProjectSettings.isRemoteStartWeb;
import static helpers.ProjectSettings.isWeb;

public class TestBase {

    @BeforeAll
    static void beforeAll() {

        if (isWeb) {
            webDriverConfig();
        } else {
            Configuration.browser = CreateMobileDriver.class.getName();
            Configuration.browserSize = null;
            Configuration.timeout = 30000;
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
