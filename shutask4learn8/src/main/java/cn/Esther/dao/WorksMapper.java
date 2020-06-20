package cn.Esther.dao;

import cn.Esther.pojo.Content;
import cn.Esther.pojo.Works;

import java.util.List;

public interface WorksMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Works record);

    int insertSelective(Works record);

    Works selectByPrimaryKey(Long id);

    List<Works> selectByList();

    int updateByPrimaryKeySelective(Works record);

    int updateByPrimaryKey(Works record);
}