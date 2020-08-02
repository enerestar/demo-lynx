package com.example.demo.controller;

import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.model.Page;
import com.example.demo.repo.PageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PageController {

    @Autowired
    private PageRepo pageRepo;

    @GetMapping("/pages")
    public List<Page> getAllPages() {
        return pageRepo.findAll();
    }

    @GetMapping("/pages/{pageid}")
    public ResponseEntity <Page> getPageById(@PathVariable(value="pageid") Integer pageid)
    throws ResourceNotFoundException {
        Page page = pageRepo.findById(pageid)
                .orElseThrow(() -> new ResourceNotFoundException("Page not found for this id :: " + pageid));
        return ResponseEntity.ok().body(page);
    }

    @PostMapping("/pages")
    public Page createPage(@Validated @RequestBody Page page) {
        return pageRepo.save(page);
    }

    @PutMapping("/pages/{pageid}")
    public ResponseEntity <Page> updatePage(@PathVariable(value = "pageid") Integer pageid,
                                                      @Validated @RequestBody Page pageDetails) throws ResourceNotFoundException {
        Page page = pageRepo.findById(pageid)
                .orElseThrow(() -> new ResourceNotFoundException("Page not found for this id :: " + pageid));

        page.setId(pageDetails.getId());
        page.setTitle(pageDetails.getTitle());
        page.setTimestampLastTouched(pageDetails.getTimestampLastTouched());
        final Page updatedPage = pageRepo.save(page);
        return ResponseEntity.ok(updatedPage);
    }
}

