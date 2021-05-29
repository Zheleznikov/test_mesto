package ru.zheleznikov.apimesto;

import java.util.HashMap;
import java.util.Map;

public class BaseApiTests {
    protected final static String apiHost = "https://api-mesto.zheleznikov.ru/";
    protected final static String signin = "/signin";

    protected static Map<String, String> correctRequestData = new HashMap<>();

    static {
        correctRequestData.put("email", "polwen@yandex.ru");
        correctRequestData.put("password", "1234qwerty");

    }

}
