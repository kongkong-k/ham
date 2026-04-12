package cn.tx.controller;

import cn.tx.model.Mtype;
import cn.tx.query.MtypeQuery;
import cn.tx.service.MtypeService;
import cn.tx.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 流派模块的表现层实现类
 */
@Controller
@RequestMapping("/mtype")
public class MtypeController {

    @Autowired
    private MtypeService mtypeService;

    /**
     * 分页条件查询所有数据
     * 1.每页数据的数量 pageSize 已知
     * 2.页码 pageNum  已知
     * 3.要查看的当前页数据集合 List<Mtype>
     * 4.开始行号 startNum  = (pageNum - 1) * pageSize
     * 5.总页数 totalPage
     * 6.数据总页数 totalCount
     * @return
     */
    @RequestMapping("/list")
    public String mytypeList(MtypeQuery Query, Model model){

        Page<Mtype> page = mtypeService.selectByPage(Query);
        model.addAttribute("page",page);
        model.addAttribute("mq",Query);
        return "mtype";
    }

    @ResponseBody
    @PostMapping("/addMtype")
    public String addMtype(Mtype mt){
        mtypeService.insert(mt);
        return "success";
    }

    @ResponseBody
    @PostMapping("/delMtype")
    public String delMtype(int tid){
        int i = mtypeService.deleteByPrimaryKey(tid);
        return "success";
    }

    /**
     * 流派修改回显数据方法
     * @return
     */
    @ResponseBody
    @PostMapping("/getMtype")
    public Mtype getMtype(int tid){
        Mtype mtype = mtypeService.selectByPrimaryKey(tid);
        return mtype;
    }

    /**
     * 流派修改方法
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/updateMtype")
    public String updateMtype(Mtype mtype){
        int i = mtypeService.updateByPrimaryKey(mtype);
        return "success";
    }

}
