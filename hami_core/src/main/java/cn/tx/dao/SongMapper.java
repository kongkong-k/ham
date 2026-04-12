package cn.tx.dao;

import cn.tx.model.Song;
import cn.tx.query.SongQuery;

import java.util.List;


public interface SongMapper extends BaseDao<Song, SongQuery>{
    List<Song> selectAll();

    List<Song> selectSongBySids(List<Integer> list);

    Song getSong(Integer sid);
}
