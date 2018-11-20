package org.jleopard.datam.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jleopard.datam.model.User;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKey(User record);

    List<User> selectByWhere(User user);

    int deleteByWhere(User user);
}