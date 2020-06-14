package cn.Esther.dao;

import cn.Esther.pojo.Content;

import java.util.List;

public interface ContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Long id);

    //没有列表查询自己加一个
    List<Content> selectByList();

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);
}