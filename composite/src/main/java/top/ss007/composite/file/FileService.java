package top.ss007.composite.file;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by Ben.Wang
 * <p>
 * author     : Ben.Wang
 * date       : 2021/6/25 17:27
 * description:
 */

@Service
public class FileService {

    public void uploadFile(MultipartFile file) {
        Path copyLocation = Paths.get(StringUtils.cleanPath(file.getOriginalFilename()));
        try {
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
