package cn.Esther.dao;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cn.Esther.pojo.User;
import cn.Esther.service.UserService;

import static org.junit.Assert.assertEquals;

//import java.util.logging.Logger;
//import static org.junit.Assert.*;

//构建spring环境然后把IOC初始化
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserDaoTest {

    @Autowired
    private UserService userService;

    Logger logger = Logger.getLogger(String.valueOf(UserDaoTest.class));

//    @Test
//    public void Test(){
////        logger.info("这个是info级别日志");
////        logger.error("这个是error级别日志");
//
//    }
//
//
//    @Test
//    public void addUser(){
//        System.out.println("dffdff");
//        Long time = System.currentTimeMillis();
//
//        User user = new User();
//        user.setName("小");
//        user.setQq("1234556");
//        user.setType(1);
//        user.setTime(time);
//        user.setLink("bbb");
//        user.setCreateAt(time);
//        user.setCreateBy(time);
//        user.setUpdateAt(time);
//        user.setUpdateBy(time);
//        System.out.println("hihi");
//        Integer result = userDao.addUser(user);
//        System.out.println("result:"+result);
//        System.out.println(user.getId());
////        sqlSession.commit(); //不提交
////        sqlSession.close();
//    }
//
////    @Test(timeout = 10000)
//    //    @Ignore
//    @Test
//    public void deleteUser(){
//        Integer result = userDao.deleteUser(9L);//删除id为9
//        System.out.println(result);
////        sqlSession.commit();
//    }
//
//    @Test
//    public void updateUser(){
//        User user = userDao.selectObject(12L);
//        System.out.println("user is: "+ user);
//
//        user.setName("你好明天");
//        Integer result = userDao.updateUser(user);
//
////        assertEquals("1", result);
//
//        User resultUser = userDao.selectObject(12L);
//        System.out.println("result is:"+resultUser);
//
//    }

    @Test
    public void selectObject(){
        User user = userService.selectObject(10L);

        String str = "Juint is working fine";
        assertEquals("Juint is working fine", str);

        System.out.println(user);

}
}
