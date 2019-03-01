package top.forcebing.borrowhome.userend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:49
 **/
@Data
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date createTime;
    private String messageDetails;
    private String toSomeOne;//对应的是用户的电话号码
    private String fromSomeOne;
    @Column(columnDefinition = "enum('1','2','3'")
    private String textType;//enum类型。对应的是聊天内容，图片还是普通文字，还是图片链接。
    @Column(columnDefinition = "enum('point2point','broadcast')")
    private String chatType;
}
