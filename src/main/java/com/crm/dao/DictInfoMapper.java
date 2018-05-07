package com.crm.dao;

import com.crm.entity.DictInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DictInfo record);

    int insertSelective(DictInfo record);

    DictInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DictInfo record);

    int updateByPrimaryKey(DictInfo record);

    List<DictInfo> selectByGroupKey(String groupKey);
}