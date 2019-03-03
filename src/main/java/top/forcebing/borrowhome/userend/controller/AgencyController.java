package top.forcebing.borrowhome.userend.controller;

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
import top.forcebing.borrowhome.shopend.model.Goods;
import top.forcebing.borrowhome.shopend.model.GoodsDetails;
import top.forcebing.borrowhome.shopend.model.Store;
import top.forcebing.borrowhome.shopend.repository.GoodsDetailsRepository;
import top.forcebing.borrowhome.shopend.repository.GoodsRepository;
import top.forcebing.borrowhome.shopend.repository.StoreRepository;
import top.forcebing.borrowhome.userend.model.AgencyGoods;
import top.forcebing.borrowhome.userend.model.AgencyGoodsDetails;
import top.forcebing.borrowhome.userend.model.Sign;
import top.forcebing.borrowhome.userend.repository.AgencyGoodsDetailsRepository;
import top.forcebing.borrowhome.userend.repository.AgencyGoodsRepository;
import top.forcebing.borrowhome.userend.repository.SignRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/3  14:24
 **/
@RestController
@RequestMapping("/agent")
@Slf4j
public class AgencyController {


    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private SysRoleRepository sysRoleRepository;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private GoodsDetailsRepository goodsDetailsRepository;

    @Autowired
    private SignRepository signRepository;
    @Autowired
    private AgencyGoodsRepository agencyGoodsRepository;

    @Autowired
    private AgencyGoodsDetailsRepository agencyGoodsDetailsRepository;

    @GetMapping("/")
    public Object create(@RequestParam String Authorization, @RequestParam String IDCard) {

        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);
        UserInfo userInfo = userInfoRepository.findById(Long.valueOf(userId)).get();

        userInfo.setIDCard(IDCard);
        userInfo.setAgency(true);
        List<SysRole> roles = new ArrayList<>();
        roles = userInfo.getRoleList();

        // INFO  2019/3/3 14:33 liliangbin   ROLE_AGENCY
        SysRole sysRole = sysRoleRepository.findByRole("ROLE_AGENCY");

        roles.add(sysRole);
        userInfo.setRoleList(roles);
        userInfoRepository.save(userInfo);
        return ResponseBean.success();
    }


    @GetMapping("/find")
    @ApiOperation(value = "根据分类拿到店铺名字")
    public Object getStore(@RequestParam String Authorization, @RequestParam String classification) {

        List<Store> stores = storeRepository.findByClassification(classification);

        return ResponseBean.success(stores);
    }

    @GetMapping("/getGoods")
    public Object getGoods(@RequestParam Long storeId) {

        return ResponseBean.success(goodsRepository.findByStoreId(storeId));
    }

    @GetMapping("/getGoodsDetails")
    public Object getDetails(@RequestParam long goodsId) {
        return ResponseBean.success(goodsDetailsRepository.findByGoodsId(goodsId));
    }

    @PostMapping("/sign")
    public Object sign(@RequestParam String Authorization, @RequestParam Long storeId) {
        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);
        Sign sign = new Sign();

        sign.setStoreId(storeId);
        sign.setUserId(Long.valueOf(userId));
        signRepository.save(sign);


        List<Goods> good = goodsRepository.findByStoreId(storeId);
        for (Goods goods1 : good) {

            AgencyGoods agencyGoods = new AgencyGoods(goods1);
            agencyGoods.setCreateTime(new Date());
            agencyGoods.setAgencyId(Long.valueOf(userId));
            agencyGoods.setGoodsId(goods1.getId());
            agencyGoodsRepository.save(agencyGoods);


            List<GoodsDetails> goodsDetails = goodsDetailsRepository.findByGoodsId(goods1.getId());
            for (GoodsDetails goodsDetails1 : goodsDetails) {

                AgencyGoodsDetails agencyGoodsDetails = new AgencyGoodsDetails(goodsDetails1);
                agencyGoodsDetails.setAgencyId(Long.valueOf(userId));
                agencyGoodsDetails.setAgencyGoodsId(agencyGoods.getId());

                agencyGoodsDetailsRepository.save(agencyGoodsDetails);
            }


        }

        return ResponseBean.success();
    }

    @DeleteMapping("/deleteSign")
    public Object deleteSign(@RequestParam String Authorization, @RequestParam long storeId) {
        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);
        agencyGoodsRepository.deleteByAgencyIdAndStoreId(Long.valueOf(userId), storeId);
        return ResponseBean.success();

    }

    @PutMapping("/changePrice")
    public Object changePrice(@RequestParam String Authorization, @RequestParam double price, @RequestParam long agencyGoodsDetailsId, @RequestParam long agencyGoodsId) {

        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);
        AgencyGoods agencyGoods = agencyGoodsRepository.findById(agencyGoodsId).get();

        if (Long
                .valueOf(userId) != agencyGoods.getAgencyId()) {
            return ResponseBean.error();
        }

        agencyGoods.setChangeTime(new Date());
        agencyGoods.setShown(true);
        agencyGoodsRepository.save(agencyGoods);

        AgencyGoodsDetails agencyGoodsDetails = agencyGoodsDetailsRepository.findById(agencyGoodsDetailsId).get();
        agencyGoodsDetails.setPrice(price);


        agencyGoodsDetailsRepository.save(agencyGoodsDetails);
        return ResponseBean.success();
    }


    @GetMapping("/showGoods")
    public Object showGoods(@RequestParam String Authorization) {
        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);
        List<AgencyGoods> agencyGoods = agencyGoodsRepository.findByAgencyIdAndShown(Long.valueOf(userId), true);

        return ResponseBean.success(agencyGoods);

    }


    @GetMapping("/getAgencyGoodsDetails")
    public Object getAgencyDetails(@RequestParam long agencyGoodsId) {
        return ResponseBean.success(agencyGoodsDetailsRepository.findByAgencyGoodsId(agencyGoodsId));
    }


}
