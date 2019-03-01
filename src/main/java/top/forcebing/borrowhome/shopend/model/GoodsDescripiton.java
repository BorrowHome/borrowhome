package top.forcebing.borrowhome.shopend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:29
 * @description 一个商品的描述
 **/
@Data
@Entity
public class GoodsDescripiton {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    // TODO: 2019/3/1 这个地方的描述用于商品详情页的表示

    private String picture1;
    private String picture2;
    private String picture3;
    private String picture4;
    private long goodsId;

}
