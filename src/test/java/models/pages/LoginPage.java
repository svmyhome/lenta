package models.pages;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginButton = $("[automation-id='login-button']"),
            authorizationWindow = $("[automation-id=dialog-auth]"),
            getCodeButton = $("input[value='Получить код']"),
            phoneInput = $("input[type=tel]");

    @Step("Кликнуть на кнопке Войти")
    public LoginPage clickOnLoginButton() {
        loginButton.click();
        return this;
    }

    @Step("Окно Войти или зарегистрироваться отображается")
    public LoginPage shouldAuthorizationWindowOpen() {
        authorizationWindow.shouldHave(text("Войти или зарегистрироваться"));
        return this;
    }

    @Step("Кнопка получить код неактивна")
    public LoginPage shouldCodeButtonDisable() {
        getCodeButton.shouldBe(disabled);
        return this;
    }

    @Step("Ввести номер телефона")
    public LoginPage setPhoneNumber() {
        phoneInput.setValue("9535006559");
        return this;
    }

    @Step("Кнопка получить код активна")
    public LoginPage shouldCodeButtonEnable() {
        getCodeButton.shouldNotBe(disabled);
        return this;
    }
}
