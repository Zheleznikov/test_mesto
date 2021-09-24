package ru.zheleznikov.mesto.main.app.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import ru.zheleznikov.mesto.main.app.HelperBase;
import ru.zheleznikov.mesto.main.modelhelpers.CardHelper;

import java.io.IOException;

public class ApiBase extends HelperBase {
    public CardHelper handleRs;

    protected ApiBase() throws IOException {
    }

    RequestSpecification reqSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(properties.getProperty("host.api"))
                .setContentType(ContentType.JSON).build();
    }
}
