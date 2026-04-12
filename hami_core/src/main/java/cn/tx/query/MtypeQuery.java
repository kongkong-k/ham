package cn.tx.query;

import cn.tx.model.Mtype;

/**
 * 作为Mtype表现层接受参数的实体类
 * 与返回（响应）封装的实体类区分
 * 这是“分层设计”的最佳实践，目的是解耦、安全、可维护。
 * MtypeQuery 是“前端要什么”，Mtype 是“数据库有什么”
 */
public class MtypeQuery extends Mtype {

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
