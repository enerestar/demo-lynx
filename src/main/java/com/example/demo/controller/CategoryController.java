package com.example.demo.controller;

import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.MostOutdated;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.repo.MostOutdatedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private MostOutdatedRepo mostOutdatedRepo;

    @GetMapping("/categories")
    public List<Category> getTop10Category() {
        return categoryRepo.findTop10ByTitle();
    }

    @GetMapping("/categories/top10/{categorytitle}")
    public ResponseEntity<List<MostOutdated>> mostOutdatedPages(@PathVariable(value="categorytitle") String categorytitle)
            throws ResourceNotFoundException {
        List<MostOutdated> mostOutdatedPageInCategory = mostOutdatedRepo.getMostOutdatedTop10Category(categorytitle);
        if (mostOutdatedPageInCategory.isEmpty()) {
            throw new ResourceNotFoundException("Category does not exist in top 10 :: "  + categorytitle);
        }
        return ResponseEntity.ok().body(mostOutdatedPageInCategory);
    }

    @GetMapping("/categories/other/{categorytitle}")
    public ResponseEntity<List<MostOutdated>> mostOutdatedPageOther(@PathVariable(value="categorytitle") String categorytitle)
            throws ResourceNotFoundException {
        List<MostOutdated> mostOutdatedPageOtherCategory = mostOutdatedRepo.getMostOutdatedOtherCategory(categorytitle);
        if (mostOutdatedPageOtherCategory.isEmpty()) {
            throw new ResourceNotFoundException("uh oh.. something went wrong");
        }
        return ResponseEntity.ok().body(mostOutdatedPageOtherCategory);
    }
}
