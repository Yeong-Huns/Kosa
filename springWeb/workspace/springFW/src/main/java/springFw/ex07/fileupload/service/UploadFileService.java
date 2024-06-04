package springFw.ex07.fileupload.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springFw.ex07.fileupload.dao.IUploadFileRepository;
import springFw.ex07.fileupload.model.UploadFileVO;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * packageName    : springFW.ex07.fileupload.service
 * fileName       : UploadFileService
 * author         : Yeong-Huns
 * date           : 2024-06-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-04        Yeong-Huns       최초 생성
 */
@Service
public class UploadFileService implements IUploadFileService {
    @Autowired
    IUploadFileRepository uploadFileRepository;

    @Override
    public void uploadFile(UploadFileVO file) {
        int newFileId = uploadFileRepository.getMaxFileId() + 1;
        file.setFileId(newFileId);
        uploadFileRepository.uploadFile(file);
    }

    @Override
    public List<UploadFileVO> getFileList(String dir) {
        return uploadFileRepository.getFileList(dir);
    }

    @Override
    public List<UploadFileVO> getAllFileList() {
        return uploadFileRepository.getAllFileList();
    }

    @Override
    public List<UploadFileVO> getImageList(String dir) {
        return uploadFileRepository.getImageList(dir);
    }

    @Override
    public UploadFileVO getFile(int fileId) {
        return uploadFileRepository.getFile(fileId);
    }

    @Override
    public String getDirectoryName(int fileId) {
        return uploadFileRepository.getDirectoryName(fileId);
    }

    @Override
    @Transactional
    public void updateDirectory(int[] fileIds, String directoryName) {
        for(int fileId : fileIds) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("directoryName", directoryName);
            map.put("fileId", fileId);
            uploadFileRepository.updateDirectory(map);
        }
    }

    @Override
    public void deleteFile(int fileId) {
        uploadFileRepository.deleteFile(fileId);
    }
}
