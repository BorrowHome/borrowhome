package top.forcebing.borrowhome.shopend.repository;

import org.springframework.data.repository.CrudRepository;
import top.forcebing.borrowhome.shopend.model.GoodsDetails;

import java.util.List;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:40
 **/
public interface GoodsDetailsRepository extends CrudRepository<GoodsDetails, Long> {

    List<GoodsDetails> findByGoodsId(long goodsId);

    void deleteByGoodsId(long goodsId);
}
