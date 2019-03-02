package top.forcebing.borrowhome.shopend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.forcebing.borrowhome.common.model.SysRole;
import top.forcebing.borrowhome.common.model.UserInfo;
import top.forcebing.borrowhome.common.repository.SysRoleRepository;
import top.forcebing.borrowhome.common.repository.UserInfoRepository;
import top.forcebing.borrowhome.common.utils.JwtTokenUtil;
import top.forcebing.borrowhome.common.utils.ResponseBean;
import top.forcebing.borrowhome.shopend.model.Store;
import top.forcebing.borrowhome.shopend.repository.StoreRepository;

import java.util.List;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/2  11:57
 **/
@RestController
@RequestMapping("/store")
@Slf4j
@Api(value = "店铺接口", tags = "note")
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private SysRoleRepository sysRoleRepository;

    @GetMapping("/create")
    @ApiOperation(value = "创建一个店铺")
    public Object createStore(@RequestParam String Authorization, @RequestParam String storeName, @RequestParam String storeHeading, @RequestParam(required = false) String storeBanner) {

        // TODO: 2019/3/2 那个banner 我们可以设置一个默认值
        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);
        Store store = new Store();
        store.setStoreName(storeName);
        store.setStoreHeading(storeHeading);
        store.setStoreBanner(storeBanner);
        store.setAdminUserId(userId);
        storeRepository.save(store);
        // TODO: 2019/3/2 给普通用户添加一个商店权限
        UserInfo userInfo = userInfoRepository.findById(Long.valueOf(userId)).get();
        List<SysRole> sysRoles = userInfo.getRoleList();
        SysRole sysRole = sysRoleRepository.findByRole("ROLE_STORE");
        sysRoles.add(sysRole);
        userInfo.setRoleList(sysRoles);
        userInfoRepository.save(userInfo);


        return ResponseBean.success(store.getId(), "storeId");
    }
}
