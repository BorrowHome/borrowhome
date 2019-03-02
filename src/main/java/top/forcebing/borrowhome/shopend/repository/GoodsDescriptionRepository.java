package top.forcebing.borrowhome.shopend.repository;

import org.springframework.data.repository.CrudRepository;
import top.forcebing.borrowhome.shopend.model.GoodsDescription;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:39
 **/
public interface GoodsDescriptionRepository extends CrudRepository<GoodsDescription, Long> {
    void deleteByGoodsId(long goodsId);

    GoodsDescription findByGoodsId(long goodsId);
}
