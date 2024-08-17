package bsm.choi.fancafe.global.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileUtil {
    @Value("${file.path}")
    private String filePath; // 상대 경로

    public String handleProfileImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("파일이 비어 있습니다.");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new RuntimeException("파일 이름을 가져오는데 실패하였습니다.");
        }

        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID() + extension;

        // 상대 경로를 절대 경로로 변환
        Path uploadPath = Paths.get(filePath).toAbsolutePath().normalize();
        try {
            // 디렉토리 존재 여부 확인 및 생성
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("디렉토리 생성에 실패하였습니다");
        }

        // 파일 저장
        File destinationFile = new File(uploadPath.toFile(), fileName);
        try {
            file.transferTo(destinationFile);
            return destinationFile.getAbsolutePath(); // 절대 경로 반환
        } catch (IOException e) {
            throw new RuntimeException("프로필 이미지 저장 실패하였습니다");
        }
    }
}
