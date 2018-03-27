package org.pf9.pangu.framework.data.mybatis.dto;

public class PaginationQuery {

    private int pageNum;

    private int pageSize;

    private String query;

    public int getPageNum() {

        return pageNum;
    }

    public void setPageNum(int pageNum) {

        this.pageNum = pageNum;
    }

    public int getPageSize() {

        return pageSize;
    }

    public void setPageSize(int pageSize) {

        this.pageSize = pageSize;
    }

    public String getQuery() {

        return query;
    }

    public void setQuery(String query) {

        this.query = query;
    }
}
