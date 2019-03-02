package top.forcebing.borrowhome.userend.repository;

import org.springframework.data.repository.CrudRepository;
import top.forcebing.borrowhome.userend.model.Comment;

import java.util.List;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:45
 **/
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findByGoodsIdAndCommentType(Long goodsId,String commentType);
}
