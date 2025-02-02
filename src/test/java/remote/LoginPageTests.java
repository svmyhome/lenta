package remote;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import models.pages.LoginPage;
import models.pages.MainPage;

@Tag("ui")
@Tags({@Tag("AUTHORIZATION"), @Tag("SMOKE")})
@Feature("Авторизация")
@Story("Авторизоваться через кнопку")
@Owner("sarychev")
public class LoginPageTests extends TestBaseRemote {

    MainPage mainPage = new MainPage();;
    LoginPage loginPage = new LoginPage();;

    @Test
    @DisplayName("Выполнен ввод номера телефона кнопка получить код активна")
    @Severity(SeverityLevel.BLOCKER)
    void LoginTest() {
        mainPage.openMainPage();
        loginPage.clickLoginButton()
                .shouldAuthorizationWindowOpen()
                .shouldCodeButtonDisable()
                .setPhoneNumber()
                .shouldCodeButtonEnable();

    }
}
