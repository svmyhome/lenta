package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiSteps {
    @Step("Полученное значение совпадает c {expected}")
    public ApiSteps assertValues(String expected, String actual) {
        assertThat(expected).isEqualTo(actual);
        return this;
    }

    @Step("Количество равно {expected}")
    public ApiSteps assertListSize(Response response, String jsonPath, int expected) {
        assertThat(response.jsonPath().getList(jsonPath).size()).isGreaterThanOrEqualTo(expected);
        return this;
    }
}
