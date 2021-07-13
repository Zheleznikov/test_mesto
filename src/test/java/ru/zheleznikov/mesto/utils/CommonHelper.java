package ru.zheleznikov.mesto.utils;

import ru.zheleznikov.mesto.model.Card;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommonHelper {

    public static Card getRandomCard(List<Card> cards) {
        return cards.get((int) (Math.random() * cards.size()));
    }

    public static String getRandomLink() throws IOException {
        List<String> links = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/links.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                links.add(line);
                line = reader.readLine();
            }
            return links.get((int) (Math.random() * links.size()));
        }


    }

    public static String getRandomName() throws IOException {
        List<String> links = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/names.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                links.add(line);
                line = reader.readLine();
            }
            return links.get((int) (Math.random() * links.size()));
        }
    }



}
