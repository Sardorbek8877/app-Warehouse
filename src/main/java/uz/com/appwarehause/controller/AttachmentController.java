package uz.com.appwarehause.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.com.appwarehause.entity.Attachment;
import uz.com.appwarehause.entity.AttachmentContent;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.repository.AttachmentContentRepository;
import uz.com.appwarehause.repository.AttachmentRepository;
import uz.com.appwarehause.service.AttachmentService;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    //UPLOAD FILE
    @SneakyThrows
    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request){
        return attachmentService.upload(request);
    }

    //DOWNLOAD FILE
    @GetMapping("/download/{id}")
    public void getFile(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        attachmentService.getFile(id, response);
    }
}










