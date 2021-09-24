package ru.zheleznikov.mesto.main.model;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@Accessors(chain = true)
public class User {

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", avatar='" + avatar + '\'' +
                ", _id='" + _id + '\'' +
                '}';
    }

    @Expose
    private String name;
    @Expose
    private String about;
    @Expose
    private String avatar;
    @Expose
    private String _id;
    @Expose
    private String email;
    @Expose
    private String password;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(about, user.about) && Objects.equals(avatar, user.avatar) && Objects.equals(_id, user._id) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, about, avatar, _id, email);
    }
}
