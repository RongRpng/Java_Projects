package cn.Esther.service.impl;
import cn.Esther.dao.UserDao;
import cn.Esther.pojo.User;
import cn.Esther.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Integer addUser(User user){return userDao.addUser(user);}
    @Override
    public Integer deleteUser(Long id){return userDao.deleteUser(id);}
    @Override
    public Integer updateUser(User user){return userDao.updateUser(user);}
    @Override
    public User selectObject(Long id){return userDao.selectObject(id);}
    @Override
    public List<User> selectList(){return userDao.selectList();}

}
//6.07