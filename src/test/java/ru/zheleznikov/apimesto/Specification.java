package ru.zheleznikov.apimesto;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.HashMap;

import static ru.zheleznikov.apimesto.BaseApiTests.apiHost;

public class Specification {

    public static RequestSpecification reqSpec(Object reqData) {
        return new RequestSpecBuilder()
                .setBaseUri(apiHost)
                .setContentType("application/json")
                .setBody(reqData)
                .build();
    }

    public static ResponseSpecification resSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }


}
