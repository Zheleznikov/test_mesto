package ru.zheleznikov.mesto.legacy;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static ru.zheleznikov.mesto.legacy.BaseApiTests.apiHost;

public class Specification {

    public static RequestSpecification reqSpec(Object reqData) {
        return new RequestSpecBuilder()
                .setBaseUri(apiHost)
                .setContentType(ContentType.JSON)
                .setBody(reqData)
                .build();
    }

    public static ResponseSpecification resSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }


}
