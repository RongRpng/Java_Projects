package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public interface UserDao {
    public void insert(Connection connection, String sql, Statement statement);
    public void delete(Connection connection, String sql, Statement statement);
    public void update(Connection connection, String sql, Statement statement);
    public void select(Connection connection, String sql, ResultSet resultSet, Statement statement);
    //防止sql注入
    public void prepareStatementSelect(Connection connection, String sql, String name, ResultSet resultSet);
}
