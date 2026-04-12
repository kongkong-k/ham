package cn.tx.controller;

import cn.tx.model.Album;
import cn.tx.query.AlbumQuery;
import cn.tx.service.AlbumService;
import cn.tx.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 流派模块的表现层实现类
 */
@Controller
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("/list")
    public String albumList(AlbumQuery albumQuery, Model model){

        Page<Album> page = albumService.selectByPage(albumQuery);
        model.addAttribute("page",page);
        model.addAttribute("mq",albumQuery);
        return "album";
    }

    @ResponseBody
    @RequestMapping("/addAlbum")
    public String addAlbum(Album album){
        albumService.insert(album);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/delAlbum")
    public String delAlbum(Album album){
        albumService.deleteByPrimaryKey(album.getAid());
        return "success";
    }

    @ResponseBody
    @RequestMapping("/getAlbum")
    public Album getAlbum(Integer aid) { // 直接接收专辑ID，更简洁
        Album album = albumService.selectByPrimaryKey(aid);
        return album; // 返回Album对象，Spring会自动转为JSON格式
    }

    @ResponseBody
    @PostMapping("/updateAlbum")
    public String updateAlbum(Album album){
        int i = albumService.updateByPrimaryKey(album);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/isSameName")
    public String isSameName(Album album){
        List<Album> albums = albumService.selectByName(album);
        return albums != null && !albums.isEmpty() ? "success" : "false";
    }
}
