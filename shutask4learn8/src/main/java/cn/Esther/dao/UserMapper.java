package cn.Esther.dao;

import cn.Esther.pojo.Content;
import cn.Esther.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String user_name);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String user_name);

    List<User> selectByList();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}