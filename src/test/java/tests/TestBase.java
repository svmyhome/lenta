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
import org.junit.jupiter.api.TestInfo;

import static com.codeborne.selenide.Selenide.open;
import static drivers.CreateWebDriver.webDriverConfig;
import static helpers.ProjectSettings.*;

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
        if(isMobile) {
            open();
        }

    }

    @AfterEach
    public void afterEach(TestInfo testInfo) {
        if (!testInfo.getTags().contains("api")) {
            Attach.screenshotAs("Финальный скриншот");
            Attach.pageSource();
            if (isWeb) {
                Attach.browserConsoleLogs();
                if (isRemoteStartWeb) {
                    Attach.addVideo();
                }
            }
            Selenide.closeWebDriver();
        }
        if (isBrowserStackDevice) {
            Selenide.closeWebDriver();
            Attach.addVideoSelenoid(Selenide.sessionId().toString());
        }
    }
}
