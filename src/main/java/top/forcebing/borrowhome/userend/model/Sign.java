package top.forcebing.borrowhome.userend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/3  14:48
 **/
@Entity
@Data
public class Sign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long userId;
    private long storeId;


}
