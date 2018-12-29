package com.tuyongkang.blog.model.common;

import java.util.List;

/**
 * 分页model
 * @param <T>
 */
public class PageModel<T> {

    /**
     * 数据总条数
     */
    private Integer total;

    /**
     * 分页的数据
     */
    private List<T> list;

    /**
     * 当前第几页
     */
    private Integer pageNo;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
