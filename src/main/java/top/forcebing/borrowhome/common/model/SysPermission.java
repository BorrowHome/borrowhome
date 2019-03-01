package top.forcebing.borrowhome.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/11/8  23:02
 */

@Entity
@Data
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @Column(columnDefinition = "enum('menu','button')")
    private String resourceType;//资源类型[menu|button]

    private String url; //没看懂有什么用
    private String permission;//权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view

    private long parentId; //父编号

    private String parentIds; //父编号列表

    private Boolean available = Boolean.FALSE;//判断是否可用

    @ManyToMany(mappedBy = "permissions",fetch = FetchType.LAZY)
    private List<SysRole> roles;


    @JsonIgnore
    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }


}
