package com.example.newspape.service;

import com.example.newspape.bean.Book;
import com.example.newspape.bean.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getCategory();
    public String getBook(Integer id);
    public String getCate(Integer id);
}
