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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/songer")
public class SongerController {

    @Autowired
    private MtypeService mtypeService;
    @Autowired
    private SongerService songerService;

    @RequestMapping("/dofindAll")
    public String list(SongerQuery mq, Model model){
        // 查询所有流派信息
        // 查询所有歌手信息
        if(mq.getPageNum() == null){
            mq.setPageNum(1);
        }
//        mq.setPageSize(10);
        //查询流派
        List<Mtype> mtypes = mtypeService.selectAll();
        //查询分页的数据
        Page<Songer> page = songerService.selectByPage(mq);
        List<Songer> list = page.getList();
        List<List<Songer>> sList = new ArrayList<>();
        List<Songer> subList = null;
        for (int i = 0; i < list.size(); i++){
            if(i%5 == 0){
                subList = new ArrayList<>();
                sList.add(subList);
            }
            Songer songer = list.get(i);
            subList.add(songer);
        }

        //把page对象发给页面
        model.addAttribute("page", page);
        model.addAttribute("mtypes", mtypes);
        model.addAttribute("sList", sList);

        //把查询条件也要回显
        model.addAttribute("mq", mq);

        return "songers";
    }


    /**
     * 获取歌手的详细信息展示
     * @param srId
     * @param model
     * @return
     */
    @RequestMapping("/getSonger")
    public String getSonger(Integer srId,Model model){
        // 通过查询数据拿到一个多表返回的结果 自己封装map
        Songer songer = songerService.getSongerSong(srId);
        // 返回给前端
        model.addAttribute("songer",songer);
        return "songer";
    }


}
