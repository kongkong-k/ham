package cn.tx.service.impl;

import cn.tx.dao.BaseDao;
import cn.tx.service.BaseService;
import cn.tx.util.Page;

import java.lang.reflect.Method;
import java.util.List;

/*
* 调用持久层
* */
public class BaseServiceImpl<T,Q> implements BaseService<T,Q> {

    //使用公用持久层
    //@Autowired  可以 繁琐 相当于注入两次
    // public default 同包：protected private
    protected BaseDao<T,Q> baseDao;

    @Override
    public int deleteByPrimaryKey(Integer tid) {
        return baseDao.deleteByPrimaryKey(tid);
    }

    @Override
    public int insert(T record) {
        return baseDao.insert(record);

    }

    @Override
    public T selectByPrimaryKey(Integer tid) {
        return baseDao.selectByPrimaryKey(tid);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return baseDao.updateByPrimaryKey(record);
    }

    @Override
    public int insertSelective(T record) {
        return baseDao.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return baseDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public Page<T> selectByPage(Q q) {
        //为Page对象封装6个属性
        Page<T> page = new Page<>();
        //拿到泛型q的类对象
        Class<?> qClass = q.getClass();
        //1.利用反射反向拿去该泛型的PageSize和PageNum
        try {
            //获得getPageSize和getPageNum对象
            Method getPageSize = qClass.getMethod("getPageSize", null);
            Method getPageNum = qClass.getMethod("getPageNum", null);

            //反射调用getPageSize和getPageNum方法
            Integer pageSize = (Integer) getPageSize.invoke(q, null);
            Integer pageNum = (Integer) getPageNum.invoke(q, null);

            //属性值
            page.setPageNum(pageNum);
            page.setPageSize(pageSize);
            page.setStartNum((pageNum-1)*pageSize);

            //设置Query对象中的startNum属性值
            Method setStartNum = qClass.getDeclaredMethod("setStartNum", Integer.class);
            setStartNum.invoke(q, (pageNum - 1) * pageSize);

            //查询结果表
            List<T> list = baseDao.selectByPage(q);
            page.setList(list);

            //查询总记录数
            Integer count = baseDao.selectCount(q);
            page.setTotalCount(count);
            page.setTotalPage(count/pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return page;
    }
}
