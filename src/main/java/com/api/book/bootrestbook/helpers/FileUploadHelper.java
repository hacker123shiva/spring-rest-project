package com.api.book.bootrestbook.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

    // public final String UPLOAD_DIR =
    // "D:\\telusko\\JavaAdvanceMock\\SpringBootSTS\\bootrestbook\\src\\main\\resources\\static\\images";
    public final String UPLOAD_DIR = new ClassPathResource("static/images").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException {

    }

    public boolean uploadFile(MultipartFile multipartFile) {
        boolean fa = false;

        try {

            // InputStream is = multipartFile.getInputStream();
            // byte data[] = new byte[is.available()];
            // is.read(data);

            // FileOutputStream fos = new FileOutputStream(UPLOAD_DIR + "\\" +
            // multipartFile.getOriginalFilename());
            // fos.write(data);

            // fos.flush();
            // fos.close();

            Files.copy(multipartFile.getInputStream(),
                    Paths.get(UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);

            fa = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fa;

    }
}
