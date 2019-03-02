package top.forcebing.borrowhome.userend.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.forcebing.borrowhome.common.utils.JwtTokenUtil;
import top.forcebing.borrowhome.common.utils.ResponseBean;
import top.forcebing.borrowhome.shopend.repository.GoodsDetailsRepository;
import top.forcebing.borrowhome.shopend.repository.GoodsRepository;
import top.forcebing.borrowhome.userend.model.ShoppingCar;
import top.forcebing.borrowhome.userend.repository.ShoppingCarRepository;

import java.util.List;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/2  14:26
 **/
@RequestMapping("/car")
@Slf4j
@RestController
@Api(value = "购物车", tags = "note")
public class ShoppingCarController {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsDetailsRepository detailsRepository;
    @Autowired
    private ShoppingCarRepository shoppingCarRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/addOne")
    public Object addOne(@RequestParam String Authorization, @RequestParam Long goodsDetailsId, @RequestParam Long goodsId, @RequestParam Long storeId, @RequestParam Integer count) {
        ShoppingCar shoppingCar = new ShoppingCar();
        shoppingCar.setGoodsId(goodsId);
        shoppingCar.setGoodsDetailId(goodsDetailsId);
        shoppingCar.setCount(count);
        shoppingCar.setUserId(jwtTokenUtil.getUserIdFromToken(Authorization));
        shoppingCar.setStoreId(storeId);
        shoppingCarRepository.save(shoppingCar);
        // INFO  2019/3/2 15:58 liliangbin  加入购物车后记得购买后删除
        return ResponseBean.success();
    }

    @DeleteMapping("/delete")
    public Object deleteOne(@RequestParam String Authorization, @RequestParam Long carId) {
        shoppingCarRepository.deleteById(carId);
        return ResponseBean.success();

    }

    @GetMapping("/findAll")
    public Object getAll(@RequestParam String Authorization) {

        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);
        List<ShoppingCar> shoppingCars = shoppingCarRepository.findByUserId(userId);

        return ResponseBean.success(shoppingCars);
    }

}
