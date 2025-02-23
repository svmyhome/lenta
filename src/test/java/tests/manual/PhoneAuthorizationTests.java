package tests.manual;

import io.qameta.allure.Feature;
import qameta.allure.Layer;
import qameta.allure.Manual;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Layer("web")
@Feature("Авторизация")
@Story("Пользователь может авторизоваться через кнопку 'Войти'")
@Manual
@Owner("Sidorov")
@DisplayName("Авторизация через номер телефона")
public class PhoneAuthorizationTests {

    @Test
    @Manual
    @DisplayName("Успешная авторизация через СМС")
    public void successfulAuthorizeFromSmsTest() {
        step("Открыть главную страницу Lenta.com");
        step("Ввести авторизационные данные", () -> {
            step("Ввести телефон");
            step("Ввести пароль");
        });
        step("Кликнуть получить смс");
        step("Ввести смс в поле ввода");
        step("Переход в профиль пользователя выполнен");
    }

    @Test
    @Manual
    @DisplayName("Безуспешная авторизация через СМС, неверный смс код")
    public void unsuccessfulAuthorizeFromWrongSmsTest() {
        step("Открыть главную страницу Lenta.com");
        step("Ввести авторизационные данные", () -> {
            step("Ввести телефон");
            step("Ввести пароль");
        });
        step("Кликнуть получить смс");
        step("Ввести неверный смс в поле ввода");
        step("Вы ввели неверный смс, повторите ввод");
    }
}
