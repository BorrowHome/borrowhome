package top.forcebing.borrowhome.shopend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:10
 **/
@Entity
@Data
public class GoodsDetails implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long goodsId;//前面商品的id

    private String color; //颜色分类的图片

    private String colorImage; //和颜色分类相同，只有我们的数据相同的时候才能这样


    private String clothesSize; //衣服的尺寸分类//前端给吧。提前定义好

    private String classification;//衣服的分类由前端分类好分类我们定义，然后返回给前端。后期来改


    private double price;
    private int count;
    private boolean existence;  //这个分类现在是否还存在

}
