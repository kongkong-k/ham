package cn.tx.dao;

import cn.tx.model.Album;
import cn.tx.query.AlbumQuery;

import java.util.List;

public interface AlbumMapper extends BaseDao<Album, AlbumQuery> {
    List<Album> selectAll();

    List<Album> selectByName(Album album);

}