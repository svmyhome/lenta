package tests.ui;

import io.qameta.allure.*;
import models.pages.LoginPage;
import models.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static models.pages.LoginPage.SEND_SMS;

@Feature("Авторизация")
@Story("Пользователь может авторизоваться через кнопку 'Войти'")
@Owner("sarychev")
@Tags({@Tag("auth"), @Tag("smoke")})
@Tag("ui")
@DisplayName("Авторизация через номер телефона")
public class LoginPageTests extends TestBase {

    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("Кнопка 'Получить код' не активна до ввода номера телефона")
    @Severity(SeverityLevel.CRITICAL)
    void codeButtonDisabledBeforeTypePhoneTest() {
        mainPage.openMainPage()
                .closeToolTip();
        loginPage.clickOnLoginButton()
                .shouldAuthorizationWindowOpen()
                .shouldCodeButtonDisable();
    }

    @Test
    @DisplayName("Кнопка 'Получить код' активна после ввода номера телефона")
    @Severity(SeverityLevel.BLOCKER)
    void codeButtonEnabledAfterTypePhoneTest() {
        mainPage.openMainPage()
                .closeToolTip();
        loginPage.clickOnLoginButton()
                .setPhoneNumber()
                .shouldCodeButtonEnable();
    }

    @Test
    @DisplayName("Открыто окно 'Введите код'")
    @Severity(SeverityLevel.BLOCKER)
    void enterCodeWindowOpenTest() {
        mainPage.openMainPage()
                .closeToolTip();
        loginPage.clickOnLoginButton()
                .setPhoneNumber()
                .shouldCodeButtonEnable()
                .clickGetCode()
                .shouldSendGetCode(SEND_SMS);
    }

}
