package top.forcebing.borrowhome.common.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/11/8  23:04
 */
@Entity
@Data
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;//编号
    private String role; //角色标识程序中判断使用，如"admin"这个是唯一的
    private String description;//角色描述，Ui展示界面使用
    private Boolean available = Boolean.FALSE;//是否可用，如果不能用就不会添加给用户。
    //角色----权限关系 。多对多的关系。

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<SysPermission> permissions;

    //用户--角色关系定义
    @ManyToMany(mappedBy = "roleList",fetch = FetchType.LAZY)
    private List<UserInfo> userInfos;


}
