package uz.com.appwarehause.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.com.appwarehause.entity.Attachment;
import uz.com.appwarehause.entity.AttachmentContent;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {


}
