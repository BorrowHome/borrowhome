package top.forcebing.borrowhome.userend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:42
 **/
@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date createTime;
    private long userId;
    private long goodsId;

    private String commentType;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "Text")
    private String contentText;


    private String picPath1;
    private String picPath2;
    private String picPath3;
    private String picPath4;
    private String picPath5;

}
