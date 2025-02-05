package tests.ui;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import models.pages.LoginPage;
import models.pages.MainPage;
import tests.TestBase;

@Feature("Авторизация")
@Story("Пользователь может авторизоваться через кнопку 'Войти'")
@Owner("sarychev")
@Tags({@Tag("AUTHORIZATION"), @Tag("SMOKE")})
@Tag("ui")
@DisplayName("Авторизация через номер телефона")
public class LoginPageTests extends TestBase {

    MainPage mainPage = new MainPage();;
    LoginPage loginPage = new LoginPage();;

    @Test
    @DisplayName("Кнопка 'Получить код' не активна до ввода номера телефона")
    @Severity(SeverityLevel.BLOCKER)
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
    @DisplayName("Окно 'Введите код' открыто")
    @Severity(SeverityLevel.BLOCKER)
    void enterCodeWindowOpenTest() {
        mainPage.openMainPage()
                .closeToolTip();
        loginPage.clickOnLoginButton()
                .setPhoneNumber()
                .shouldCodeButtonEnable();
        //TODO
    }


}
