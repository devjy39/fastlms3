package com.zerobase.fastlms.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
public class FilePathUtil {
    static final String BASE_LOCAL_PATH = "C:/dev/IdeaProjects/fastlms3/files";
    static final String BASE_URL_PATH = "/files";

    public static String[] getNewSaveFile(String originalFilename) {
        LocalDate now = LocalDate.now();

        String[] dirs = {
                String.format("%s/%d/", BASE_LOCAL_PATH, now.getYear()),
                String.format("%s/%d/%02d/", BASE_LOCAL_PATH, now.getYear(), now.getMonthValue()),
                String.format("%s/%d/%02d/%02d/", BASE_LOCAL_PATH, now.getYear(), now.getMonthValue(), now.getDayOfMonth())};

        String urlDir = String.format("%s/%d/%02d/%02d/", BASE_URL_PATH, now.getYear(), now.getMonthValue(), now.getDayOfMonth());

        for (String dir : dirs) {
            File file = new File(dir);
            if (!file.isDirectory()) {
                file.mkdir();
            }
        }

        String fileExtension = "";
        if (originalFilename != null) {
            int dotPos = originalFilename.lastIndexOf(".");
            if (dotPos > -1) {
                fileExtension = originalFilename.substring(dotPos + 1);
            }
        }

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String newFilename = String.format("%s%s", dirs[2], uuid);
        String newUrlFilename = String.format("%s%s", urlDir, uuid);
        if (fileExtension.length() > 0) {
            newFilename += "." + fileExtension;
            newUrlFilename += "." + fileExtension;
        }

        return new String[]{newFilename, newUrlFilename};
    }

    public static void saveFile(MultipartFile file, String saveFilename) {
        try {
            File newFile = new File(saveFilename);
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
        } catch (IOException e) {
            log.info("file save error. ############################ - 1");
            log.info(e.getMessage());
        }
    }
}
