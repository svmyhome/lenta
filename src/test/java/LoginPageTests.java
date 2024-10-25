import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;


public class LoginPageTests extends BaseTest {
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();

    @Test
    void LoginTest() {
        mainPage.openMainPage();
        loginPage.clickLoginButton()
                .shouldAuthorizationWindowOpen()
                .shouldCodeButtonDisable()
                .setPhoneNumber()
                .shouldCodeButtonEnable();

    }
}
