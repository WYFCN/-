package com.example.newspape.mapper;

import com.example.newspape.bean.Login;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginMapper {
    @Update("update login set login_state='未登录' where username=#{username}")
    public void updateLogin(String username);
    @Update("update login set login_state='已登录' where username=#{username}")
    public void updateLogin02(String username);
    @Select("select login_state from login where username=#{username}")
    public String getLoginState(String username);
    @Select("select * from login where username=#{username}")
    public Login getLogin(String username);
    @Insert("insert into login(username,login_state) values(username=#{username},login_state=#{login_state})")
    public void InsertLogin(String username,String login_state);
}
