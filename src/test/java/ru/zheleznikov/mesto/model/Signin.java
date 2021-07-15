package ru.zheleznikov.mesto.model;

public class Signin {
    private String email;
    private String password;

    public Signin withEmail(String email) {
        this.email = email;
        return this;
    }

    public Signin withPassword(String password) {
        this.password = password;
        return this;
    }
}
