package remote;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;


@Feature("Авторизация")
@Story("Авторизоваться через кнопку")
@Owner("sarychev")
@Tags({@Tag("AUTHORIZATION"), @Tag("SMOKE")})
public class LoginTests extends TestBaseRemote {


    @Test
    void LoginTest() {
        step("Открыта главная страница", () -> {
            open("/");
        });

        step("Кликнуть на кноке Войти", () -> {
            $("[automation-id='login-button']").click();
        });
        step("Окно Войти или зарегистрироваться появилось", () -> {
            $("[automation-id=dialog-auth]").shouldHave(text("Войти или зарегистрироваться"));
        });
        step("Кнопка получить код неактивна", () -> {
            $("input[value='Получить код']").shouldBe(disabled);
        });
        step("Введен номер телефона", () -> {
            $("input[type=tel]").setValue("9535006559");
        });
        step("Кнопка получить код активна", () -> {
            $("input[value='Получить код']").shouldNotBe(disabled);
        });
    }
}
