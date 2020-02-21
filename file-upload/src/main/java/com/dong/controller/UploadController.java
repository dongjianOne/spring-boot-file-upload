package com.dong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

/**
 * @Author dongjian
 * @Date 2020/2/21 22:54
 * @Description:
 * @Version:1.0
 */
@Controller
public class UploadController {
    private static String UPLOAD_FOLDER = "E:\\upload";

    /**
     * 首页即进入上传页面
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file,
                         RedirectAttributes redirectAttributes) {
        // 判断是否选择了要上传的文件
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Please choose a file to upload");
            // redirect:uploadStatus 请求重定向
            return "redirect:uploadStatus";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("msg", "You had uploaded successfully" +"\t"+ file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
}
