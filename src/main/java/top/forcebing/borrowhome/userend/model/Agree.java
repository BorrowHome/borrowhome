package top.forcebing.borrowhome.userend.model;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/2  16:46
 **/
@Data
@Entity

public class Agree {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long noteId;

    private String userId;

}
