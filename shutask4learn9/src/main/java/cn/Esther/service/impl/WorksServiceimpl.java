package cn.Esther.service.impl;

import cn.Esther.dao.WorksMapper;
import cn.Esther.pojo.Content;
import cn.Esther.pojo.Works;
import cn.Esther.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorksServiceimpl implements WorksService {
    @Autowired
    private WorksMapper worksMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return worksMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Works record) {
        return worksMapper.insert(record);
    }

    @Override
    public int insertSelective(Works record) {
        return worksMapper.insertSelective(record);
    }

    @Override
    public Works selectByPrimaryKey(Long id) {
        return worksMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Works> selectByList(){
        return worksMapper.selectByList();
    };

    @Override
    public int updateByPrimaryKeySelective(Works record) {
        return worksMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Works record) {
        return worksMapper.updateByPrimaryKey(record);
    }
}
