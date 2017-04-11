package com.navy.entity;

import javax.persistence.*;

/**
 * 基础信息
 *
 * @author liuzh
 * @since 2016-01-31 21:42
 */
@Entity
public class BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //第一页
    @Transient
    private Integer page = 1;
    //每页显示5条记录
    @Transient
    private Integer rows = 10;

    public Integer getPage() {
        return page;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
