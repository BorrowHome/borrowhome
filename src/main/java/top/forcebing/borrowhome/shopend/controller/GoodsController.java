package top.forcebing.borrowhome.shopend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.forcebing.borrowhome.common.utils.JwtTokenUtil;
import top.forcebing.borrowhome.common.utils.ResponseBean;
import top.forcebing.borrowhome.shopend.model.Goods;
import top.forcebing.borrowhome.shopend.model.GoodsDescription;
import top.forcebing.borrowhome.shopend.model.GoodsDetails;
import top.forcebing.borrowhome.shopend.model.Store;
import top.forcebing.borrowhome.shopend.repository.GoodsDescriptionRepository;
import top.forcebing.borrowhome.shopend.repository.GoodsDetailsRepository;
import top.forcebing.borrowhome.shopend.repository.GoodsRepository;
import top.forcebing.borrowhome.shopend.repository.StoreRepository;

import java.util.List;


/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/2  12:07
 **/
@RestController
@RequestMapping("/goods")
@Api(value = "商品处理", tags = "note")
public class GoodsController {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsDetailsRepository detailsRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private GoodsDescriptionRepository goodsDescriptionRepository;

    @PostMapping("/create")
    @ApiOperation(value = "商品建立")
    public Object create(@RequestParam String Authorization, @RequestParam Long storeId, @RequestParam String goodsName,
                         @RequestParam String goodsDescription,
                         @RequestParam String classification,
                         @RequestParam String preview) {
        // INFO  2019/3/2 13:16 liliangbin   校验store 和userId 的关系
        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);

        Store store = storeRepository.findById(storeId).get();

        if (userId != store.getAdminUserId()) {
            return ResponseBean.error("你不是该店的主人");
        }
        // INFO  2019/3/2 13:16 liliangbin  存储大分类
        Goods goods = new Goods();
        goods.setAdminId(Long.valueOf(userId));
        goods.setStoreId(storeId);
        goods.setClassification(classification);
        goods.setClothesName(goodsName);
        goods.setClothesDescription(goodsDescription);
        goods.setPreviewImg(preview);
        goodsRepository.save(goods);
        return ResponseBean.success(goods.getId(), "goodsId");
    }

    @PostMapping("/goodDetails")
    public Object createGoodsDetails(@RequestParam String Authorization, @RequestParam Long storeId,
                                     @RequestParam Long goodsId,
                                     @RequestParam String color,
                                     @RequestParam String colorImg,
                                     @RequestParam String size,
                                     @RequestParam String classification,
                                     @RequestParam double price) {

        // INFO  2019/3/2 13:33 liliangbin  校验

        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);
        Store store = storeRepository.findById(storeId).get();
        if (store.getAdminUserId() != userId) {

            return ResponseBean.error("校验出错");
        }

        // INFO  2019/3/2 13:33 liliangbin  入库
        GoodsDetails goodsDetails = new GoodsDetails();
        goodsDetails.setGoodsId(goodsId);
        goodsDetails.setClassification(classification);
        goodsDetails.setClothesSize(size);
        goodsDetails.setPrice(price);
        goodsDetails.setColor(color);
        goodsDetails.setColorImage(colorImg);
        detailsRepository.save(goodsDetails);
        return ResponseBean.success();
    }

    @GetMapping("/getGoods")
    @ApiOperation(value = "获取商家的所有产品")
    public Object getAllGoods(@RequestParam String Authorization) {
        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);

        List<Goods> goods = goodsRepository.findByAdminId(Long.valueOf(userId));

        return ResponseBean.success(goods);

    }

    @GetMapping("/getGoodsDetails")
    @ApiOperation(value = "获得商家已经商家的产品")
    public Object getGoodsDetails(@RequestParam String Authorization, @RequestParam String goodsId) {

        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);

        List<GoodsDetails> goodsDetails = detailsRepository.findByGoodsId(Long.valueOf(goodsId));

        return ResponseBean.success(goodsDetails);
    }

    @DeleteMapping("/deleteGoodsDetails")
    @ApiOperation("删除某个产品分类")
    public Object deleteGoodsDetails(@RequestParam String Authorization, @RequestParam Long goodsDetailsId) {

        detailsRepository.deleteById(goodsDetailsId);
        return ResponseBean.success();
    }

    @DeleteMapping("/deleteGoods")
    public Object deleteGoods(@RequestParam String Authorization, Long goodsId) {

        goodsRepository.deleteById(goodsId);
        detailsRepository.deleteByGoodsId(goodsId);
        goodsDescriptionRepository.deleteByGoodsId(goodsId);
        return ResponseBean.success();
    }

    @PostMapping("/goodsDescription")
    public Object createGoodsDescription(@RequestParam String Authorization, @RequestParam Long goodsId, @RequestParam String img1,
                                         @RequestParam String img2,
                                         @RequestParam String img3,
                                         @RequestParam String img4,
                                         @RequestParam String preview1,
                                         @RequestParam String preview2,
                                         @RequestParam String preview3) {

        GoodsDescription description = new GoodsDescription();
        description.setGoodsId(goodsId);
        description.setPicture1(img1);
        description.setPicture2(img2);
        description.setPicture3(img3);
        description.setPicture4(img4);

        description.setPreview(preview1);
        description.setPreview2(preview2);
        description.setPreview3(preview3);
        goodsDescriptionRepository.save(description);
        return ResponseBean.success();
    }

    @DeleteMapping("/deleteDescription")
    public Object deletedd(@RequestParam String Authorization, @RequestParam long descriptionId) {
        goodsDescriptionRepository.deleteById(descriptionId);
        return ResponseBean.success();
    }

}
