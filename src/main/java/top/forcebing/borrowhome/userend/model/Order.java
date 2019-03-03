package top.forcebing.borrowhome.userend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:47
 *
 **/
@Entity
@Data
@Table(name = "borrow_home_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private long userId;

    private String userLocation; //具体的坐标值经纬度

    private long agencyGoodsDetailsId; //
    private long agencyGoodsId;
    private long storeId;
    private String remarks;//备注

    private int count; //我买了几件衣服 。。
    private double amount; //total amount
    private String orderStatus;//create  preparingpay 支付宝页面那一块  待付款 待发货 待收货  订单完成  退款/售后（衣服店是否是没有售后这一说的）

    private String outTradeNo;//支付宝交易号，我们自己生成。
    private String tradeNo; //支付宝异步生成的交易号，异步发送给我们。


    private Date createTime;
    private Date payTime;
    private Date notifyTime;

    private String userTel;
}

// INFO  2019/3/2 18:07 liliangbin  这个类默认的order 对象有点问题，应该是和关键词冲突了
