package ru.zheleznikov.mesto.modelhelpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.zheleznikov.mesto.model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static org.testng.reporters.Files.readFile;
import static ru.zheleznikov.mesto.utils.UnsplashHelper.getRandomNameFromDryCodes;
import static ru.zheleznikov.mesto.utils.UnsplashHelper.getRandomPhotoFromUnsplash;

public class GenerateNewUser {
    public static void main(String[] args) throws IOException {

        writeJsonInFile(generateClassUser());
    }

    public static List<User> generateClassUser() {
        List<User> users = new ArrayList<>();
        User user = new User()
                .withName(getRandomNameFromDryCodes())
                .withEmail("ww@mail.ru")
                .withPassword("1234qwerty")
                .withAvatar(getRandomPhotoFromUnsplash())
                .withAbout("it's me");

        try {
            users.addAll(readUserJsonFile());
            users.add(user);
        } catch (IOException e) {
            users.add(user);
        }
        return users;
    }

    public static void writeJsonInFile(List<User> users) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(users);
        try (Writer writer = new FileWriter("src/test/resources/users.json");) {
            writer.write(json);
        }
    }

    public static List<User> readUserJsonFile() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        File jsonFile = new File("src/test/resources/users.json");
        String json1 = readFile(jsonFile);

        return gson.fromJson(json1, new TypeToken<List<User>>() {
        }.getType());  // List<User>.class
    }


}
