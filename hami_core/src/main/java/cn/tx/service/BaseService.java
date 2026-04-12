package cn.tx.service;

import cn.tx.util.Page;

public interface BaseService<T,Q> {

    //通过主键删除
    int deleteByPrimaryKey(Integer tid);

    //新增
    int insert(T record);

    //查询
    T selectByPrimaryKey(Integer tid);

    //通过传递实体类及实体类的主键作为条件进行修改
    int updateByPrimaryKey( T record);

    int insertSelective( T record);

    int updateByPrimaryKeySelective( T record);

    /**
     * 分页查询
     * @param q
     * @return
     */
    public Page<T> selectByPage(Q q);
}
