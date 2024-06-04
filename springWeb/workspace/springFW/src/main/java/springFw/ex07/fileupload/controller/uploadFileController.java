package springFw.ex07.fileupload.controller;

import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springFw.ex07.fileupload.model.UploadFileVO;
import springFw.ex07.fileupload.service.IUploadFileService;

import java.util.List;

/**
 * packageName    : springFW.ex07.fileupload.controller
 * fileName       : Controller
 * author         : Yeong-Huns
 * date           : 2024-06-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-04        Yeong-Huns       최초 생성
 */
@Controller
@Slf4j
@RequestMapping("/upload")
public class uploadFileController {
    private static final Logger log = LoggerFactory.getLogger(uploadFileController.class);
    @Autowired
    IUploadFileService uploadFileService;

    public void uploadFile(UploadFileVO file){
        uploadFileService.uploadFile(file);
    }
    @RequestMapping("list")
    public String getFileList(Model model){
        List<UploadFileVO> list = uploadFileService.getAllFileList();
        model.addAttribute("fileList", list);
        return "list";
    }

    @RequestMapping("list/{dir}")
    public String getFileList(@PathVariable String dir, Model model){
        List<UploadFileVO> list = uploadFileService.getFileList("/"+dir);
        model.addAttribute("fileList", list);
        return "list";
    }

    @RequestMapping("gallery")
    public String getImageList(@RequestParam(value = "dir", required = false, defaultValue = "/images") String dir, Model model){
        List<UploadFileVO> list = uploadFileService.getImageList(dir);
        model.addAttribute("fileList", list);
        return "list";
    }


    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String uploadFile(Model model){
        return "form";
    }
    @RequestMapping(value="new", method = RequestMethod.POST)
    public String uploadFile(@RequestParam(value ="dir", required = false, defaultValue = "/")String dir,
                             MultipartFile file , RedirectAttributes redirectAttributes){
        log.debug(file.getOriginalFilename());
        try{
            UploadFileVO newFile = new UploadFileVO();
            newFile.setDirectoryName(dir);
            newFile.setFileName(file.getOriginalFilename());
            newFile.setFileSize(file.getSize());
            newFile.setFileContentType(file.getContentType());
            newFile.setFileData(file.getBytes());
            uploadFileService.uploadFile(newFile);
        }catch (Exception e){
            log.error(e.getMessage());
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/upload/list";
    }

    @RequestMapping("/file/{fileId}")
    public ResponseEntity<byte[]> getBinaryFile(@PathVariable int fileId){
        UploadFileVO file = uploadFileService.getFile(fileId);
        if(file != null) {
            log.debug("getFile : " + file.getFileName());
            HttpHeaders headers = new HttpHeaders();
              String[] mtypes = file.getFileContentType().split("/");
              headers.setContentType(new MediaType(mtypes[0], mtypes[1]));
              headers.setContentLength(file.getFileSize());
              headers.setContentDispositionFormData("attachment", file.getFileName());

              return new ResponseEntity<byte[]>(file.getFileData(), headers, HttpStatus.OK);
        }else {
            return ResponseEntity.notFound().build();
            //return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable int fileId){
        uploadFileService.deleteFile(fileId);
        return "redirect:/upload/list";
    }

    @RequestMapping("/updateDir")
    public String updateDirectory(@RequestParam int[] fileIds, @RequestParam String directoryName){
        uploadFileService.updateDirectory(fileIds, directoryName);
        return "redirect:/upload/list";
    }
}
