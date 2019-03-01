package top.forcebing.borrowhome.common.config;

import top.forcebing.borrowhome.common.dto.AccessToken;
import top.forcebing.borrowhome.common.model.SysPermission;
import top.forcebing.borrowhome.common.model.SysRole;
import top.forcebing.borrowhome.common.model.UserInfo;
import top.forcebing.borrowhome.common.repository.UserInfoRepository;
import top.forcebing.borrowhome.common.utils.JwtTokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/11/8  23:09
 */
public class MyRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LogManager.getLogger(MyRealm.class);


    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AccessToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String userId = jwtTokenUtil.getUserIdFromToken(principals.toString());
        System.out.println(principals.toString() + "这是一个传入的参数" + " 应该是等于 admin");
        System.out.println("当需要权限的时候 就会调用我了");
        UserInfo userInfo = userInfoRepository.findById(Long.valueOf(userId)).get();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        System.out.println("用户的名字是====>" + " 用户拥有的是那些角色" + userInfo.getRoleList());

        for (SysRole role : userInfo.getRoleList()) {

            authorizationInfo.addRole(role.getRole());
            for (SysPermission p : role.getPermissions()) {

                authorizationInfo.addStringPermission(p.getPermission());
                System.out.println("这个是该用户的权限=====>" + p.getPermission() + "用户的名字是 ====>" + p.getName());

            }

        }

        //  authorizationInfo.addRole("admin");
        return authorizationInfo;


    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比

        System.out.println("用户登录认证 :" + token);

        String userId = jwtTokenUtil.getUserIdFromToken(token);
        if (userId == null) {
            throw new AuthenticationException("token invalid");
        }

        UserInfo userBean = userInfoRepository.findById(Long.valueOf(userId)).get();
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}