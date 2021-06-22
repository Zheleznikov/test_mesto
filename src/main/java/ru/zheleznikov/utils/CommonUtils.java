package ru.zheleznikov.utils;

import org.json.JSONObject;
import ru.zheleznikov.jsonClasses.Signin;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CommonUtils {

    /**
     *     1 способ. Получение содержимого файла, как объект с типом данных JSONObject
     *     Для дальнейшего преобразования в класс с валидными параметрами.
     *     После этого класс передается в тело запроса
     *     с помощью метода getSigninJson
      */
    private static JSONObject getJson(String path) throws IOException {
        Scanner sc = new Scanner(new File(path));
        String jsonFile = "";
        while (sc.hasNextLine()) {
            jsonFile += sc.nextLine();
        }
        return new JSONObject(jsonFile);
    }

    /**
     * 2 способ. Получение содержимого файла, как объект с типом данных String
     * После этого полученная строка сразу же передается как строка в тело запроса
     */
    private static String getFileContentAsString(String path) throws IOException {
        Scanner sc = new Scanner(new File(path));
        String file = "";
        while (sc.hasNextLine()) {
            file += sc.nextLine();
        }
        return file;
    }

    public static Signin getSigninJson() throws IOException {
        JSONObject obj = getJson("src/main/resources/signin.json");
        return new Signin(obj.getString("email"), obj.getString("password"));
    }

    public static String getSigninJson2() throws IOException {
        return getFileContentAsString("src/main/resources/signin.json");
    }

    public static String postCardJson() throws IOException {
        return getFileContentAsString("src/main/resources/postCard.json");

    }
}
