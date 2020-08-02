package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "mostoutdated")
public class MostOutdated {

    @Id
    @Column(name = "to_page_id", nullable = false)
    private Integer id;

    @Column(name = "cat_title", nullable = false)
    private String cat_title;

    @Column(name = "from_page_id", nullable = false)
    private Integer from_id;

    @Column(name = "to_page_title", nullable = false)
    private String to_title;

    @Column(name = "from_page_title", nullable = false)
    private String from_title;

    @Column(name = "diff", nullable = false)
    private BigInteger diff;

    public MostOutdated() {}
    public MostOutdated(String cat_title,
                        Integer id,
                        Integer from_id,
                        String to_title,
                        String from_title,
                        BigInteger diff) {
        this.cat_title = cat_title;
        this.id = id;
        this.from_id = from_id;
        this.to_title = to_title;
        this.from_title = from_title;
        this.diff = diff;
    }

    public String getCat_title() {
        return cat_title;
    }

    public void setCat_title(String cat_title) {
        this.cat_title = cat_title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFrom_id() {
        return from_id;
    }

    public void setFrom_id(Integer from_id) {
        this.from_id = from_id;
    }

    public String getTo_title() {
        return to_title;
    }

    public void setTo_title(String to_title) {
        this.to_title = to_title;
    }

    public String getFrom_title() {
        return from_title;
    }

    public void setFrom_title(String from_title) {
        this.from_title = from_title;
    }

    public BigInteger getDiff() {
        return diff;
    }

    public void setDiff(BigInteger diff) {
        this.diff = diff;
    }
}
