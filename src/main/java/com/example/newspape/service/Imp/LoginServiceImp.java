package com.example.newspape.service.Imp;

import com.example.newspape.bean.Login;
import com.example.newspape.mapper.LoginMapper;
import com.example.newspape.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp implements LoginService {
    @Autowired
    LoginMapper loginMapper;
    @Override
    public void updateLogin(String username) {
        loginMapper.updateLogin(username);
    }

    @Override
    public String getLoginState(String username) {
        return loginMapper.getLoginState(username);
    }

    @Override
    public Login getLogin(String username) {
        return loginMapper.getLogin(username);
    }

    @Override
    public void updateLogin02(String username) {
        loginMapper.updateLogin02(username);
    }

    @Override
    public void InsertLogin(String username, String login_state) {
        loginMapper.InsertLogin(username,login_state);
    }
}
