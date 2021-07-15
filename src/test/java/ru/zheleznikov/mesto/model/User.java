package ru.zheleznikov.mesto.model;

import com.google.gson.annotations.Expose;

import java.util.Objects;

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

    public String getPassword() {
        return password;
    }

    public User withPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public User withName(String name) {
        this.name = name;
        return this;
    }

    public User withoutName() {
        this.name = null;
        return this;
    }

    public User withoutAvatar() {
        this.avatar = null;
        return this;
    }

    public User withoutAbout() {
        this.about = null;
        return this;
    }

    public String getAbout() {
        return about;
    }

    public User withAbout(String about) {
        this.about = about;
        return this;

    }

    public String getAvatar() {
        return avatar;
    }

    public User withAvatar(String avatar) {
        this.avatar = avatar;
        return this;

    }

    public String get_id() {
        return _id;
    }

    public User with_id(String _id) {
        this._id = _id;
        return this;

    }

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
