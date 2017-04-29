package org.seckill.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by DIY on 2017/4/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDAOTest {

    @Resource
    private SuccessKilledDAO successKilledDAO;

    @Test
    public void insertSuccessKilled() throws Exception {
        long userPhone = 18773238725L;
        int insertCount = successKilledDAO.insertSuccessKilled(1000L,userPhone);
	System.out.println("insertCount: "+ insertCount);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long userPhone = 18773238725L;
	SuccessKilled successKilled = successKilledDAO.queryByIdWithSeckill(1000L,userPhone);
	System.out.println("successKilled:  "+ successKilled );
	System.out.println(successKilled.getSeckill());
    }

}
