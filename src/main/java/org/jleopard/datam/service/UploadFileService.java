/**
 * @author (c) 2018,
 * @date 2018/11/18  11:22
 * @version 1.0
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */
package org.jleopard.datam.service;

import org.jleopard.datam.common.Msg;
import org.jleopard.datam.model.UploadFile;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {

    Msg save(UploadFile uploadFile, MultipartFile... files);

    Msg delete(UploadFile uploadFile);

    Msg update(UploadFile uploadFile,MultipartFile... files);

    Msg list(UploadFile uploadFile);

    Msg upload(MultipartFile... files);

}
