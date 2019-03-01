package top.forcebing.borrowhome.userend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:46
 **/

@Data
@Entity
public class ShoppingCar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long goodsDetailId; //具体的某个商品
    private long goodsId;
    private long storeId;
    private String userId;
    private int count;

}