package com.example.demo.services;

import com.example.demo.model.Category;
import com.example.demo.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Category> listAll() {
        return new ArrayList<>(categoryRepo.findAll());
    }

    @Override
    public Category getById(Integer id) {
        return categoryRepo.findById(id).orElse(null);
    }

    @Override
    public Category getByTitle(String title) {
        return null;
    }
}
