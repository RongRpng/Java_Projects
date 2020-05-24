package jdbc;

import dao.UserDao;
import dao.UserDaoImpl;
import pojo.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.List;
import util.DatasSourceUtil;
import static com.sun.tools.doclint.Entity.or;


public class test {


    public static void main(String[] args) throws InterruptedException{
        DataSource dataSource = DatasSourceUtil.getDruid();

        JdbcTemplate jdbcTamplate = new JdbcTemplate(dataSource);

        Long time = System.currentTimeMillis();

        UserDao userdao = new UserDaoImpl();

        User user = new User();
        user.setName("小红");
        user.setQq("1234556");
        user.setType(1);
        user.setTime(time);
        user.setLink("fggggg");
        user.setCreateAt(time);
        user.setCreateBy(time);
        user.setUpdateAt(time);
        user.setUpdateBy(time);

        Long id = userdao.addUser(jdbcTamplate, user);
        System.out.println("插入返回主键"+id);

//        //删除
//        Long deleteId = 6L;
//        Integer deleteResult = userdao.deleteUser(jdbcTamplate, deleteId);
//        System.out.println("删除结果："+ deleteResult);
//
//        //查询
//        User resultUser = userdao.selectObject(jdbcTamplate,id);
//        System.out.println(resultUser);
//
//        //修改
//        resultUser.setQq("11111");
//        Integer updateResult = userdao.updateUser(jdbcTamplate, resultUser);
//        System.out.println(updateResult);
//
//        //列表查询
//        List<User> userList = userdao.selectList(jdbcTamplate);
//        System.out.println("用户列表："+userList);



    }





}

