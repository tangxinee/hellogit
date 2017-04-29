package org.seckill.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillRepeatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by DIY on 2017/4/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void queryAll() throws Exception {
        List<Seckill> list = seckillService.queryAll();
        logger.info("list="+list);
    }

    @Test
    public void queryById() throws Exception {
        long id = 1000L;
        Seckill seckill = seckillService.queryById(id);
        logger.info("seckill:"+seckill);
    }

    @Test
    public void testSeckillLogin() throws Exception {
        long id = 1001L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if(exposer.isExposed()){
            long userPhone = 18773238712L;
            String md5 = exposer.getMd5();
            try{
                SeckillExecution seckillExecution = seckillService.executeSeckill(id,userPhone,md5);
                logger.info("result={}",seckillExecution);
            }catch ( SeckillRepeatException e){
                logger.error(e.getMessage());
            }catch (SeckillCloseException e){
                logger.error(e.getMessage());
            }
        }else {
            logger.warn("exposer={}",exposer);
        }
    }

}







