package tests.api;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static drivers.CreateWebDriver.webDriverConfig;

public class TestBaseApi {

    @BeforeAll
    static void beforeAll() {
        webDriverConfig();
    }

    @BeforeEach
    public void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

//    @AfterEach
//    public void afterEach(TestInfo testInfo) {
//        if (!testInfo.getTags().contains("api")) {
//            Attach.screenshotAs("Финальный скриншот");
//            Attach.pageSource();
//            if (isWeb) {
//                Attach.browserConsoleLogs();
//                if (isRemoteStartWeb) {
//                    Attach.addVideo();
//                }
//            }
//            Selenide.closeWebDriver();
//        }
//        if (isBrowserStackDevice) {
//            Selenide.closeWebDriver();
//            Attach.addVideoSelenoid(Selenide.sessionId().toString());
//        }
//    }
}
