package com.example.demo.repo;

import com.example.demo.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepo extends JpaRepository<Page, Integer> {
}
