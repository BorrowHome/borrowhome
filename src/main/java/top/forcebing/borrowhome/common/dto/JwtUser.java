package top.forcebing.borrowhome.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/11/8  23:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtUser {

    private String username;
    private String userId;

}
