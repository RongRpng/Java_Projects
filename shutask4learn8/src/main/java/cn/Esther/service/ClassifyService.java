package cn.Esther.service;

import cn.Esther.pojo.Classify;
import cn.Esther.pojo.Content;

import java.util.List;

public interface ClassifyService {
    int deleteByPrimaryKey(Long id);

    int insert(Classify record);

    int insertSelective(Classify record);

    Classify selectByPrimaryKey(Long id);

    List<Classify> selectByList();

    int updateByPrimaryKeySelective(Classify record);

    int updateByPrimaryKey(Classify record);
}
