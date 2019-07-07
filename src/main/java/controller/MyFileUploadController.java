package controller;

import form.MyUploadForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MyFileUploadController {
    //pthuc nay duoc goi moi lan submit
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target =" + target);
        if (target.getClass() == MyUploadForm.class) {
            //dang ky de chuyen doi giua cac doi tuong multipart thanh bytep[]
            dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
            ;
        }
    }

    //GET: hien thi trang form upload
    @RequestMapping(value = "/uploadOneFile", method = RequestMethod.GET)
    public String uploadOneFileHandler(Model model) {
        MyUploadForm myUploadForm = new MyUploadForm();
        model.addAttribute("myUploadForm", myUploadForm);
        //Forward to /WEB-INF/pages/uploadOneFile.jsp
        return "uploadOneFile";
    }

    //POST: xu ly Upload
    @RequestMapping(value = "uploadOneFile", method = RequestMethod.POST)
    public String uploadOneFileHandlerPOST(HttpServletRequest request, Model model, @ModelAttribute("myUploadForm") MyUploadForm myUploadForm) {
        return this.doUpload(request, model, myUploadForm);
    }

    //GET: Hien thi trang form upload
    @RequestMapping(value = "/uploadMultiFile", method = RequestMethod.GET)
    public String uploadMultiFileHandler(Model model) {
        MyUploadForm myUploadForm = new MyUploadForm();
        model.addAttribute("myUploadForm", myUploadForm);
        //forward to "/WEB-INF/pages/uploadMultiFile.jsp
        return "uploadMultiFile";
    }

    //POST: xu ly upload
    @RequestMapping(value = "/uploadMultiFile", method = RequestMethod.POST)
    public String uploadMultiFileHandlerPOST(
            HttpServletRequest request, Model model, @ModelAttribute("myUploadForm") MyUploadForm myUploadForm) {
        return this.doUpload(request, model, myUploadForm);
    }
    private String doUpload(HttpServletRequest request, Model model, MyUploadForm myUploadForm){
        String description = myUploadForm.getDescription();
        System.out.println("Description =" + description);
        //thu muc goc upload file
        String uploadRootPath =
                request.getServletContext().getRealPath("upload");
        System.out.println("uploadRootPath =" + uploadRootPath);
        File uploadRootDir = new File(uploadRootPath);
        //tao thu muc goc upload neu no khong ton tai
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        CommonsMultipartFile[] fileDatas = myUploadForm.getFileDatas();
        Map<File, String> uploadedFiles = new HashMap<>();
        for (CommonsMultipartFile fileData : fileDatas) {
            //tao file goc tai client
            String name = fileData.getOriginalFilename();
            System.out.println("Client File Name = " + name);
            if (name != null && name.length() > 0) {
                try {
                    //tao file tai server
                    File serverFile = new
                            File(uploadRootDir.getAbsolutePath() + File.separator + name);
                    //luong ghi du lieu vao file tren server
                    BufferedOutputStream stream = new
                            BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(fileData.getBytes());
                    stream.close();
                    uploadedFiles.put(serverFile, name);
                    System.out.println("Write file: " + serverFile);
                } catch (Exception e) {
                    System.out.println("Error Write file: " + name);
                }
            }
        }
        model.addAttribute("description", description);
        model.addAttribute("uploadedFiles",uploadedFiles);
        return "uploadResult";
    }
}
