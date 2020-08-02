package com.example.demo.repo;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT * FROM category ORDER BY cat_pages DESC limit 10",
            nativeQuery = true)
    List<Category> findTop10ByTitle();

    @Query(value = "SELECT * FROM category WHERE cat_title = ?1",
            nativeQuery = true)
    List<Category> findCategoryByTitle(String cat_title);
}
