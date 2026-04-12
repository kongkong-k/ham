package cn.tx.service.impl;

import cn.tx.dao.SongerMapper;
import cn.tx.model.Mtype;
import cn.tx.model.Songer;
import cn.tx.query.SongerQuery;
import cn.tx.service.SongerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongerServiceImpl extends BaseServiceImpl<Songer, SongerQuery> implements SongerService {

    private SongerMapper songerMapper;

    @Autowired
    public void setSongerMapper(SongerMapper songerMapper){
        this.songerMapper=songerMapper;
        this.baseDao=songerMapper;
    }

    @Override
    public List<Songer> selectAll(){
        return songerMapper.selectAll();
    }

    @Override
    public Songer getSongerSong(Integer srId){
        return songerMapper.getSongerSong(srId);
    }


}
