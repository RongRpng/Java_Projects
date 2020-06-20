package cn.Esther.dao;

import cn.Esther.pojo.Student;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Long id);

    List<Student> selectByParam(String isHot);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}