package cn.Esther.dao;

import cn.Esther.pojo.Comment;
import cn.Esther.pojo.Works;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    List<Comment> selectByList();

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}