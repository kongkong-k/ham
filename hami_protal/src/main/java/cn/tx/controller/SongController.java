package cn.tx.controller;

import cn.tx.model.Mtype;
import cn.tx.model.Song;
import cn.tx.query.SongQuery;
import cn.tx.service.MtypeService;
import cn.tx.service.SongService;
import cn.tx.util.Page;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/song")
public class SongController {

    @Autowired
    private MtypeService mtypeService;

    @Autowired
    private SongService songService;

    /**
     * 直接展示所有歌曲
     * @param mq
     * @param model
     * @return
     */
    @RequestMapping("/dofindAll")
    public String doFindALL(SongQuery mq, Model model){

        if(mq.getPageNum() == null){
            mq.setPageNum(1);
        }
        // 调用业务层进行分页查询
        Page<Song> page = songService.selectByPage(mq);
        // 前端显示流派做添加
        List<Mtype> mtypeList = mtypeService.selectAll();
        // 返回给前端数据
        model.addAttribute("page", page);
        model.addAttribute("mq", mq);
        model.addAttribute("mtypes", mtypeList);
        return "search";
    }

    @RequestMapping("/play")
    public String play(String sids, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        // 定义一个数组进行装载
        String[] idsArr = null;
        // 非空判断
        if (sids != null && !"".equals(sids)){
            idsArr = sids.split(",");
        }
        // 同时解读上次播放列表有没有歌曲
        Cookie[] cookies = request.getCookies();
        String cookieIds = null;
        // 存上次的所有sid值 已有的播放列表的值
        String [] idsArrCookie = null;
        // 遍历浏览器中的所有cookie 找到自己的
        if (cookies != null){
            for (Cookie cookie : cookies){
                // 每一个cookie的名字
                String cookieName = cookie.getName();
                // 匹配是不是我的要找cookie
                if ("playids".equals(cookieName)){
                    //获取当前播放列表中的值
                    cookieIds = cookie.getValue();
                    // 解码
                    cookieIds = URLDecoder.decode(cookieIds,"UTF-8");
                }
            }
        }
        // 拿到cookie的值之后判断是否为空，不为空，解析，拿到对应的id
        if (cookieIds != null){
            idsArrCookie = cookieIds.split(",");
        }
        // 创建一个集合，装int类型 interger类型，为了传递后台进行id查询
        List<Integer> list = new ArrayList<Integer>();
        // 定义往前端返回的cookie
        cookieIds = "";
        // 判断id的数组不为null
        if (idsArr != null){
            // 遍历数组，字符串的id转换为interger的值
            for (String s : idsArr){
                // 利用包装类的构造器
                list.add(new Integer(s));
                cookieIds = cookieIds + s +",";
            }
            if (idsArrCookie != null && !"".equals(idsArrCookie)){
                for (String s : idsArrCookie){
                    Integer sid = new Integer(s);
                    boolean exists = false;
                    for (Integer i : list){
                        if (sid.equals(i)){
                            exists = true;
                            break;
                        }
                    }
                    if (!exists){
                        list.add(sid);
                        cookieIds = cookieIds + s + ",";
                    }
                }
            }
        }
        // 查询返回
        // 通过集合里的sid的值查询所有要添加到播放列表的歌曲
        List<Song> songs = songService.selectSongBySids(list);
        cookieIds = URLEncoder.encode(cookieIds,"UTF-8");
        Cookie cookie = new Cookie("playids",cookieIds);
        // cookie的访问有效路径
        cookie.setPath("/");
        // cookie的有效时间
        cookie.setMaxAge(60*60*24*30);
        response.addCookie(cookie);
        model.addAttribute("songs",songs);
        return "player";
    }


//    @RequestMapping("/play")
//    public String play(String ids, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
//        List<String> idList = new ArrayList<>();
//
//        if(ids != null && !"".equals(ids)){
//            String[] idArr = ids.split(",");
//            for (String s : idArr) {
//                idList.add(s);
//            }
//        }
//
//        String pids = "";
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null && cookies.length > 0){
//            for (Cookie cookie : cookies) {
//                String name = cookie.getName();
//                if("playids".equals(name)){
//                    pids = URLDecoder.decode(cookie.getValue(),"UTF-8");
//
//                }
//            }
//        }
//
//        String[] oldArr = pids.split(",");
//        for (int i = 0; i < oldArr.length; i++) {
//            if(!idList.contains(oldArr[i])){
//                idList.add(oldArr[i]);
//            }
//        }
//
//        List<Integer> idsList = new ArrayList<>();
//        String playids = "";
//        for (String s : idList) {
//            if(!"".equals(s)){
//                idsList.add(new Integer(s));
//                playids = playids + s+",";
//            }
//
//        }
//
//        List<Song> songs = new ArrayList<>();
//        if(idsList.size() > 0)
//            songs = songService.selectSongBySids(idsList);
//
//
//        model.addAttribute("songs",songs);
//        System.out.println(playids);
//        playids = URLEncoder.encode(playids, "UTF-8");
//        Cookie cookie = new Cookie("playids", playids);
//        cookie.setMaxAge(60*60*24*30);
//        cookie.setPath("/");
//
//        response.addCookie(cookie);
//        return "player";
//    }


    @ResponseBody
    @RequestMapping("/getSong")
    public Song getSong(Integer sid){
        Song song = songService.getSong(sid);
        return song;
    }

}
