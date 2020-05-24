package cn.Esther.dao;

import org.apache.ibatis.annotations.Param;
import cn.Esther.pojo.User;

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
