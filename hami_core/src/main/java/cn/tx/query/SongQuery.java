package cn.tx.query;

import cn.tx.model.Song;

/**
 * 作为Songer表现层接受参数的实体类
 * 与返回（响应）封装的实体类区分
 *
 */
public class SongQuery extends Song {
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

    private Integer pageNoPortal = 1;

    // 冗余字段设计
    // 目的是接收前端模糊查询的参数
    // 歌手名称
    private  String srname;
    // 专辑名称
    private  String aname;

    @Override
    public String getSrname() {
        return srname;
    }

    @Override
    public void setSrname(String srname) {
        this.srname = srname;
    }

    @Override
    public String getAname() {
        return aname;
    }

    @Override
    public void setAname(String aname) {
        this.aname = aname;
    }

    public Integer getPageNoPortal() {
        return pageNoPortal;
    }

    public void setPageNoPortal(Integer pageNoPortal) {
        this.pageNoPortal = pageNoPortal;
    }

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
