package com.secondkill.system.goods.service;

import com.rabbitmq.client.Channel;
import com.secondkill.api.goods.entry.TbMsGoods;
import com.secondkill.message.config.StockQueueConfig;
import com.secondkill.system.goods.mapper.TbGoodsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


/**
 * 消费mq扣减库存服务
 * @author choy
 * @date 2021/03/30
 */
@Service
public class StockMqService{

    private final static Logger logger = LoggerFactory.getLogger(StockMqService.class);

    @Autowired
    private TbGoodsMapper goodsMapper;

    /**
     * 消费mq扣减库存
     * @param msGoodsId
     * @return
     */
    @RabbitListener(queues = StockQueueConfig.STOCK_QUEUE_NAME)
    @RabbitHandler
    @Transactional
    public void updateStock(Channel channel, Message message, Integer msGoodsId)  {
        // 获取秒杀活动，从中获取商品id
        TbMsGoods msGoodsById = goodsMapper.getMsGoodsById(msGoodsId);
        Integer goodsId = msGoodsById.getTbGoods().getGoodsId();
        if (goodsMapper.updateMsGoodsStock(msGoodsId) == 1){
            if(goodsMapper.updateGoodsStock(goodsId) == 1){
                logger.info("秒杀活动：{}, 商品：{}, 库存扣减成功", msGoodsId, goodsId);
                return;
            }
        }
        logger.info("秒杀活动：{}, 商品：{}, 库存扣减失败", msGoodsId, goodsId);
        // 触发回滚
        throw new RuntimeException();
    }

}
