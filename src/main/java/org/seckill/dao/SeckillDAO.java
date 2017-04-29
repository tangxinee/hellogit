package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * DAO层是做数据交换  数据访问
 * 执行秒杀操作  就要减库存
 * 根据偏移量查看商品列表
 */
public interface SeckillDAO {

//
    //1  执行秒杀的时候 要减库存  这里要定义减库存的方法
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    //2  通过ID查看秒杀的商品详情
    Seckill queryById(long seckillId);

    //3  通过偏移量 查看商品列表
    List<Seckill>  queryAll (@Param("offset") int offset , @Param("limit") int limit);

}
