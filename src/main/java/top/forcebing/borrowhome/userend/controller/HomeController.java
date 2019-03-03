package top.forcebing.borrowhome.userend.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.forcebing.borrowhome.common.utils.ResponseBean;
import top.forcebing.borrowhome.shopend.model.Goods;
import top.forcebing.borrowhome.shopend.model.GoodsDescription;
import top.forcebing.borrowhome.shopend.model.GoodsDetails;
import top.forcebing.borrowhome.shopend.repository.GoodsDescriptionRepository;
import top.forcebing.borrowhome.shopend.repository.GoodsDetailsRepository;
import top.forcebing.borrowhome.shopend.repository.GoodsRepository;
import top.forcebing.borrowhome.userend.model.AgencyGoods;
import top.forcebing.borrowhome.userend.model.AgencyGoodsDetails;
import top.forcebing.borrowhome.userend.repository.AgencyGoodsDetailsRepository;
import top.forcebing.borrowhome.userend.repository.AgencyGoodsRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/2  14:23
 **/
@RestController
@RequestMapping("/home")
@Slf4j
@Api(value = "客户端主页", tags = "note")
public class HomeController {

    @Autowired
    private AgencyGoodsRepository agencyGoodsRepository;

    @Autowired
    private GoodsDescriptionRepository goodsDescriptionRepository;

    @Autowired
    private AgencyGoodsDetailsRepository agencyGoodsDetailsRepository;

    @GetMapping("/")
    public Object getRandom(@RequestParam String classification) {

        List<AgencyGoods> goods = agencyGoodsRepository.findByRandom(classification);
        return ResponseBean.success(goods);
    }


    @GetMapping("/getOne")
    public Object getOne(@RequestParam Long agencyGoodsId) {

        Map<String, Object> goodsDetailsWithString = new HashMap<>();
        AgencyGoods agencyGoods = agencyGoodsRepository.findById(agencyGoodsId).get();

        List<AgencyGoodsDetails> goodsDetails = agencyGoodsDetailsRepository.findByAgencyGoodsId(agencyGoodsId);

        GoodsDescription goodsDescription = goodsDescriptionRepository.findByGoodsId(agencyGoods.getGoodsId());

        goodsDetailsWithString.put("goodsDetails", goodsDetails);
        goodsDetailsWithString.put("goodsDescription", goodsDescription);
        // INFO  2019/3/3 15:56 liliangbin  返回当前代理商得数据。
        return ResponseBean.success(goodsDetailsWithString, String.valueOf(agencyGoods.getAgencyId()));

    }

}
