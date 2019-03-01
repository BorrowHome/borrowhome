package top.forcebing.borrowhome.common.repository;

import top.forcebing.borrowhome.common.model.UserInfo;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/11/8  23:16
 */
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {

    UserInfo findByOpenId(String openId);


}


/*这个userinfo是个bug，后期来修改，我们现在采用sql语句自己写
*
* @Modifying
@Query("update User u set u.userName = ?1 where u.id = ?2")
int modifyByIdAndUserId(String  userName, Long id);

@Transactional
@Modifying
@Query("delete from User where id = ?1")
void deleteByUserId(Long id);

@Transactional(timeout = 10)
@Query("select u from User u where u.emailAddress = ?1")
    User findByEmailAddress(String emailAddress);
*
*
* */