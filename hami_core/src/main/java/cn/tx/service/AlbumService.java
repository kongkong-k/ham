package cn.tx.service;

import cn.tx.model.Album;
import cn.tx.query.AlbumQuery;

import java.util.List;

public interface AlbumService extends BaseService<Album, AlbumQuery>{
    List<Album> selectAll();

    List<Album> selectByName(Album album);
}
