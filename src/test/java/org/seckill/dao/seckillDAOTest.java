package org.seckill.dao;


import org.junit.Test;

import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by DIY on 2017/4/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class seckillDAOTest {

    @Resource
    private SeckillDAO seckillDAO;

    @Test
    public void testReduceNumber() throws Exception {
	Date nowTime = new Date();
	int updateCount =	seckillDAO.reduceNumber(1000L,nowTime);
	System.out.println("updateCount: "+ updateCount);
    }

    @Test
    public void queryById() throws Exception {
        Seckill seckill = seckillDAO.queryById(1000L);
	System.out.println("seckill:"+seckill);
    }

    @Test
    public void queryAll() throws Exception {
	List<Seckill> list = seckillDAO.queryAll(0,100);
	for (Seckill seckill :
		list) {
	    System.out.println("seckill:"+seckill);
	}
    }

}
