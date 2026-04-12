package cn.tx.service.impl;

import cn.tx.dao.SongMapper;
import cn.tx.model.Song;
import cn.tx.query.SongQuery;
import cn.tx.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl extends BaseServiceImpl<Song, SongQuery> implements SongService {


   private SongMapper songDao;

   @Autowired
   public void setSongDao(SongMapper songDao) {
      this.songDao = songDao;
      this.baseDao = songDao;
   }
   @Override
   public List<Song> selectAll(){
      return songDao.selectAll();
   }
   @Override
   public List<Song> selectSongBySids(List<Integer> list){
      return songDao.selectSongBySids(list);
   }

   public Song getSong(Integer sid){
      return songDao.getSong(sid);
   }

}
