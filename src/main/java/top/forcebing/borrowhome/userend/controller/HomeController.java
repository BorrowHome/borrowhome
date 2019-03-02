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
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsDescriptionRepository goodsDescriptionRepository;

    @Autowired
    private GoodsDetailsRepository goodsDetailsRepository;

    @GetMapping("/")
    public Object getRandom(@RequestParam String classification) {

        List<Goods> goods = goodsRepository.findByRandom(classification);
        return ResponseBean.success(goods);
    }


    @GetMapping("/getOne")
    public Object getOne(@RequestParam Long goodsId) {

        Map<String, Object> goodsDetailsWithString = new HashMap<>();

        List<GoodsDetails> goodsDetails = goodsDetailsRepository.findByGoodsId(goodsId);
        GoodsDescription goodsDescription = goodsDescriptionRepository.findByGoodsId(goodsId);

        goodsDetailsWithString.put("goodsDetails", goodsDetails);
        goodsDetailsWithString.put("goodsDescription", goodsDescription);
        return ResponseBean.success(goodsDetailsWithString);

    }

}
