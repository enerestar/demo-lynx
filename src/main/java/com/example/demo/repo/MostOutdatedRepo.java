package com.example.demo.repo;

import com.example.demo.model.MostOutdated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MostOutdatedRepo extends JpaRepository<MostOutdated, Integer> {

    @Query(value = "SELECT * FROM outdated WHERE cat_title = ?1",
    nativeQuery = true)
    List<MostOutdated> getMostOutdatedTop10Category(String categoryTitle);

    @Query(value =  "SELECT\n" +
            "    category.cat_title,\n" +
            "    page_from.page_id as from_page_id,\n" +
            "    page_from.page_title as from_page_title,\n" +
            "    page_to.page_id as to_page_id,\n" +
            "    page_to.page_title as to_page_title,\n" +
            "    MIN(TIMESTAMPDIFF(MINUTE, page_to.page_touched, page_from.page_touched)) as diff\n" +
            "FROM category\n" +
            "LEFT OUTER JOIN categorylinks \n" +
            "    ON category.cat_title = categorylinks.cl_to \n" +
            "LEFT OUTER JOIN page AS page_from\n" +
            "    ON page_from.page_id = categorylinks.cl_from\n" +
            "LEFT OUTER JOIN pagelinks\n" +
            "    ON pagelinks.pl_from = page_from.page_id\n" +
            "LEFT OUTER JOIN page AS page_to\n" +
            "    ON pagelinks.pl_title = page_to.page_title\n" +
            "WHERE\n" +
            "    categorylinks.cl_to = ?1 \n" +
            "AND \n" +
            "    page_to.page_id IS NOT NULL\n" +
            "GROUP BY \n" +
            "    to_page_id\n" +
            "ORDER BY\n" +
            "    diff\n" +
            "LIMIT 1;",
    nativeQuery = true)
    List<MostOutdated> getMostOutdatedOtherCategory(String categoryTitle);
}
