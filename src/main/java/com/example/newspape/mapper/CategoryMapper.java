package com.example.newspape.mapper;

import com.example.newspape.bean.Book;
import com.example.newspape.bean.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper {
    @Select("select * from category")
    public List<Category> getCategory();
    @Select("select cate from category where id=#{id}")
    public String getBook(Integer id);
    @Select("select category from book where id=#{id}")
    public String getCate(Integer id);
}
