package top.forcebing.borrowhome.userend.repository;

import org.springframework.data.repository.CrudRepository;
import top.forcebing.borrowhome.userend.model.Agree;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/2  16:47
 **/
public interface AgreeRepository extends CrudRepository<Agree, Long> {

    long countAgreeByNoteId(long noteId);

    void deleteByNoteIdAndUserId(Long noteId, String userId);

}
