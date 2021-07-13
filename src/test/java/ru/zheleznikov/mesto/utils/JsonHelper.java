package ru.zheleznikov.mesto.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.zheleznikov.mesto.model.Card;

import java.io.*;
import java.util.List;

public class JsonHelper {

    public static List<Card> generateCardList(List<Object> cardsFromApi) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(cardsFromApi);
        System.out.println(json);
        return gson.fromJson(json, new TypeToken<List<Card>>() {}.getType());// List<Card>.class
    }

    public static String generateStringToReq(Card card) {
        return new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create().toJson(card);
    }

    public static Card generateCard(Object card) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(card);
        System.out.println(json);
        return gson.fromJson(json, Card.class);// Card.class
    }

    public static void writeJsonInFile(List<Object> cardsFromApi) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(cardsFromApi);
        try (Writer writer = new FileWriter("src/test/resources/cards.json");) {
            writer.write(json);
        }
    }

    public static List<Card> readJsonFromFile() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        File jsonFile = new File("src/test/resources/cards.json");
        String json1 = readFile(jsonFile);

        List<Card> cards = gson.fromJson(json1, new TypeToken<List<Card>>() {
        }.getType());  // List<Cards>.class

        cards.forEach(c-> System.out.println(c));
        return cards;
    }

    private static String readFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String str = "";
            String line = reader.readLine();
            while (line != null) {
                str += line;
                line = reader.readLine();
            }
            return str;
        }
    }


}
