package springFW.ex07.fileupload.service;

import springFW.ex07.fileupload.model.UploadFileVO;

import java.util.List;

public interface IUploadFileService {

	void uploadFile(UploadFileVO file);

	List<UploadFileVO> getFileList(String dir);
	List<UploadFileVO> getAllFileList();
	List<UploadFileVO> getImageList(String dir);

	UploadFileVO getFile(int fileId);
	
	String getDirectoryName(int fileId);
	void updateDirectory(int[] fileIds, String directoryName);

	void deleteFile(int fileId);
}