package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 *   执行秒杀的时候  需要做的
 *   1  插入秒杀明细
 *   2  查询明细记录并返回商品属性
 */
public interface SuccessKilledDAO {
	//1 插入秒杀明细
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    	//2 查询单个商品秒杀记录并返回商品属性
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

	//增加注释
}
