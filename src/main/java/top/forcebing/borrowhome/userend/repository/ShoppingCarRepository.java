package top.forcebing.borrowhome.userend.repository;

import org.springframework.data.repository.CrudRepository;
import top.forcebing.borrowhome.userend.model.ShoppingCar;

import java.util.List;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:53
 **/
public interface ShoppingCarRepository extends CrudRepository<ShoppingCar, Long> {

    List<ShoppingCar> findByUserId(String useId);


}
