package com.example.newspape.service;

import com.example.newspape.bean.User;

import java.util.List;

public interface UserService {
    public String yanzheng(String username,String password);
    public void saveUser(User user);
    public List<User> getAllUser();
    public User getById(int id);
    public void updateUser(User user);
    public void deleteUser(Integer id);
    public Boolean judgeUsername(String username);
    public String getName(String username);
    public int getId(String username);
}
