package ru.zheleznikov.mesto.cases;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import ru.zheleznikov.mesto.model.Signin;
import ru.zheleznikov.mesto.model.User;

import static ru.zheleznikov.mesto.utils.JsonHelper.generateStringToReq;

public class TestTheories extends TestBase {

    @Test
    public void generateUser() {
        User user =
                app.user().getUserFromJson();

        user.withoutName().withoutAbout().withoutAvatar();
        System.out.println(new Gson().toJson(user));
//        Signin signin = new Signin().withPassword(user.getPassword()).withEmail(user.getEmail());

    }
}
