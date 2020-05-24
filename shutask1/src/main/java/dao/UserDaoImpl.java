package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class UserDaoImpl implements UserDao{

    public void select(Connection connection, String sql, ResultSet resultSets, Statement statements){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){//输出or打印结果集
                String id  = resultSet.getString("id");
                String name  = resultSet.getString("name");

                System.out.println(id);
                System.out.println(name);
            }
            resultSet.close();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(null != connection){
                    connection.rollback();
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    public void prepareStatementSelect(Connection connection, String sql, String name, ResultSet resultSet) {
        try{
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, name);
            System.out.println(prepareStatement);
            resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                System.out.println(id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(null != connection){
                    connection.rollback();
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    public void insert(Connection connection, String sql, Statement statements){
        try{
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(null != connection){
                    connection.rollback();
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    public void delete(Connection connection, String sql, Statement statements){
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(null != connection){
                    connection.rollback();
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    public void update(Connection connection, String sql, Statement statements) {
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
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
