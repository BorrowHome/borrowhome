package top.forcebing.borrowhome.common.model;

import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/11/8  17:04
 */

@Entity
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final char[] salts = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'o', 'p', 'q'};

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String openId; //当前小程序唯一的id
    private String unionId; // 微信支付id。一个用户多个地方支付后这个id是唯一的。

    private String salt;

    private boolean agency;

    private String IDCard;

    public static char[] getSalts() {
        return salts;
    }


    @ManyToMany(fetch = FetchType.EAGER)//立即重数据库中加载数据。
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "userId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roleList;//一个用户具有多个角色

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRole(SysRole role) {
        List<SysRole> roles = new ArrayList<>();
        roles.add(role);
        this.roleList = roles;

    }

    public UserInfo() {
        this.salt = RandomStringUtils.random(6, salts); //后期把他改为随机生成的数据
    }

    public String getCredentialsSalt() {

        return this.salt;

    }


}
