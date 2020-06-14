package cn.Esther.backup;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String user_name);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(String user_name);

    List<User> selectByList();

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);
}