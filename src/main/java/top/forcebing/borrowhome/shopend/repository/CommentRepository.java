package top.forcebing.borrowhome.shopend.repository;

import org.springframework.data.repository.CrudRepository;
import top.forcebing.borrowhome.shopend.model.Comment;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:45
 **/
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
