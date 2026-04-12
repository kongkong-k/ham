package cn.tx.controller;

import cn.tx.model.Album;
import cn.tx.model.Mtype;
import cn.tx.model.Song;
import cn.tx.model.Songer;
import cn.tx.query.SongQuery;
import cn.tx.service.AlbumService;
import cn.tx.service.MtypeService;
import cn.tx.service.SongService;
import cn.tx.service.SongerService;
import cn.tx.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 歌曲模块的表现层实现类
 */
@Controller
@RequestMapping("/song")
public class SongController {

    @Autowired
    private MtypeService mtypeService;

    @Autowired
    private SongerService songerService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;

    @RequestMapping("/list")
    public String listType(SongQuery mq, Model model){

        if(mq.getPageNum() == null){
            mq.setPageNum(1);
        }
        Page<Song> page = songService.selectByPage(mq);
        model.addAttribute("page", page);
        model.addAttribute("mq", mq);


        List<Mtype> mtypes = mtypeService.selectAll();
        List<Songer> songers = songerService.selectAll();
        List<Album> albums = albumService.selectAll();
        model.addAttribute("mtypes", mtypes);
        model.addAttribute("songers", songers);
        model.addAttribute("albums", albums);
//        model.addAttribute("filePath", "http://localhost:8085");
        return "song";
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model){
        List<Mtype> mtypes = mtypeService.selectAll();
        model.addAttribute("mtypes",mtypes);
        List<Songer> songers = songerService.selectAll();
        model.addAttribute("songers",songers);
        List<Album> albums = albumService.selectAll();
        model.addAttribute("albums",albums);
        return "addSong";
    }

    @RequestMapping("/add")
    public String add(Song song){
        Songer songer = songerService.selectByPrimaryKey(song.getSrid());
        song.setPic(songer.getPic());
        int insert = songService.insert(song);
        return "redirect:list";
    }


    @ResponseBody
    @RequestMapping("/delSong")
    public String delSong(Integer sid){
        Song song = songService.selectByPrimaryKey(sid);
//        Client client = Client.create();
//        String filePath="http://localhost:808";
//        WebResource resource = client.resource(filePath+songer.getPic());
//        resource.delete();
        int res = songService.deleteByPrimaryKey(sid);
        if (res == 0){
            return "error";
        }
        return "success";
    }


    /**
     * 歌曲修改方法
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer sid,Model model){
        Song song = songService.selectByPrimaryKey(sid);
        model.addAttribute("song",song);
        //查询流派
        List<Mtype> mtypes = mtypeService.selectAll();
        model.addAttribute("mtypes", mtypes);
        List<Songer> songers = songerService.selectAll();
        model.addAttribute("songers",songers);
        List<Album> albums = albumService.selectAll();
        model.addAttribute("albums",albums);
        model.addAttribute("filePath", "http://localhost:8085");
        return "updateSong";
    }

    @RequestMapping("/update")
    public String updateSong(Song song){
        songService.updateByPrimaryKey(song);
        return "redirect:list";
    }

}
