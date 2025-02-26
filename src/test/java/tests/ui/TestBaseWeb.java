package tests.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import common.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import static common.ProjectSettings.isRemoteStartWeb;
import static drivers.CreateWebDriver.webDriverConfig;

public class TestBaseWeb {

    @BeforeAll
    static void beforeAll() {
        webDriverConfig();
    }

    @BeforeEach
    public void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    public void afterEach(TestInfo testInfo) {
        Attach.screenshotAs("Финальный скриншот");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        if (isRemoteStartWeb) {
            Attach.addVideo();
        }
        Selenide.closeWebDriver();
    }
}
