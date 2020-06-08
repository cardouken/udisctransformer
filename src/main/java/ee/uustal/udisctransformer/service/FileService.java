package ee.uustal.udisctransformer.service;

import ee.uustal.udisctransformer.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileService {

    @Value("${app.upload.dir:${user.home}/Desktop}")
    public String uploadDir;

    public Path uploadFile(MultipartFile file) {
        Path copyLocation;
        try {
            copyLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new FileStorageException(String.format("Could not store file %s. Please try again!", file.getOriginalFilename()));
        }

        return copyLocation.toAbsolutePath();
    }
}