package cn.tx.dao;

import java.util.List;

/*
* 持久层：base接口
* 目的：抽取公用方法
**/
public interface BaseDao<T,Q> {

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

    List<T> selectByPage(Q q);

    Integer selectCount(Q q);
}
