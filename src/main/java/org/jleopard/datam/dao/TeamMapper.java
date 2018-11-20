package org.jleopard.datam.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jleopard.datam.model.Team;

import java.util.List;

@Mapper
public interface TeamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Team record);

    Team selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Team record);

    List<Team> selectByWhere(Team team);

    int deleteByWhere(Team team);
}