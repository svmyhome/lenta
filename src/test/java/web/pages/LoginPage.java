package web.pages;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement loginButton = $("[automation-id='login-button']"),
            authorizationWindow = $("[automation-id=dialog-auth]"),
            getCodeButton = $(".lui-button"),
            phoneInput = $("input[type=tel]"),
            smsInput = $(".loginbox__text");

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

    @Step("Кликнуть получить код")
    public LoginPage clickGetCode() {
        getCodeButton.click();
        return this;
    }

    @Step("Отображается окно ввода смс")
    public LoginPage shouldSendGetCode(String value) {
        smsInput.shouldHave(text(value));
        return this;
    }

}
