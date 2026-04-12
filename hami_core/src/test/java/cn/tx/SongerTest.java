package cn.tx;

import cn.tx.model.Songer;
import cn.tx.service.SongerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SongerTest {

    @Autowired
    SongerService songerService;

    @Test
    public void testAdd(){
        Songer a = new Songer();
        a.setArea("北京");
        a.setIntro("很好");
        a.setIsHot(1);
        a.setPic("zz");
        a.setSrname("阳哥");
        songerService.insert(a);
    }

    @Test
    public void getById(){
        Songer songer = songerService.selectByPrimaryKey(1);
        System.out.println(songer);
    }

    @Test
    public void updateSonger(){
        Songer songer = songerService.selectByPrimaryKey(1);
        songer.setSrname("最亮的仔");
        songerService.updateByPrimaryKeySelective(songer);
    }

    @Test
    public void delSongerById(){
        songerService.deleteByPrimaryKey(1);
    }
}
