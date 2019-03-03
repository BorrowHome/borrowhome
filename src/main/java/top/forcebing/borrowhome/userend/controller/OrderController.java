package top.forcebing.borrowhome.userend.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.forcebing.borrowhome.common.utils.JwtTokenUtil;
import top.forcebing.borrowhome.common.utils.ResponseBean;
import top.forcebing.borrowhome.userend.model.AgencyGoods;
import top.forcebing.borrowhome.userend.model.AgencyGoodsDetails;
import top.forcebing.borrowhome.userend.model.Order;
import top.forcebing.borrowhome.userend.model.ShoppingCar;
import top.forcebing.borrowhome.userend.repository.AgencyGoodsDetailsRepository;
import top.forcebing.borrowhome.userend.repository.AgencyGoodsRepository;
import top.forcebing.borrowhome.userend.repository.OrderRepository;
import top.forcebing.borrowhome.userend.repository.ShoppingCarRepository;

import java.util.Date;

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
    @Autowired
    private AgencyGoodsDetailsRepository agencyGoodsDetailsRepository;

    @PostMapping("/create")
    public Object getAll(@RequestParam String Authorization,
                         @RequestParam long shoppingCarId,
                         @RequestParam String userTel,
                         @RequestParam String userLocation,
                         @RequestParam String remark) {

        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);

        ShoppingCar shoppingCar = shoppingCarRepository.findById(shoppingCarId).get();


        AgencyGoodsDetails agencyGoodsDetails = agencyGoodsDetailsRepository.findById(shoppingCar.getAgencyGoodsDetailId()).get();

        Order order = new Order();
        order.setAmount(shoppingCar.getCount() * agencyGoodsDetails.getPrice());
        order.setCount(shoppingCar.getCount());
        order.setCreateTime(new Date());
        order.setAgencyGoodsDetailsId(shoppingCar.getAgencyGoodsDetailId());
        order.setAgencyGoodsId(shoppingCar.getAgencyGoodsId());
        order.setUserId(Long.valueOf(userId));
        order.setStoreId(shoppingCar.getStoreId());
        order.setOrderStatus("1");
        order.setUserLocation(userLocation);
        order.setRemarks(remark);
        order.setUserTel(userTel);
        orderRepository.save(order);

        shoppingCarRepository.delete(shoppingCar);
        // TODO 2019/3/3 19:51 liliangbin 支付问题 我们需要返回给前端得东西。
        return ResponseBean.success();

    }

    @GetMapping("/changeOrderStatus")
    @ApiOperation(value = "订单状态管理  0 为 未付款  1 为已付款待发货 2 为 已发货  3  为已签收")
    public Object changeOrderStatus(@RequestParam String status, @RequestParam long orderId) {


        Order order = orderRepository.findById(orderId).get();

        switch (status) {
            case "0":
            case "1":
            case "2":
            case "3": {
                order.setOrderStatus(status);

                return ResponseBean.success();
            }
            default: {
                return ResponseBean.error();
            }
        }
    }


}
