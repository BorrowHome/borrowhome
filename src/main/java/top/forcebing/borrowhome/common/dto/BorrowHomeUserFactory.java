package top.forcebing.borrowhome.common.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.forcebing.borrowhome.common.model.SysRole;
import top.forcebing.borrowhome.common.model.UserInfo;
import top.forcebing.borrowhome.common.repository.SysRoleRepository;
import top.forcebing.borrowhome.common.repository.UserInfoRepository;
import top.forcebing.borrowhome.wxapi.databack.LoginDataBack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  12:08
 **/
@Service
public class BorrowHomeUserFactory {


    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private SysRoleRepository sysRoleRepository;

    public Long createUser(LoginDataBack loginDataBack) {

        String openId = loginDataBack.openid;
        UserInfo userInfo = userInfoRepository.findByOpenId(openId);

        if (userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setOpenId(loginDataBack.openid);
            userInfo.setUnionId(loginDataBack.unionid);
            SysRole sysRole = sysRoleRepository.findByRole("ROLE_COMMON");//我么提前写好数据库

            List<SysRole> sysRoles = new ArrayList<>();
            sysRoles.add(sysRole);
            userInfo.setRoleList(sysRoles);
            userInfoRepository.save(userInfo);
        }
        return userInfo.getId();
    }
}
