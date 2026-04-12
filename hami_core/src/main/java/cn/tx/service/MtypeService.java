package cn.tx.service;

import cn.tx.model.Mtype;
import cn.tx.query.MtypeQuery;

import java.util.List;

public interface MtypeService extends BaseService<Mtype, MtypeQuery> {
    List<Mtype> selectAll();
}