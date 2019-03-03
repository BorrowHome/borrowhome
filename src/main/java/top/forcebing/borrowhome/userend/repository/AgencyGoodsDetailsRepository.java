package top.forcebing.borrowhome.userend.repository;

import org.springframework.data.repository.CrudRepository;
import top.forcebing.borrowhome.userend.model.AgencyGoodsDetails;

import java.util.List;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/3  16:29
 **/
public interface AgencyGoodsDetailsRepository extends CrudRepository<AgencyGoodsDetails, Long> {

    List<AgencyGoodsDetails> findByAgencyGoodsId(long agencyGoodsId);
}
