package com.example.newspape.service.Imp;

import com.example.newspape.bean.Book;
import com.example.newspape.bean.Category;
import com.example.newspape.mapper.CategoryMapper;
import com.example.newspape.service.BookService;
import com.example.newspape.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Override
    public List<Category> getCategory() {
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Category> list= (List<Category>) redisTemplate.opsForValue().get("allCategorys");
        if(list==null)
        {
            synchronized (this){
                list= (List<Category>) redisTemplate.opsForValue().get("allCategorys");
                if(list==null)
                {
                    List<Category> allCategory =categoryMapper.getCategory();
                    redisTemplate.opsForValue().set("allCategorys",allCategory);
                    return allCategory;
                }else{
                    return list;
                }
            }
        }else{
            return list;
        }
    }

    @Override
    public String getBook(Integer id) {
        return categoryMapper.getBook(id);
    }

    @Override
    public String getCate(Integer id) {
        return categoryMapper.getCate(id);
    }
}
