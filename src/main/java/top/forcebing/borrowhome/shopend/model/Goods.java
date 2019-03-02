package top.forcebing.borrowhome.shopend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:07
 **/
@Entity
@Data
public class Goods implements Serializable {

    private static final long serialVersionUID = 9182265363009205905L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long adminId;
    private long storeId; //店铺的ID
    private String clothesName;//衣服的名字
    private String classification;//衣服的分类
    private String clothesDescription;

    private String previewImg;


}
