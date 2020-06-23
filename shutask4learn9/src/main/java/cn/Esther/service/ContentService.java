package cn.Esther.service;

import cn.Esther.pojo.Content;

import java.util.List;

public interface ContentService {
    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Long id);

    List<Content> selectByParam(Integer type);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);
}



