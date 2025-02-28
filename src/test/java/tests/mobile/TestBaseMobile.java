package tests.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import common.Attach;
import drivers.CreateMobileDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class TestBaseMobile {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = CreateMobileDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    public void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    public void afterEach() {
        Attach.screenshotAs("Финальный скриншот");
        Attach.pageSource();
        if (CreateMobileDriver.isBrowserStackDevice()) {
            Selenide.closeWebDriver();
            Attach.addVideoBrowserStack(Selenide.sessionId().toString());
        } else {
            Selenide.closeWebDriver();
        }
    }

}
