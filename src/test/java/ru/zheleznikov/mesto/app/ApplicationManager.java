package ru.zheleznikov.mesto.app;

import java.io.IOException;

public class ApplicationManager {
    private ApiHelper apiHelper;
    private MongoHelper mongoHelper;


    public void init() throws IOException {
        apiHelper = new ApiHelper();
        mongoHelper = new MongoHelper();
    }

    public ApiHelper api() {return this.apiHelper;}

    public MongoHelper db() {return this.mongoHelper;}



}
