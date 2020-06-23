package cn.Esther.service;

import cn.Esther.pojo.Content;
import cn.Esther.pojo.Student;

import java.util.List;


public interface StudentService {
    int deleteByPrimaryKey(Long id);

    int insert(Student student);

    int insertSelective(Student student);

    Student selectByPrimaryKey(Long id);

    List<Student> selectByParam(String is_hot);

    int updateByPrimaryKeySelective(Student student);

    int updateByPrimaryKey(Student student);
}
