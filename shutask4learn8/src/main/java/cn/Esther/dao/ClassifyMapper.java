package cn.Esther.dao;

import cn.Esther.pojo.Classify;
import cn.Esther.pojo.Comment;

import java.util.List;

public interface ClassifyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Classify record);

    int insertSelective(Classify record);

    Classify selectByPrimaryKey(Long id);

    List<Classify> selectByList();

    int updateByPrimaryKeySelective(Classify record);

    int updateByPrimaryKey(Classify record);
}