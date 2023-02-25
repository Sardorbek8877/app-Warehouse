package uz.com.appwarehause.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.com.appwarehause.entity.Attachment;
import uz.com.appwarehause.entity.AttachmentContent;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.repository.AttachmentContentRepository;
import uz.com.appwarehause.repository.AttachmentRepository;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    @SneakyThrows
    public Result upload(MultipartHttpServletRequest request){
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        //
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);
        //
        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new Result("File successfully saved!", true, savedAttachment.getId());
    }

    public void getFile(Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();

            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findById(id);
            AttachmentContent attachmentContent = optionalAttachmentContent.get();
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + attachment.getName() + "\"");
            response.setContentType(attachment.getContentType());
            FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
        }
    }
}
