package cn.tx;

import cn.tx.model.Mtype;
import cn.tx.service.MtypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Mtypetest {

    @Autowired
    MtypeService mtypeService;

    @Test
    public void testAdd(){
        Mtype s = new Mtype();
        s.setTdesc("很好");
        s.setTname("阳哥");
        mtypeService.insert(s);
    }

    @Test
    public void getById(){
        Mtype m = mtypeService.selectByPrimaryKey(1);
        System.out.println(m);
    }
}