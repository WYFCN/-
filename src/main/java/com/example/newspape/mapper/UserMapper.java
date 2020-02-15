package com.example.newspape.mapper;

import com.example.newspape.bean.Book;
import com.example.newspape.bean.User;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @Select("select password from user where username=#{username}")
    public String getByUsername(String username);
    @Insert("insert into user(username,password,email,name) values(#{username},#{password},#{email},#{name})")
    public void saveUser(User user);
    @Select("select status from user where username=#{username}")
    public int getStatusByUsername(String username);
    @Select("select * from user")
    public List<User> getAllUser();
    @Select("select * from user where id=#{id}")
    public User getById(int id);
    @Update("update user set password=#{password},status=#{status} where name=#{name}")
    public void updateUser(User user);
    @Delete("delete from user where id=#{id}")
    public void deleteUser(Integer id);
    @Select("select name from user where username=#{username}")
    public String getName(String username);
    @Select("select id from user where username=#{username}")
    public int getId(String username);
}
