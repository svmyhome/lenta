package tests.api;

import common.helpers.ApiHelper;
import io.qameta.allure.Feature;
import io.qameta.allure.Layer;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import api.models.login.LoginOtpRequest;
import api.models.login.UnsupportedMediaTypeResponse;
import api.models.login.UserIsNotApprovedResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static api.models.ApiConstants.LOGINOTP;
import static api.models.ApiConstants.UNSUPPORTED_MEDIA_TYPE_ERROR;
import static api.models.ApiConstants.UNSUPPORTED_MEDIA_TYPE_MESSAGE;
import static api.models.ApiConstants.USER_IS_NOT_APPROVED_ERROR;
import static api.models.ApiConstants.USER_IS_NOT_APPROVED_MESSAGE;
import static api.specifications.ApiSpecifications.requestSpecification;
import static api.specifications.ApiSpecifications.statusCode403ResponseSpecification;
import static api.specifications.ApiSpecifications.statusCode415RequestSpecification;
import static api.specifications.ApiSpecifications.statusCode415ResponseSpecification;


@Layer("rest")
@Slf4j
@Feature("Авторизация")
@Story("Пользователь не может авторизоваться если в запросе некорректные данные")
@Owner("Sarychev")
@Tag("api")
@DisplayName("Ошибки авторизации")
public class LoginTests extends TestBase {
    final ApiHelper api = new ApiHelper();

    public final LoginOtpRequest loginOtpRequest = new LoginOtpRequest("79535006575");

    @Test
    @DisplayName("Запрашиваемый media-type не поддерживается")
    @Severity(SeverityLevel.MINOR)
    public void unsupportedMediaTypeloginOtpTest() {
        UnsupportedMediaTypeResponse unsupportedMediaTypeResponse = step("Получен код 415", () ->
                given(statusCode415RequestSpecification)
                        .when().body(loginOtpRequest)
                        .post(LOGINOTP)
                        .then()
                        .spec(statusCode415ResponseSpecification)
                        .extract().as(UnsupportedMediaTypeResponse.class));

        step("Нет аккаунта с таким номером телефона", () ->
        {
            api.assertValues(unsupportedMediaTypeResponse.message(), UNSUPPORTED_MEDIA_TYPE_MESSAGE)
                    .assertValues(unsupportedMediaTypeResponse.errorCode(), UNSUPPORTED_MEDIA_TYPE_ERROR);
        });
    }

    @Test
    @DisplayName("Ошибка 403: Нет аккаунта с указанным номером телефона")
    @Severity(SeverityLevel.TRIVIAL)
    public void forbiddenLoginOtpTest() {
        UserIsNotApprovedResponse userIsNotApprovedResponse = step("Получен код 403", () ->
                given(requestSpecification)
                        .when().body(loginOtpRequest)
                        .post(LOGINOTP)
                        .then()
                        .spec(statusCode403ResponseSpecification).extract().as(UserIsNotApprovedResponse.class));

        step("Нет аккаунта с таким номером телефона", () ->
        {
            api.assertValues(userIsNotApprovedResponse.message(), USER_IS_NOT_APPROVED_MESSAGE)
                    .assertValues(userIsNotApprovedResponse.errorCode(), USER_IS_NOT_APPROVED_ERROR);
        });
    }

}
