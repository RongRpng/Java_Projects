import dao.UserDao;
import dao.UserDaoImpl;

import java.sql.*;

import static com.sun.tools.doclint.Entity.or;


public class test {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //mysql 5
//    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //mysql 8
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Java_task1?characterEncoding=UTF-8";//DB+？

    private static final String USER="root";
    private static final String PASSWORD = "pass";

    public static void main(String[] args) {
        Connection connection = null;

        try {
            //step1 通过反射注册JDBC驱动
            Class.forName(JDBC_DRIVER);

            //step2 链接数据库
            System.out.println("连接数据库...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);


            //超过最大链接数 Error: too many connections
//            int i =0;
//            while(true){
//                System.out.println("链接数量"+i);
//                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
//                i++;
//                if (i>130){
//                    //然后去查看show processlist
//                    Thread.sleep(1000000);
//                }
//            }
            connection.setAutoCommit(false);
            //初始化一个对象
            UserDao userDao = new UserDaoImpl();

//            Statement statement = connection.createStatement();
//            String sql = "select * from  user ";
//            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()){//输出or打印结果集
//                String id  = resultSet.getString("id");
//                String name  = resultSet.getString("name");
//
//                System.out.println(id);
//                System.out.println(name);
//            }
//            resultSet.close();
//            statement.close();


             String name = "杜腾飞' or '1' = '1";
//            //查询
//            String name = "杜腾飞";
//            String selectSql = "Select * from user where name = '"+name+"'";
//            System.out.println(selectSql);
//            userDao.select(connection, selectSql, null, null);
//

            //防止sql注入的查询
            String prepareSql = "Select * from user where name = ?";
            userDao.prepareStatementSelect(connection, prepareSql, name, null);

//            //删除
//            Long id = 1L; //id = 1
//            String deleteSql = "Delete from user where id= '"+id+"'";
//            System.out.println(deleteSql);
//            userDao.delete(connection, deleteSql,null);
//
//            //修改
//            Long id = 1L; //id = 1
//            String update_sql = "UPDATE `Java_task1`.`user` SET " +
//                    "`name` = '杜腾飞', " +
//                    "`qq` = '657344389', " +
//                    "`type` = 4, " +
//                    "`time` = 20180327, " +
//                    "`link` = 'r', `create_at` = 1, `create_by` = 1, " +
//                    "`update_at` = 1, `update_by` = 1 WHERE `id` = "+id+";";
//            userDao.update(connection, update_sql, null);
//
//
//           //新增
//            String name = "aa";
//            String qq = "12345678";
//            int type = 1;
//            String link = "www.baidu.com";
//            Long time = System.currentTimeMillis();
//            int create_at=1;
//            int create_by  =1;
//            int update_at = 1;
//            int update_by = 1;
//            String insertSql ="INSERT INTO `Java_task1`.`user`" +
//                    "(`name`, `qq`, `type`, `time`, `link`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES " +
//                    "('"+name+"',"+qq+", "+type+", "+time+", '"+link+"', " +
//                    ""+create_at+", "+create_by+", "+update_at+", "+update_by+");" ; //String要比int多个单引号
//            userDao.insert(connection, insertSql, null);

//            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            try {
                if(null != connection){
                    connection.rollback();
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }finally {
            //关闭资源
            try {
                if(null != connection){
                    connection.rollback();
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }

    }





}
