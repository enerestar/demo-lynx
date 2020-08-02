package com.example.demo.services;

import com.example.demo.model.Page;
import com.example.demo.repo.PageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageServiceImpl implements PageService {

    private PageRepo pageRepo;

    @Autowired
    public PageServiceImpl(PageRepo pageRepo) {
        this.pageRepo = pageRepo;
    }

    @Override
    public List<Page> listAll() {
        return new ArrayList<>(pageRepo.findAll());
    }

    @Override
    public Page getById(Integer id) {
        return pageRepo.findById(id).orElse(null);
    }


}
