package cn.bogewang.redisSpring.dao.impl;

import cn.bogewang.redisSpring.dao.IOrderDao;
import cn.bogewang.redisSpring.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * Created by bogewang on 2017/3/24.
 */
public class OrderDaoImpl implements IOrderDao{
    @Autowired
    private RedisTemplate<String ,Order> redisTemplate;

    public boolean save(Order order) {
        try {
            ValueOperations<String, Order> valueOper = redisTemplate.opsForValue();
            valueOper.set(order.getId(),order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Order read(String id) {
        ValueOperations<String, Order> valueOper = redisTemplate.opsForValue();
        return valueOper.get(id);
    }

    public boolean delete(String id) {
        try {
            ValueOperations<String, Order> valueOper = redisTemplate.opsForValue();
            RedisOperations<String, Order> operations = valueOper.getOperations();
            operations.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
