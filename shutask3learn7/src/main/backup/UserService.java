package cn.Esther.backup;

import cn.Esther.backup.User;

import java.util.List;

public interface UserService {
    int deleteByPrimaryKey(String user_name);

    int insert(User user);

    int insertSelective(User user);

    //user主键user_name
    User selectByPrimaryKey(String user_name);

    List<User> selectByList();

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);
}
