package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "page")
public class Page {

    @Id
    @Column(name = "page_id", nullable = false)
    private Integer id;

    @Column(name = "page_title", nullable = false)
    private String title;

    @Column(name = "page_touched", nullable = false)
    private String timestampLastTouched;

    public Page() {

    }

    public Page(Integer id, String title, String timestampLastTouched) {
        this.id = id;
        this.title = title;
        this.timestampLastTouched = timestampLastTouched;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimestampLastTouched() {
        return timestampLastTouched;
    }

    public void setTimestampLastTouched(String timestampLastTouched) {
        this.timestampLastTouched = timestampLastTouched;
    }
}
