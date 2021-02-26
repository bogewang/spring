package cn.bogewang.redisSpring.dao;

import cn.bogewang.redisSpring.entity.Order;

/**
 * order的接口，提供order操作的各种方法
 * Created by bogewang on 2017/3/24.
 *
 */
public interface IOrderDao {
    /**
     * 保存订单
     * @param order
     * @return 返回是否保存成功；
     *          成功返回true，
     *          失败返回false；
     */
    boolean save(Order order);

    /**
     * 根据id返回对应Order，如果没有对应的返回null
     * @param id
     * @return
     */
    Order read(String id);

    /**
     * 删除id对应的order
     * @param id
     * @return 删除成功返回true
     *          否则返回false；
     */
    boolean delete(String id);
}
