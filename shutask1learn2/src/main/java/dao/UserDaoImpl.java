package dao;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pojo.User;
import java.sql.*;
import java.util.List;
import java.sql.Statement;
import java.sql.PreparedStatement;

import static java.sql.Statement.RETURN_GENERATED_KEYS;


public class UserDaoImpl implements UserDao{
    public Long addUser(JdbcTemplate jdbcTemplate, final User user){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql ="INSERT INTO `Java_task1`.`user`" +
                    "(`name`, `qq`, `type`, `time`, `link`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES " +
                    "(?,?,?,?,?,?,?,?,?);" ;
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                ps.setString(2, user.getQq());
                ps.setInt(3, user.getType());
                ps.setLong(4, user.getTime());
                ps.setString(5, user.getLink());
                ps.setLong(6, user.getCreateAt());
                ps.setLong(7, user.getCreateBy());
                ps.setLong(8, user.getUpdateAt());
                ps.setLong(9, user.getUpdateBy());
                return ps;
            }},keyHolder);
        return keyHolder.getKey().longValue();
    }
    public Integer deleteUser(JdbcTemplate jdbcTemplate, Long id){
        String sql = "DELETE FROM user where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public Integer updateUser(JdbcTemplate jdbcTemplate, User user){
        String sql = "UPDATE `Java_task1`.`user` SET"+
                "`name`=?, `qq`=?, `type`=?, `time`=?, `link`=?, `create_at`=?, `create_by`=?, `update_at`=?, " +
                "`update_by=?`";
        return jdbcTemplate.update(sql, user.getName(), user.getQq(), user.getType(), user.getTime(), user.getLink(),
                user.getCreateAt(),user.getCreateBy(), user.getUpdateAt(), user.getUpdateBy());
    }
    public User selectObject(JdbcTemplate jdbcTemplate, Long id){
        String sql = "SELECT * FROM user WHERE id= ?";
//        User user = jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
//            public User mapRow(ResultSet resultSet, int i) throws SQLException {
//                User user = new User();
//                user.setId(resultSet.getLong("id"));
//                user.setName(resultSet.getString("name"));
//                user.setQq(resultSet.getString("name"));
//                return user;
//            }
//        },id);
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>());
        return user;

    }
    public List<User> selectList(JdbcTemplate jdbcTemplate){
        String sql = "SELECT * FROM user";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>());
        return userList;
    }

}
