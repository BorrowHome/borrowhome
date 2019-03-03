package top.forcebing.borrowhome.shopend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:03
 **/
@Data
@Entity
public class Store implements Serializable {

    private static final long serialVersionUID = 9182265363009205905L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String storeName;
    private String storeHeading; //店的头像。。

    private String storeBanner;
    private String adminUserId; //在授权过后的数据那边收录

    private String classification;
    public enum CheckStauts {Pass, NotPass}

    private CheckStauts checking; //审核商家的营业执照默认的时候是等于零的。  通过为1，准备为2 ,//enum  使用枚举来搞定
    private String businessLicence;//营业执照
    private String IDCard;

    private Date createTime;

}
