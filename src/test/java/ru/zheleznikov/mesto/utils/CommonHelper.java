package ru.zheleznikov.mesto.utils;

import ru.zheleznikov.mesto.model.Card;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommonHelper {

    private final static String PATH_TO_NAMES_TXT = "src/test/resources/names.txt";
    private final static String PATH_TO_LINKS_TXT = "src/test/resources/links.txt";


    public static Card getRandomCard(List<Card> cards) {
        return cards.get(getRandom(cards.size()));
    }

    public static List<Card> getExactContactCards(List<Card> cards, String id) {
        return cards.stream().filter(c -> c.getOwner().get_id().equals(id)).collect(Collectors.toList());
    }

    public static Card getExactCard(List<Card> cards, String id) {
        return cards.stream().filter(c -> c.get_id().equals(id)).findFirst().get();
    }

    public static List<Card> getOthersContactCard(List<Card> cards, String id) {
        return cards.stream().filter(c -> !c.getOwner().get_id().equals(id)).collect(Collectors.toList());
    }

    public static Card getExactCard(List<Card> cards) {
        return cards.get(getRandom(cards.size()));
    }

    public static String getRandomLink() throws IOException {
        List<String> links = generateArrayFromTxtFile(PATH_TO_LINKS_TXT);
        return links.get(getRandom(links.size()));
    }

    public static String getRandomName() {
        List<String> names = null;
        try {
            names = generateArrayFromTxtFile(PATH_TO_NAMES_TXT);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
