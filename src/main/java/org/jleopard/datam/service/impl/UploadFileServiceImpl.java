/**
 * @author (c) 2018,
 * @date 2018/11/18  11:28
 * @version 1.0
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */
package org.jleopard.datam.service.impl;

import org.jleopard.datam.common.Msg;
import org.jleopard.datam.dao.UploadFileMapper;
import org.jleopard.datam.model.UploadFile;
import org.jleopard.datam.service.UploadFileService;
import org.jleopard.datam.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.jleopard.datam.common.MsgResultKey.FILE_LIST;

/**
 * 文件信息Service
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Autowired
    private UploadFileMapper uploadFileMapper;

    @Override
    public Msg save(UploadFile uploadFile, MultipartFile... files) {
        try {
            if (uploadFileMapper.insert(uploadFile) > 0){
                FileUtils.saveFile(files);
                return Msg.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
        return Msg.msg("上传文件失败");
    }

    @Override
    public Msg delete(UploadFile uploadFile) {
        try {
            if (uploadFileMapper.deleteByWhere(uploadFile) > 0){
                FileUtils.deleteFile(uploadFile.getFilePath());
                return Msg.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
        return Msg.msg("删除失败");
    }

    @Override
    public Msg update(UploadFile uploadFile,MultipartFile... files) {
        return null;
    }

    @Override
    public Msg list(UploadFile uploadFile) {
        try {
            List<UploadFile> uploadFileList = uploadFileMapper.selectByWhere(uploadFile);
            if (CollectionUtils.isEmpty(uploadFileList)){
                return Msg.msg("暂无数据");
            }else {
                return Msg.success().put(FILE_LIST,uploadFileList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }

    @Override
    public Msg upload(MultipartFile... files) {
        return null;
    }
}
