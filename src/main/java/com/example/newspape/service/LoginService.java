package com.example.newspape.service;

import com.example.newspape.bean.Login;

public interface LoginService {
    public void updateLogin(String username);
    public String getLoginState(String username);
    public Login getLogin(String username);
    public void updateLogin02(String username);
    public void InsertLogin(String username,String login_state);
}
