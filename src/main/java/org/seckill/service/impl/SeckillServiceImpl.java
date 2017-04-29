package org.seckill.service.impl;

import org.seckill.dao.SeckillDAO;
import org.seckill.dao.SuccessKilledDAO;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.exception.SeckillRepeatException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by DIY on 2017/4/27.
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SeckillDAO seckillDAO;
    @Resource
    private SuccessKilledDAO successKilledDAO;
	//盐值字符串  混淆md5
    private final String salt="anvaovnavnavsasnfaosfn";

    public List<Seckill> queryAll() {
	return seckillDAO.queryAll(0,4);
    }

    public Seckill queryById(long seckillId) {
	return seckillDAO.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
	Seckill seckill = seckillDAO.queryById(seckillId);
	if(seckill == null){
	    return new Exposer(false,seckillId);
	}
	Date nowTime = new Date();
	Date startTime = seckill.getStartTime();
	Date endTime = seckill.getEndTime();
	if(nowTime.getTime()<startTime.getTime()
		|| nowTime.getTime()>endTime.getTime()){
	    return new Exposer(false,seckillId,nowTime.getTime(),
		    startTime.getTime(),endTime.getTime());
	}
	String md5 = getMD5(seckillId);
	return new Exposer(true,md5,seckillId);
    }

    private String getMD5(long seckillId){
        String base = seckillId+"/"+salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
	    throws SeckillException, SeckillRepeatException, SeckillCloseException {
	if(md5 ==null || !md5.equals(getMD5(seckillId))){
	    throw new SeckillException("seckill is rewrited");
	}
	Date nowTime = new Date();
	try{
	    //减库存
	    int updateCount = seckillDAO.reduceNumber(seckillId,nowTime);
	    if(updateCount<=0){
		throw new SeckillCloseException("seckill is closed");
	    }else{
		int insertCount = successKilledDAO.insertSuccessKilled(seckillId,userPhone);
		if(insertCount<=0){
		    throw new SeckillRepeatException("seckill repeated");
		}else {
		    SuccessKilled successKilled = successKilledDAO.queryByIdWithSeckill(seckillId,userPhone);
		    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS,successKilled);
		}
	    }
	}catch (SeckillCloseException e1){
	    throw e1;
	}catch (SeckillRepeatException e2){
	    throw e2;
	}catch (Exception e){
		logger.error(e.getMessage(),e);
		//所有编译期异常，转换为运行期异常
		throw new SeckillException("seckill inner error:"+e.getMessage());
	}
    }
}















