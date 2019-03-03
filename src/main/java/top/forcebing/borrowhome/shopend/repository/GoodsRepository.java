package top.forcebing.borrowhome.shopend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import top.forcebing.borrowhome.shopend.model.Goods;

import java.util.List;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:38
 **/
public interface GoodsRepository extends CrudRepository<Goods, Long> {
    List<Goods> findByAdminId(long adminId);
    List<Goods> findByStoreId(long storeId);

    @Query(value = "select * from goods where classification=?1 order by RAND() limit 10", nativeQuery = true)
    List<Goods> findByRandom(String classification);
}
