package cn.Esther.service;

import cn.Esther.pojo.Comment;
import cn.Esther.pojo.Content;

import java.util.List;

public interface CommentService {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    List<Comment> selectByList();

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}
