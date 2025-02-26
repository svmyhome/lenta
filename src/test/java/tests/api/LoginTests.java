package tests.api;

import api.ApiEnpoints;
import api.ApiSteps;
import api.models.login.LoginOtpRequest;
import api.models.login.UnsupportedMediaTypeResponse;
import api.models.login.UserIsNotApprovedResponse;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qameta.allure.Layer;

import static api.specifications.ApiSpecifications.requestSpecification;
import static api.specifications.ApiSpecifications.statusCode403ResponseSpecification;
import static api.specifications.ApiSpecifications.statusCode415RequestSpecification;
import static api.specifications.ApiSpecifications.statusCode415ResponseSpecification;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;


@Layer("rest")
@Slf4j
@Feature("Авторизация")
@Story("Пользователь не может авторизоваться если в запросе некорректные данные")
@Owner("Sarychev")
@Tag("api")
@DisplayName("Ошибки авторизации")
public class LoginTests extends TestBaseApi {
    private static final String USER_IS_NOT_APPROVED_MESSAGE = "Нет аккаунта с таким номером телефона. Проверьте цифры, пожалуйста, или зарегистрируйтесь.";
    private static final String USER_IS_NOT_APPROVED_ERROR = "DbUserIsNotApproved";
    private static final String UNSUPPORTED_MEDIA_TYPE_MESSAGE = "Запрашиваемый media-type не поддерживается.";
    private static final String UNSUPPORTED_MEDIA_TYPE_ERROR = "UnsupportedMediaType";
    final ApiSteps api = new ApiSteps();

    public final LoginOtpRequest loginOtpRequest = new LoginOtpRequest("79535006575");

    @Test
    @DisplayName("Запрашиваемый media-type не поддерживается")
    @Severity(SeverityLevel.MINOR)
    public void unsupportedMediaTypeloginOtpTest() {
        UnsupportedMediaTypeResponse unsupportedMediaTypeResponse = step("Получен код 415", () ->
                given(statusCode415RequestSpecification)
                        .when().body(loginOtpRequest)
                        .post(ApiEnpoints.LOGINOTP)
                        .then()
                        .spec(statusCode415ResponseSpecification)
                        .extract().as(UnsupportedMediaTypeResponse.class));

        step("Нет аккаунта с таким номером телефона", () ->
        {
            api.assertValues(UNSUPPORTED_MEDIA_TYPE_MESSAGE, unsupportedMediaTypeResponse.message())
                    .assertValues(UNSUPPORTED_MEDIA_TYPE_ERROR, unsupportedMediaTypeResponse.errorCode());
        });
    }

    @Test
    @DisplayName("Ошибка 403: Нет аккаунта с указанным номером телефона")
    @Severity(SeverityLevel.TRIVIAL)
    public void forbiddenLoginOtpTest() {
        UserIsNotApprovedResponse userIsNotApprovedResponse = step("Получен код 403", () ->
                given(requestSpecification)
                        .when().body(loginOtpRequest)
                        .post(ApiEnpoints.LOGINOTP)
                        .then()
                        .spec(statusCode403ResponseSpecification).extract().as(UserIsNotApprovedResponse.class));

        step("Нет аккаунта с таким номером телефона", () ->
        {
            api.assertValues(USER_IS_NOT_APPROVED_MESSAGE, userIsNotApprovedResponse.message())
                    .assertValues(USER_IS_NOT_APPROVED_ERROR, userIsNotApprovedResponse.errorCode());
        });
    }

}
