package com.example.newspape.service.Imp;

import com.example.newspape.bean.Book;
import com.example.newspape.bean.User;
import com.example.newspape.mapper.UserMapper;
import com.example.newspape.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Override
    public String yanzheng(String username, String password) {
        String pwd = userMapper.getByUsername(username);
        int status=userMapper.getStatusByUsername(username);
        System.out.println(pwd);
        if(password.equals(pwd))
        {
            if(status==0)
                return "success0";
            else
                return "success1";
        }
        else
            return "error";
    }

    @Override
    public void saveUser(User user) {
        redisTemplate.delete("allUsers");
        userMapper.saveUser(user);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            redisTemplate.delete("allUsers");
        }
    }

    @Override
    public List<User> getAllUser() {
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<User> list= (List<User>) redisTemplate.opsForValue().get("allUsers");
        if(list==null)
        {
            synchronized (this){
                list= (List<User>) redisTemplate.opsForValue().get("allUsers");
                if(list==null)
                {
                    List<User> allUser = userMapper.getAllUser();;
                    redisTemplate.opsForValue().set("allUsers",allUser);
                    return allUser;
                }else{
                    return list;
                }
            }
        }else{
            return list;
        }
    }

    @Override
    public User getById(int id) {
        return userMapper.getById(id);
    }

    @Override
    public void updateUser(User user) {
        redisTemplate.delete("allUsers");
        userMapper.updateUser(user);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            redisTemplate.delete("allUsers");
        }
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
        redisTemplate.delete("allUsers");
        userMapper.deleteUser(id);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            redisTemplate.delete("allUsers");
        }
    }

    @Override
    public Boolean judgeUsername(String username) {
        String s = userMapper.getByUsername(username);
        if(s==null)
            return true;
        else
            return false;
    }

    @Override
    public String getName(String username) {
        return userMapper.getName(username);
    }

    @Override
    public int getId(String username) {
        return userMapper.getId(username);
    }
}
