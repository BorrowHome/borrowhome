package top.forcebing.borrowhome.userend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import top.forcebing.borrowhome.userend.model.Note;

import java.util.List;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/2  16:58
 **/
public interface NoteRepository extends CrudRepository<Note, Long> {

    @Query(value = "select  * from  note order by RAND() limit 20", nativeQuery = true)
    List<Note> findRandom();

    List<Note> findByUserId(String userId);
}
