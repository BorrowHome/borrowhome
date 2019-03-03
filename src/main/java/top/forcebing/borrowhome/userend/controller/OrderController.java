package top.forcebing.borrowhome.userend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.forcebing.borrowhome.common.utils.JwtTokenUtil;
import top.forcebing.borrowhome.userend.model.AgencyGoods;
import top.forcebing.borrowhome.userend.model.Order;
import top.forcebing.borrowhome.userend.model.ShoppingCar;
import top.forcebing.borrowhome.userend.repository.AgencyGoodsRepository;
import top.forcebing.borrowhome.userend.repository.OrderRepository;
import top.forcebing.borrowhome.userend.repository.ShoppingCarRepository;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/3  16:16
 **/
@RequestMapping("/order")
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AgencyGoodsRepository agencyGoodsRepository;

    @Autowired
    private ShoppingCarRepository shoppingCarRepository;

    @PostMapping("/create")
    public Object getAll(@RequestParam String Authorization,
                         @RequestParam long shoppingCarId,
                         @RequestParam String userTel,
                         @RequestParam String userLocation,
                         @RequestParam String remark) {

        String useId = jwtTokenUtil.getUserIdFromToken(Authorization);

        ShoppingCar shoppingCar = shoppingCarRepository.findById(shoppingCarId).get();

        AgencyGoods agencyGoods = agencyGoodsRepository.findById(shoppingCar.getAgencyGoodsId()).get();
        Order order = new Order();
        order.setAmount(shoppingCar.getCount() * agencyGoods.getAgencyId());
    }
}
