package dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pojo.User;
import pojo.UserVO;

import java.util.List;


public interface UserDao {
   Integer addUser(User user);
    Integer deleteUser(Long id);
    Integer updateUser(User user);

    User selectObject(@Param("id") Long id);
//    UserVO selectObject(Long id);

//    @Select("select * from USER ")
    List<User> selectList();
}
