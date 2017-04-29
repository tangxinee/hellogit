package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.exception.SeckillRepeatException;

import java.util.List;

/**
 * Created by DIY on 2017/4/27.
 */
public interface SeckillService {

    List<Seckill> queryAll();

    Seckill queryById(long seckillId);

    Exposer exportSeckillUrl(long seckill);

    SeckillExecution executeSeckill(long seckill, long userPhone, String md5)
	    throws SeckillException,SeckillRepeatException,SeckillCloseException;

}
