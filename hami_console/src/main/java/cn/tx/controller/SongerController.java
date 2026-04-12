package cn.tx.controller;

import cn.tx.model.Mtype;
import cn.tx.model.Songer;
import cn.tx.query.SongerQuery;
import cn.tx.service.MtypeService;
import cn.tx.service.SongerService;
import cn.tx.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 歌手模块的表现层实现类
 */
@Controller
@RequestMapping("/songer")
public class SongerController {

    @Autowired
    private SongerService songerService;

    @Autowired
    private MtypeService mtypeService; // 仿照流派控制器，注入流派服务

    /**
     * 分页条件查询所有歌手数据
     * 仿照MtypeController的list方法写法
     */
    @RequestMapping("list")
    public String list(SongerQuery mq, Model model){
        if(mq.getPageNum() == null){
            mq.setPageNum(1);
        }
        //查询分页的数据
        Page<Songer> page = songerService.selectByPage(mq);
        //把page对象发给页面
        model.addAttribute("page", page);
        //把查询条件也要回显
        model.addAttribute("mq", mq);
        //查询流派
        List<Mtype> mtypes = mtypeService.selectAll();
        model.addAttribute("mtypes", mtypes);
        return "songer";

//        if(songerQuery.getPageNum()==null){
//            songerQuery.setPageNum(1);
//        }
//
//        Page<Songer> page = songerService.selectByPage(songerQuery);
//        model.addAttribute("page",page);
//        model.addAttribute("mq",songerQuery);
//        return "songer";

    }

    /**
     * 新增歌手
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        List<Mtype> mtypeList = mtypeService.selectAll();
        model.addAttribute("mtypes",mtypeList);
        return "addSonger";
    }

    @RequestMapping("/add")
    public String add(Songer songer){
        songerService.insert(songer);
        return "redirect:list";
    }

    /**
     * 删除歌手
     */
    @ResponseBody
    @RequestMapping("/delSonger")
    public String delSonger(Integer srid){
        Songer songer = songerService.selectByPrimaryKey(srid);
//        Client client = Client.create();
//        String filePath="http://localhost:808";
//        WebResource resource = client.resource(filePath+songer.getPic());
//        resource.delete();
        int res = songerService.deleteByPrimaryKey(srid);
        if (res == 0){
            return "error";
        }
        return "success";
    }

    /**
     * 歌手修改方法
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer srid,Model model){
        Songer songer = songerService.selectByPrimaryKey(srid);
        model.addAttribute("songer",songer);
        //查询流派
        List<Mtype> mtypes = mtypeService.selectAll();
        model.addAttribute("mtypes", mtypes);
        model.addAttribute("filePath", "http://localhost:8085");
        return "updateSonger";
    }

    @RequestMapping("/update")
    public String updateSonger(Songer songer){
        songerService.updateByPrimaryKey(songer);
        return "redirect:list";
    }


}