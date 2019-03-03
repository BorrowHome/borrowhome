package top.forcebing.borrowhome.shopend.repository;

import org.springframework.data.repository.CrudRepository;
import top.forcebing.borrowhome.shopend.model.Store;

import java.util.List;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:40
 **/

public interface StoreRepository extends CrudRepository<Store, Long> {

    List<Store> findByClassification(String classification);

    Store findByAdminUserId(long userId);

}
