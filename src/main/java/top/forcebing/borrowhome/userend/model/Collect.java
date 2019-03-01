package top.forcebing.borrowhome.userend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:49
 **/
@Entity
@Data
public class Collect {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String userTel;
    private CollectType collectionType;  //收藏的是店铺还是衣服店铺为1.衣服为2.收藏的笔记为3
    private long collectionId;  //店铺或是衣服的id；

    public enum CollectType {goods, shop, note}
}
