package top.forcebing.borrowhome.userend.repository;

import org.springframework.data.repository.CrudRepository;
import top.forcebing.borrowhome.userend.model.ChatMessage;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  19:50
 **/
public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> {
}
