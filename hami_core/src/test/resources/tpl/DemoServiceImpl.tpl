package cn.tx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tx.dao.DemoMapper;
import cn.tx.model.Demo;
import cn.tx.query.DemoQuery;
import cn.tx.service.DemoService;
@Service
public class DemoServiceImpl extends BaseServiceImpl<Demo> implements DemoService {


   private DemoMapper demoDao;

   @Autowired
   public void setDemoDao(DemoMapper demoDao) {
      this.demoDao = demoDao;
      this.baseDao = demoDao;
   }

}