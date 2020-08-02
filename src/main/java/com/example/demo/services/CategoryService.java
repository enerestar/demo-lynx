package com.example.demo.services;

import com.example.demo.model.Category;
import com.example.demo.model.Page;

import java.util.List;

public interface CategoryService {
    List<Category> listAll();
    Category getById(Integer id);
    Category getByTitle(String title);
}
