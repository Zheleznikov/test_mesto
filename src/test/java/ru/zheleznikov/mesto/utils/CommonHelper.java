package ru.zheleznikov.mesto.utils;

import ru.zheleznikov.mesto.model.Card;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommonHelper {

    private final static String PATH_TO_NAMES_TXT = "src/test/resources/names.txt";
    private final static String PATH_TO_LINKS_TXT = "src/test/resources/links.txt";


    public static Card getRandomCard(List<Card> cards) {
        return cards.get(getRandom(cards.size()));
    }

    public static String getRandomLink() throws IOException {
        List<String> links = generateArrayFromTxtFile(PATH_TO_LINKS_TXT);
        return links.get(getRandom(links.size()));
    }

    public static String getRandomName() throws IOException {
        List<String> names = generateArrayFromTxtFile(PATH_TO_NAMES_TXT);
        return names.get(getRandom(names.size()));
    }

    public static List<String> generateArrayFromTxtFile(String pathToRead) throws IOException {
        List<String> array = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToRead))) {
            String line = reader.readLine();
            while (line != null) {
                array.add(line);
                line = reader.readLine();
            }
        }
        return array;
    }

    public static int getRandom(int n) {
        return (int) (Math.random() * n);
    }


}
