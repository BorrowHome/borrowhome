package top.forcebing.borrowhome.userend.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/2  14:29
 **/
@Entity
@Data
public class Note {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String userId;
    @Column(columnDefinition = "text")
    private String content;
    private String img1;
    private String img2;
    private String img3;

    private long goodsId1;
    private long goodsId2;
    private long goodsId3;

}
