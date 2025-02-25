package tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qameta.allure.Layer;
import tests.TestBase;
import web.pages.LoginPage;
import web.pages.MainPage;

@Layer("web")
@Feature("Авторизация")
@Story("Пользователь может авторизоваться через кнопку 'Войти'")
@Owner("Ivanov")
@Tag("ui")
@DisplayName("Авторизация через номер телефона")
public class AuthorizationPageTests extends TestBase {

    private static final String SEND_SMS = "Мы отправили код на номер";
    private static final String phoneNumber = "9535006559";
    final MainPage mainPage = new MainPage();
    final LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("Кнопка 'Получить код' не активна до ввода номера телефона")
    @Severity(SeverityLevel.CRITICAL)
    void codeButtonDisabledBeforeTypePhoneTest() {
        mainPage.openMainPage()
                .closeStoreSelectionTooltip();
        loginPage.clickOnLoginButton()
                .shouldAuthorizationWindowOpen()
                .shouldCodeButtonDisable();
    }

    @Test
    @DisplayName("Кнопка 'Получить код' активна после ввода номера телефона")
    @Severity(SeverityLevel.BLOCKER)
    void codeButtonEnabledAfterTypePhoneTest() {
        mainPage.openMainPage()
                .closeStoreSelectionTooltip();
        loginPage.clickOnLoginButton()
                .setPhoneNumber(phoneNumber)
                .shouldCodeButtonEnable();
    }

    @Test
    @DisplayName("Открыто окно 'Введите код'")
    @Severity(SeverityLevel.BLOCKER)
    void enterCodeWindowOpenTest() {
        mainPage.openMainPage()
                .closeStoreSelectionTooltip();
        loginPage.clickOnLoginButton()
                .setPhoneNumber(phoneNumber)
                .shouldCodeButtonEnable()
                .clickGetCode()
                .shouldDisplaySmsInputWindow(SEND_SMS);
    }

}
