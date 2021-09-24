package ru.zheleznikov.mesto.main.modelhelpers;

import ru.zheleznikov.mesto.main.model.User;

import java.io.IOException;

import static ru.zheleznikov.mesto.main.modelhelpers.GenerateNewUser.readUserJsonFile;

public class UserHelper {

    public User getUserFromJson() {
        try {
            return readUserJsonFile().get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserFromJson(int n) {
        try {
            return readUserJsonFile().get(n);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
