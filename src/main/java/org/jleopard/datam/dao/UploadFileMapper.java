package org.jleopard.datam.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jleopard.datam.model.UploadFile;

import java.util.List;

@Mapper
public interface UploadFileMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UploadFile record);

    UploadFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(UploadFile record);

    List<UploadFile> selectByWhere(UploadFile uploadFile);

    int deleteByWhere(UploadFile uploadFile);
}