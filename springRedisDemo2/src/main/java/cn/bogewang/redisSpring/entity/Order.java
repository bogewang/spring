package cn.bogewang.redisSpring.entity;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bogewang on 2017/3/24.
 */
@Repository
public class Order implements Serializable {
    private static final long serialVersionUID = -3775403953496513791L;
    /**
     * 数据库主键：     */
    private String id;

    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 价格
     */
    private double price;
    /**
     * 创建订单日期
     */
    private Date creatDate;

    public Order(String id, String orderNo, double price, Date creatDate) {
        this.id = id;
        this.orderNo = orderNo;
        this.price = price;
        this.creatDate = creatDate;
    }

    public Order() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", price=" + price +
                ", creatDate=" + creatDate +
                '}';
    }
}
