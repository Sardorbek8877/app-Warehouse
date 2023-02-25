package uz.com.appwarehause.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.com.appwarehause.entity.Attachment;
import uz.com.appwarehause.entity.AttachmentContent;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.repository.AttachmentContentRepository;
import uz.com.appwarehause.repository.AttachmentRepository;
import uz.com.appwarehause.service.AttachmentService;

import java.util.Iterator;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @SneakyThrows
    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request){
        return attachmentService.upload(request);
    }

    
}










