package dao;

import dao.UserDao;
//import dao.UserDaoImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import pojo.User;
import org.junit.*;
import pojo.UserVO;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest{
    private UserDao userDao;
    private SqlSession sqlSession;

    @Before
    public void setUp() throws Exception{
        //mybatis-config.xml
        String resource = "mybatis-config.xml";
        //读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlSession
        sqlSession = sqlSessionFactory.openSession();

        //不使用动态代理，直接使用有参构造
//        userDao = new UserDaoImpl(sqlSession);
        //使用动态代理
        userDao = sqlSession.getMapper(UserDao.class);

    }
    @Test
    public void addUser(){
        System.out.println("dffdff");
        Long time = System.currentTimeMillis();

        User user = new User();
        user.setName("小");
        user.setQq("1234556");
        user.setType(1);
        user.setTime(time);
        user.setLink("bbb");
        user.setCreateAt(time);
        user.setCreateBy(time);
        user.setUpdateAt(time);
        user.setUpdateBy(time);
        System.out.println("hihi");
        Integer result = userDao.addUser(user);
        System.out.println("result:"+result);
        System.out.println(user.getId());
//        sqlSession.commit(); //不提交
//        sqlSession.close();
    }

    @Test
    public void deleteUser(){
        Integer result = userDao.deleteUser(9L);//删除id为9
        System.out.println(result);
//        sqlSession.commit();
    }

    @Test
    public void updateUser(){
        User user = userDao.selectObject(12L);
        System.out.println("user is: "+ user);

        user.setName("你好明天");
        Integer result = userDao.updateUser(user);

        User resultUser = userDao.selectObject(12L);
        System.out.println("result is:"+resultUser);

    }

    @Test
    public void selectObject(){
        User user = userDao.selectObject(10L);
        System.out.println(user);
//        sqlSession.commit();
    }
//   @Test
//    public void selectObject(){
//        UserVO user = userDao.selectObject(10L);
//        System.out.println(user);
////        sqlSession.commit();
//    }

    @Test
    public void selectList(){
        List<User> userList = userDao.selectList();
        System.out.println(userList);
//        sqlSession.commit();
    }


}