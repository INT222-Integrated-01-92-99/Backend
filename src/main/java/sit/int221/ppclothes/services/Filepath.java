package sit.int221.ppclothes.services;


import org.apache.commons.io.IOUtils;
import sit.int221.ppclothes.repositories.repoProduct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Filepath {
    String fold = "./public/img/";

    public void saveImage(MultipartFile file, String fileName) throws Exception {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(fold, fileName);
        Files.write(path, bytes);
    }

    public byte[] getFile(String file) throws IOException {
        Path path = Paths.get(fold, file);
        return IOUtils.toByteArray(path.toUri());
    }

    public void deleteImage(String fileName) throws Exception {
        Path path = Paths.get(fold, fileName);
        Files.delete(path);
    }


}
