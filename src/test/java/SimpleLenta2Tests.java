import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class SimpleLenta2Tests {

    @Test
    void simpleTest() {

        step("ОТкрыть браузер", () -> {
            open("https://lenta.com//");
        });
        step("ОТкрыта главная страница", () -> {
                    $("[luautotestlocator='login-button']").shouldHave(text("Войти"));
                });
        System.out.println();
    }

}
