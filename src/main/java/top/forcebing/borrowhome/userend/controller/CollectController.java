package top.forcebing.borrowhome.userend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.forcebing.borrowhome.common.utils.JwtTokenUtil;
import top.forcebing.borrowhome.common.utils.ResponseBean;
import top.forcebing.borrowhome.userend.model.Collect;
import top.forcebing.borrowhome.userend.repository.CollectRepository;

import java.util.List;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/2  14:27
 **/
@RestController
@RequestMapping("/collect")
@Slf4j
@Api(value = "收藏夹", tags = "note")
public class CollectController {


    @Autowired
    private CollectRepository collectRepository;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/create")
    public Object create(@RequestParam String Authorization,
                         @RequestParam @ApiParam(value = "收藏的id") long collectId,
                         @RequestParam @ApiParam(value = "收藏的分类 goods  store  note  ") String collectType) {


        Collect collect = new Collect();

        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);
        switch (collectType) {
            case "goods": {
                collect.setCollectionType(Collect.CollectType.goods);
                break;
            }
            case "store": {
                collect.setCollectionType(Collect.CollectType.shop);
                break;
            }
            case "note": {
                collect.setCollectionType(Collect.CollectType.note);
                break;
            }
            default: {
                return ResponseBean.error();
            }

        }
        collect.setCollectionId(collectId);
        collect.setUserId(userId);
        collectRepository.save(collect);

        return ResponseBean.success();
    }


    @GetMapping("/getAll")
    public Object getAll(@RequestParam String Authorization, @RequestParam String collectType) {

        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);

        switch (collectType) {
            case "goods": {

                List<Collect> collects = collectRepository.findByUserIdAndCollectionType(userId, Collect.CollectType.goods);
                return ResponseBean.success(collects);
            }
            case "store": {
                List<Collect> collects = collectRepository.findByUserIdAndCollectionType(userId, Collect.CollectType.shop);
                return ResponseBean.success(collects);
            }
            case "note": {
                List<Collect> collects = collectRepository.findByUserIdAndCollectionType(userId, Collect.CollectType.note);
                return ResponseBean.success(collects);
            }
            default: {
                return ResponseBean.error();
            }
        }

    }

    @DeleteMapping("/deleteOne")
    public Object deleteOne(@RequestParam String Authorization, String collectId) {

        collectRepository.deleteById(Long.valueOf(collectId));
        return ResponseBean.success();
    }

}