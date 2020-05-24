//package dao;
//
//import org.apache.ibatis.session.SqlSession;
//import pojo.User;
//
//import java.util.List;
//
//
//public class UserDaoImpl implements UserDao {
//    private SqlSession sqlSession;
//
//    public UserDaoImpl(SqlSession sqlSession){
//
//        this.sqlSession = sqlSession;
//    }
//
//    public Integer addUser(User user){
//        System.out.println("hi");
//        System.out.println(user);
//        return sqlSession.insert("dao.UserDao.addUser",user);
//    }
//
//    public Integer deleteUser(Long id){
//        System.out.println(id);
//        return sqlSession.delete("dao.UserDao.deleteUser",id);
//    }
//
//    public Integer updateUser(User user){
//        return sqlSession.update("dao.UserDao.updateUser", user);
//    }
//
//    public User selectObject(Long id){
//        return sqlSession.selectOne("dao.UserDao.selectObject", id);
//    }
//
//    public List<User> selectList(){
//        return sqlSession.selectList("dao.UserDao.selectList");
//    }
////
////    }
//
//}
