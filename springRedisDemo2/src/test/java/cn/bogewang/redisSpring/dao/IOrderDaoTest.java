package cn.bogewang.redisSpring.dao;

import cn.bogewang.redisSpring.entity.Order;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.Date;

/**
 * Created by bogewang on 2017/3/24.
 */
@ContextConfiguration("classpath*:applicationContext.xml")
public class IOrderDaoTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private IOrderDao orderDao;

    @Test
    public void save() throws Exception {
        Order order = new Order();
        order.setId("1001");
        order.setCreatDate(new Date());
        order.setOrderNo("1001");
        order.setPrice(10001.01);
        orderDao.save(order);
    }

    @Test
    public void read() throws Exception {
        Order result = orderDao.read("1001");
        System.out.println(result);
    }

    @Test
    public void delete() throws Exception {
        boolean result = orderDao.delete("1001");
        System.out.println("执行删除："+result);
        System.out.println(orderDao.read("1001"));
    }

}