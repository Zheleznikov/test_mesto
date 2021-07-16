package ru.zheleznikov.mesto.modelhelpers;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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
import java.util.Locale;

import static org.testng.reporters.Files.readFile;
import static ru.zheleznikov.mesto.utils.UnsplashHelper.getRandomNameFromDryCodes;
import static ru.zheleznikov.mesto.utils.UnsplashHelper.getRandomPhotoFromUnsplash;

public class GenerateNewUser {

    @Parameter(names = {"-c", "--count"}, description = "count to generate")
    public  int n = 20; // default value if no arg -d in command line

    public static void main(String[] args) throws IOException {

        GenerateNewUser generator = new GenerateNewUser();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            jCommander.usage();
            return;
        }

        generator.run();
    }

    public  void run() throws IOException {
        writeJsonInFile(generateClassUser());

    }

    public  List<User> generateClassUser() {
        List<User> allUsers = new ArrayList<>();
        try {
            allUsers.addAll(readUserJsonFile());
            allUsers.addAll(generateListOfUsers(n));
        } catch (IOException e) {
            e.printStackTrace();
            allUsers.addAll(generateListOfUsers(n));
        }

        return allUsers;
    }

    public  List<User> generateListOfUsers(int n) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> fields = getRandomNameFromDryCodes(2);
            users.add(
                    new User()
                            .withName(fields.get(0).replace("_", " "))
                            .withEmail(fields.get(0).toLowerCase(Locale.ROOT) + "@zhe.ru")
                            .withPassword("1234qwerty")
                            .withAvatar(getRandomPhotoFromUnsplash())
                            .withAbout(fields.get(1).replace("_", " "))
            );
        }
        return users;
    }

    public  void writeJsonInFile(List<User> users) throws IOException {
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
