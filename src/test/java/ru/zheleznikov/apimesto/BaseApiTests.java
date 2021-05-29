package ru.zheleznikov.apimesto;

import java.util.HashMap;
import java.util.Map;

public class BaseApiTests {
    // apiHost
    protected final static String apiHost = "https://api-mesto.zheleznikov.ru/";
    protected final static String signin = "signin";

    // signin Data
    protected final static Map<String, String> correctRequestData = new HashMap<>();
    protected final static Map<String, String> incorrectDataEmail = new HashMap<>();
    protected final static Map<String, String> incorrectDataPass = new HashMap<>();

    protected final static String emailKey = "email";
    protected final static String passKey = "password";

    protected final static String correctEmail = "cat@cat.cat";
    protected final static String correctPass = "1234qwerty";
    protected final static String incorrectPass = "1234qwert";
    protected final static String emptyField = "";
    protected final static String incorrectEmail = "mail@";


    static {
        correctRequestData.put(emailKey, correctEmail);
        correctRequestData.put(passKey, correctPass);
    }

}
