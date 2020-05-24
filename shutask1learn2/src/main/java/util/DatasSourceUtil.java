package util;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.apache.commons.dbcp.BasicDataSource;

public class DatasSourceUtil {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //mysql 5
    //    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //mysql 8
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Java_task1?characterEncoding=UTF-8";//DB+？

    private static final String USER="root";
    private static final String PASSWORD = "pass";

    //自己自带的
    public static DriverManagerDataSource getTemplate(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

//        dataSource.setDriverClassName(JDBC_DRIVER);
//        dataSource.setUrl(DB_URL);
//        dataSource.setUsername(USER);
        dataSource.setDriverClass(JDBC_DRIVER);
        dataSource.setJdbcUrl(DB_URL);
        dataSource.setUser(USER);

        dataSource.setPassword(PASSWORD);
        return dataSource;
    }
    // c3p0连接池
    public static ComboPooledDataSource getC3p0(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(JDBC_DRIVER);
            dataSource.setJdbcUrl(DB_URL);
            dataSource.setUser(USER);
            dataSource.setPassword(PASSWORD);
            dataSource.setInitialPoolSize(50);
            dataSource.setMaxPoolSize(100);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataSource;
    }
    //dbcp连接池
    public static BasicDataSource getdbcp(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(JDBC_DRIVER);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setInitialSize(50);
        dataSource.setMaxActive(100);
        return dataSource;
    }
    //druid 阿里巴巴连接池
    public static DruidDataSource getDruid(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(JDBC_DRIVER);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setInitialSize(50);
        dataSource.setMaxActive(100);
        return dataSource;
    }
}