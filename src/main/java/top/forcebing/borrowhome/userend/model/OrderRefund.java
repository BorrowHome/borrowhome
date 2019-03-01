package top.forcebing.borrowhome.userend.model;

import lombok.Data;
import org.springframework.context.annotation.Description;

import javax.persistence.*;
import java.util.Date;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:50
 **/
@Data
@Entity
@Description(value = "退款或是交换衣服") //不允许交换衣服，只是拍错后从新下单就行
public class OrderRefund {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    private long orderId;
    @Column(nullable = false, columnDefinition = "varchar(20)", unique = true)
    private String userTel;
    private long goodsDetailsId;
    @Column(nullable = false, unique = true)
    private long shopId;
    private long goodsId;
    private boolean getGoods;

    private RefundReason refundReason; //退款说明

    public enum RefundReason {NOT_GET_GOODS, FILL_IN_ERROR, SHOTTING_WRONG, Insufficient_quantity, Personal_reasons_want_to_return}


    private String refundReasonDetails;
    private String image;//上传凭证


    private String refundProcess;//0 买家填写退款信息， 1 卖家处理退款信息

    private Date createTime;
    private Date changeTime;
    private Date commentTime;
    private Date doneTime;
}
