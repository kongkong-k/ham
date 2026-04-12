package cn.tx.query;

import cn.tx.model.Album;

/**
 * 作为Album表现层接受参数的实体类
 * 与返回（响应）封装的实体类区分
 *
 */
public class AlbumQuery extends Album {
    /**
     * 每页记录数（已知）
     */
    private Integer pageSize = 5;

    /**
     * 页码（已知）
     */
    private Integer pageNum = 1;

    /**
     * 使用sql查询的时候的开始行号
     */
    private Integer startNum = 0;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getStartNum() {
        return startNum;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

}
