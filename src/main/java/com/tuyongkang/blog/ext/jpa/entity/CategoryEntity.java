package com.tuyongkang.blog.ext.jpa.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 文章所属分类的实体类
 */
@Entity
@Table(name = "T_CATEGORY")
public class CategoryEntity {

    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 分类的名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 分类的创建时间
     */
    @Column(name = "create_time")
    private Date createTime;


}
