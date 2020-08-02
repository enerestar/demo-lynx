package com.example.demo.repo;

import com.example.demo.model.MostOutdated;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawSQLQueryRepo extends JpaRepository<MostOutdated, String> {
}
