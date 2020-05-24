package cn.Esther.service;

import cn.Esther.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

//(接口不需要写注解@services）

@Repository // if you add this, IntelliJ will not show error: could not use autowired, no bean of type "UserService"
public interface UserService {
    Integer addUser(User user);

    Integer deleteUser(Long id);

    Integer updateUser(User user);

    User selectObject(Long id);

    List<User> selectList();
}
