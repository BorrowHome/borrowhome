package top.forcebing.borrowhome.userend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import top.forcebing.borrowhome.userend.model.AgencyGoods;

import java.util.List;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/3  15:16
 **/


public interface AgencyGoodsRepository extends CrudRepository<AgencyGoods, Long> {
    void deleteByAgencyIdAndStoreId(long userId, long storeId);

    List<AgencyGoods> findByAgencyIdAndShown(long agencyId, boolean show);

    @Query(value = "select * from goods where classification=?1 order by RAND() limit 10", nativeQuery = true)
    List<AgencyGoods> findByRandom(String classification);
}
