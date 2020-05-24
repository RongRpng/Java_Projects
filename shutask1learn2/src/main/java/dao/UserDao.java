package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import pojo.User;

import java.util.List;


public interface UserDao {
    public Long addUser(JdbcTemplate jdbcTemplate, final User user);
    public Integer deleteUser(JdbcTemplate jdbcTemplate, Long id);
    public Integer updateUser(JdbcTemplate jdbcTemplate, User user);
    public User selectObject(JdbcTemplate jdbcTemplate, Long id);
    public List<User> selectList(JdbcTemplate jdbcTemplate);
}
