package cn.tx.util;

import java.util.List;

/**
 * 分页工具类
 * @param <T>
 */
public class Page<T> {


    /**
     * 每页记录数（已知）
     */
    private int pageSize = 5;

    /**
     * 页码（已知）
     */
    private int pageNum = 1;

    /**
     * 指定查询条件下的总记录数（已知）
     */
    private int totalCount = 0;

    /**
     * 指定查询条件下 的总页数
     */
    private int totalPage = 1;

    /**
     * 使用sql查询的时候的开始行号
     */
    private int startNum = 0;


    /**
     * 结果集
     */
    private List<T> list;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


    /**
     *
     *
     * totalCount  pageSize   totalPage
     * 0         10       1
     * 55        10       6
     * 100       10       10
     * @return
     */
    public int getTotalPage() {
        totalPage = totalCount/pageSize;
        if(totalCount == 0 || totalCount%pageSize != 0){
            totalPage++;
        }

        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartNum() {
        return (pageNum -1 )*pageSize;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
