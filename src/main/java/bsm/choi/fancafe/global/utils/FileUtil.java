package bsm.choi.fancafe.global.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Component
public class FileUtil {
    public String handleProfileImage(MultipartFile file) throws RuntimeException {
        if(file != null && !file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename() + "_" + UUID.randomUUID();
                String filePath = "uploads/" + fileName;

                file.transferTo(new File(filePath));
                return filePath;
            } catch (Exception e){
                throw new RuntimeException("프로필 이미지 저장 실패하였습니다");
            }
        }
        return null;
    }
}
