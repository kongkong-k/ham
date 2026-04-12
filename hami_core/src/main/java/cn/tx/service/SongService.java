package cn.tx.service;

import cn.tx.model.Song;
import cn.tx.query.SongQuery;

import java.util.List;

public interface SongService extends BaseService<Song, SongQuery>{
    List<Song> selectAll();

    List<Song> selectSongBySids(List<Integer> list);

    Song getSong(Integer sid);
}
