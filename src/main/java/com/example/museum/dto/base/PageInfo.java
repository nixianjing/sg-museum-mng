package com.example.museum.dto.base;

import java.io.Serializable;

/**
 * 类描述： mysql分页 适用于服务端
 * 提供两个构造函数
 * 1.默认分页大小
 * 2.自定义分页大小
 * @author  小明
 */
public class PageInfo<T> implements Serializable {
    /**
     * 默认分页大小
     */
    private static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 默认每页最大分页大小
     */
    private static final int MAX_PAGE_SIZE = 300;

    /**
     * limit 第一个参数的值  -1 说明传过来的数据不合法
     */
    private long limitOneValue = -1;

    /**
     * 总页数
     */
    private long totalPages;

    /**
     * 查询的页码
     */
    private long queryPage = 1;

    /**
     * 总条数
     */
    private long countNumbers;

    /**
     * 分页大小
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 分页大小备份
     */
    private int pageSizeBak = DEFAULT_PAGE_SIZE;

    /**
     * 下一个列表的多余数
     */
    private int nextNum = 0;

    private T data;

    public PageInfo(long queryPage) {
        if (queryPage <= 0) {
            this.queryPage = 1;
        } else {
            this.queryPage = queryPage;
        }
        this.queryPage = queryPage;
    }

    public PageInfo(long queryPage, int pageSize) {
        if (queryPage <= 0) {
            this.queryPage = 1;
        } else {
            this.queryPage = queryPage;
        }
        if (pageSize <= 0 || pageSize > MAX_PAGE_SIZE) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        } else {
            this.pageSize = pageSize;
        }
    }

    private PageInfo(long queryPage, int pageSize, int nextNum, int pageSizeBak) {
        if (queryPage <= 0) {
            this.queryPage = 1;
        } else {
            this.queryPage = queryPage;
        }
        if (pageSize <= 0 || pageSize > MAX_PAGE_SIZE) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        } else {
            this.pageSize = pageSize;
        }
        this.pageSizeBak = pageSizeBak;
        if (nextNum <= 0) {
            this.nextNum = 0;
        } else {
            this.nextNum = nextNum;
        }
    }

    /**
     * 设置总条数
     *
     * @param countNumbers
     */
    public void setCountNumbers(long countNumbers) {
        this.countNumbers = countNumbers;
        countTotalPages();
        countLimitOneValue();
    }

    /**
     * 是否可以进行查询
     *
     * @return true是 false否
     */
    public boolean isQuery() {
        if (limitOneValue < 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获得第二个列表查询的分页信息
     *
     * @param allNum   总数量
     * @param listSize 列表数量
     * @return
     */
    public PageInfo nextPageInfo(long allNum, int listSize) {
        int nextPageSize = getPageSize() - listSize;
        int yuShu = Integer.parseInt(allNum % getPageSize() + "");
        long nextQueryPage = 1;
        if (yuShu == 0) {
            nextQueryPage = getQueryPage() - getTotalPages();
        } else {
            nextQueryPage = (getQueryPage() - getTotalPages()) + 1;
        }
        return new PageInfo(nextQueryPage, nextPageSize, yuShu, getPageSize());
    }

    /**
     * 计算总页数总页数
     */
    private void countTotalPages() {
        long totalPages = 0;
        if (this.countNumbers <= 0) {
            totalPages = 0;
        } else {
            if (nextNum > 0) {
                long allNum = this.countNumbers - nextNum;
                if (allNum <= 0) {
                    totalPages = 1;
                } else {
                    if (allNum % this.pageSizeBak == 0) {// 页数刚好是条数的总倍数
                        totalPages = (this.countNumbers / this.pageSizeBak) + 1;
                    } else {
                        totalPages = (this.countNumbers / this.pageSizeBak) + 2;// 页数不是总条数的倍数
                    }
                }
            } else {
                if (this.countNumbers % this.pageSize == 0) {// 页数刚好是条数的总倍数
                    totalPages = (this.countNumbers / this.pageSize);
                } else {
                    totalPages = (this.countNumbers / this.pageSize) + 1;// 页数不是总条数的倍数
                }
            }
        }
        this.totalPages = totalPages;
    }

    /**
     * 计算limit第一个参数的值
     */
    private void countLimitOneValue() {
        if (!isParameterLegitimacy()) {// 总页数小于等于0 当前也超过总页数 当前页小于 0
            this.limitOneValue = -1;//不合法的分页参数校验
        } else {
            if (nextNum > 0) {
                if (queryPage > 1) {
                    this.limitOneValue = ((queryPage - 1) * pageSize) - (pageSizeBak - nextNum);
                } else {
                    this.limitOneValue = (queryPage - 1) * pageSize;
                }
            } else {
                this.limitOneValue = (queryPage - 1) * pageSize;
            }
        }
    }

    /**
     * 判断用户传过来的值是否合法
     */
    private boolean isParameterLegitimacy() {
        if (totalPages <= 0 || queryPage > totalPages || queryPage <= 0) {// 总页数小于等于0 当前页超过总页数 当前页小于等于0
            return false;// 不合法的分页参数校验
        }
        return true;
    }

    public long getLimitOneValue() {
        return limitOneValue;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public long getQueryPage() {
        return queryPage;
    }

    public long getCountNumbers() {
        return countNumbers;
    }

    public int getPageSize() {
        return pageSize;
    }

    /**
     * 计算总页数
     *
     * @return
     */
    public static long countAllPage(long allNum, int size) {
        if (allNum % size == 0) {// 页数刚好是条数的总倍数
            return (allNum / size);
        } else {
            return (allNum / size) + 1;// 页数不是总条数的倍数
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
