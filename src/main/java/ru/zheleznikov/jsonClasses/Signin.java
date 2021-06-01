package ru.zheleznikov.jsonClasses;

public class Signin {
    private String email;
    private String password;

    public Signin() {super();}

    public Signin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
