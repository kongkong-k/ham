package cn.tx.dao;

import cn.tx.model.Songer;
import cn.tx.query.SongerQuery;

import java.util.List;

public interface SongerMapper extends BaseDao<Songer, SongerQuery> {
    int updateByPrimaryKeyWithBLOBs(Songer record);

    List<Songer> selectAll();

    Songer getSongerSong(Integer srId);

}