package com.tuyongkang.blog.model.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 文章的实体类
 */
@Entity
@Table(name = "T_ARTICLE")
public class ArticleEntity {

    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文章标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 文章摘要
     */
    @Column(name = "digest")
    private String digest;

    /**
     * 文章内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 文章所属分类
     */
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 文章创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 文章最近一次修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 文章的浏览次数
     */
    @Column(name = "browse_times")
    private Integer browseTimes;

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

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getBrowseTimes() {
        return browseTimes;
    }

    public void setBrowseTimes(Integer browseTimes) {
        this.browseTimes = browseTimes;
    }
}
