package run;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.User;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {

    public static void main(String[] args) throws IOException{
        //指定全局配置文件
        String resource = "mybatis-config.xml";
        //读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径
        // 构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = sqlSession.selectOne("dao.UserDao.selectObject", 10);
        System.out.println(user);
    }
}
/**
 1)配置mybatis-config.xml 全局配置文件 (1.数据源 2.外部的mapper)
 2）构建sqlSessionFactory
 3）通过sqlSessionFactory构建SqlSession对象
 4）通过Sqlsession操作数据库CRUD
 5）调用Session。commit提交事务
 6）调用Session.close关闭会话
**/