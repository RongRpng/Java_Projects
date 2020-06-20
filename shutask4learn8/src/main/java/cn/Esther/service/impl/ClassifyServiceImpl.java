package cn.Esther.service.impl;

import cn.Esther.dao.ClassifyMapper;
import cn.Esther.pojo.Classify;
import cn.Esther.pojo.Content;
import cn.Esther.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassifyServiceImpl implements ClassifyService {

    @Autowired
    private ClassifyMapper classifyMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return classifyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Classify classify) {
        return classifyMapper.insert(classify);
    }

    @Override
    public int insertSelective(Classify classify) {
        return classifyMapper.insertSelective(classify);
    }

    @Override
    public Classify selectByPrimaryKey(Long id) {
        return classifyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Classify> selectByList(){
        return classifyMapper.selectByList();
    };

    @Override
    public int updateByPrimaryKeySelective(Classify classify) {
        return classifyMapper.updateByPrimaryKeySelective(classify);
    }

    @Override
    public int updateByPrimaryKey(Classify classify) {
        return classifyMapper.updateByPrimaryKey(classify);
    }
}
