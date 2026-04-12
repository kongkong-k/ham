package cn.tx.service.impl;

import cn.tx.dao.AlbumMapper;
import cn.tx.model.Album;
import cn.tx.query.AlbumQuery;
import cn.tx.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl extends BaseServiceImpl<Album, AlbumQuery> implements AlbumService {

    private AlbumMapper albumMapper;

    @Autowired
    public void setAlbumMapper(AlbumMapper albumMapper){
        this.albumMapper=albumMapper;
        this.baseDao=albumMapper;
    }

    @Override
    public List<Album> selectAll(){
        return albumMapper.selectAll();
    }

    @Override
    public List<Album> selectByName(Album album){
        return albumMapper.selectByName(album);
    }

}