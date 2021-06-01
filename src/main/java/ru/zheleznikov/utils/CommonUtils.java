package ru.zheleznikov.utils;

import org.json.JSONObject;
import ru.zheleznikov.jsonClasses.Signin;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CommonUtils {

    private static JSONObject getJson(String path) throws IOException {
        Scanner sc = new Scanner(new File(path));
        String jsonFile = "";
        while (sc.hasNextLine()) {
            jsonFile += sc.nextLine();
        }
        return new JSONObject(jsonFile);
    }

    public static Signin getSigninJson() throws IOException {
        JSONObject obj = getJson("src/main/resources/signin.json");
        return new Signin(obj.getString("email"), obj.getString("password"));
    }
}
