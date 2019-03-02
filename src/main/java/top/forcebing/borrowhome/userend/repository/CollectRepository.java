package top.forcebing.borrowhome.userend.repository;

import org.springframework.data.repository.CrudRepository;
import top.forcebing.borrowhome.userend.model.Collect;

import java.util.List;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:51
 **/
public interface CollectRepository extends CrudRepository<Collect, Long> {

    List<Collect> findByUserIdAndCollectionType(String userId, Collect.CollectType collectType);
}
