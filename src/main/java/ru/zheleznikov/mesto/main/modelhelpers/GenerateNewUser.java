package ru.zheleznikov.mesto.main.modelhelpers;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.zheleznikov.mesto.main.app.HelperBase;
import ru.zheleznikov.mesto.main.model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static ru.zheleznikov.mesto.main.utils.JsonHelper.readFile;
import static ru.zheleznikov.mesto.main.utils.UnsplashHelper.getRandomNameFromDryCodes;
import static ru.zheleznikov.mesto.main.utils.UnsplashHelper.getRandomPhotoFromUnsplash;

public class GenerateNewUser extends HelperBase {


    private static final String PATH_TO_FILE = "src/main/resources/users.json";

    @Parameter(names = {"-c", "--count"}, description = "count to generate")
    public int n = 2; // default value if no arg -d in command line

    public GenerateNewUser() throws IOException {
    }

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

    public void run() throws IOException {
        writeJsonInFile(generateClassUser());

    }

    public List<User> generateClassUser() {
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

    public List<User> generateListOfUsers(int n) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> fields = getRandomNameFromDryCodes(2);
            users.add(
                    new User()
                            .setName(fields.get(0).replace("_", " "))
                            .setEmail(fields.get(0).replace("_", " ").toLowerCase(Locale.ROOT) + "@zhe.ru")
                            .setPassword(properties.getProperty("default.pass"))
                            .setAvatar(getRandomPhotoFromUnsplash())
                            .setAbout(fields.get(1).replace("_", " "))
            );
        }
        return users;
    }

    public void writeJsonInFile(List<User> users) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(users);
        try (Writer writer = new FileWriter(PATH_TO_FILE);) {
            writer.write(json);
        }
    }

    public static List<User> readUserJsonFile() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        File jsonFile = new File(PATH_TO_FILE);
        String json1 = readFile(jsonFile);

        return gson.fromJson(json1, new TypeToken<List<User>>() {
        }.getType());  // List<User>.class
    }


}
