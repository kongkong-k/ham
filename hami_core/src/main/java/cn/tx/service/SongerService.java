package cn.tx.service;

import cn.tx.model.Songer;
import cn.tx.query.SongerQuery;

import java.util.List;

public interface SongerService extends BaseService<Songer, SongerQuery>{
    List<Songer> selectAll();

    Songer getSongerSong(Integer srId);
}
