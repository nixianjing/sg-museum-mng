package com.example.museum.dto.base;

import java.io.Serializable;
import java.util.List;

/**
 * 分页Bean类
 * @author 小明
 */
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 5808581910906126235L;
    /**
     * 当前页面
     */
    private int pageIndex = 1;

    /**
     * 每页记录数
     */
    private int pageSize = 10;

    /**
     * 总记录数
     */
    private int totalRecord;

    /**
     * 总记页码
     */
    private int totalPage;

    private int count;

    /**
     * 响应数据
     */
    private List<T> data;

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应是否成功
     */
    public boolean isSuccess() {
        return CodeTable.SUCCESS.getCode() == code;
    }

    /**
     * 默认构造函数
     */
    public PageResult() {
        super();
    }

    /**
     * 响应码的构造函数
     *
     * @param codeTable
     */
    public PageResult(CodeTable codeTable) {
        this.code = codeTable.getCode();
        this.message = codeTable.getMessage();
        this.data = null;
    }

    /**
     * 响应码的构造函数
     * 自定义消息
     *
     * @param codeTable
     */
    public PageResult(CodeTable codeTable, String msg) {
        this.code = codeTable.getCode();
        this.message = msg;
        this.data = null;
    }

    /**
     * 构造函数  (成功默认调用)
     *
     * @param pageIndex
     * @param pageSize
     * @param totalRecord
     * @param data
     */
    public PageResult(int pageIndex, int pageSize, int totalRecord, List<T> data) {
        this.code = CodeTable.SUCCESS.getCode();
        this.message = CodeTable.SUCCESS.getMessage();
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.totalRecord = totalRecord;
        this.data = data;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    /**
     * 总页数
     *
     * @return
     */
    public int getTotalPage() {
        if (totalRecord == 0) {
            return 0;
        }
        this.totalPage = (totalRecord + getPageSize() - 1) / getPageSize();
        return this.totalPage;
    }

    /**
     * 是否有下页
     *
     * @return
     */
    public boolean hasNextPage() {
        if (pageIndex < getTotalPage()) {
            return true;
        } else {
            return false;
        }
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return this.totalRecord;
    }
}
