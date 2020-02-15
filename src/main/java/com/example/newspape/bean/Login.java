package com.example.newspape.bean;

public class Login {
    private String username;
    private String login_state;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin_state() {
        return login_state;
    }

    public void setLogin_state(String login_state) {
        this.login_state = login_state;
    }

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", login_state='" + login_state + '\'' +
                '}';
    }
}
