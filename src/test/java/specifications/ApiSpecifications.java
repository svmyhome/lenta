package specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class ApiSpecifications {
    static final Map<String, String> headers = Map.of(
            "Content-Type", "application/json",
            "DeviceID", "ff86f8dd-7154-f591-199c-19fecb138aac",
            "SessionToken", "3849FACA09F05B077ADF56894288E40A1",
            "X-Domain", "moscow",
            "X-Organization-ID", "",
            "X-Platform", "omniweb",
            "X-Retail-Brand", "lo"
    );

    public final static RequestSpecification requestSpecification = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .log().body()
            .headers(headers);


    public final static ResponseSpecification statusCode200ResponseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public final static ResponseSpecification statusCode403ResponseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(403)
            .log(STATUS)
            .log(BODY)
            .build();

    public final static RequestSpecification statusCode415RequestSpecification = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .log().body()
            .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36");

    public final static ResponseSpecification statusCode415ResponseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(415)
            .log(STATUS)
            .log(BODY)
            .build();
}
