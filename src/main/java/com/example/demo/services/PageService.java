package com.example.demo.services;

import com.example.demo.model.Page;

import java.util.List;

public interface PageService {
    List<Page> listAll();
    Page getById(Integer id);
}
