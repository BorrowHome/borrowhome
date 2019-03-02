package top.forcebing.borrowhome.common.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

/**
 * @ Author     ：hantiaotiao.
 * @ Date       ：Created in 19-2-25 下午7:09
 * @ Description：
 */
@Entity
public class MoneyTransPortModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double money;
    private long userId;

    private String aliPayId; //支付宝账号

    private String payEmail;
    private String orderId;  //自己生成的id
    private String backOrderId; //支付宝返回id

    public enum type {toDriver, toShop, fromConsumer}

    private type orderType;
    private Date createTime;
    private Date doneTime;


}
