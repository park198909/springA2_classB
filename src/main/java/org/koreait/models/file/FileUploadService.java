package org.koreait.models.file;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.FileInfo;
import org.koreait.repositories.FileInfoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {
    @Value("${file.upload.path}")
    private String fileUploadPath;
    private final FileInfoRepository repository;
    private final HttpServletRequest request;
    private final FileDeleteService deleteService;

    /**
     * 
     * @param files
     * @param gid - null 값이거나 "" 일때는 랜덤하게 하나 생성
     * @param location
     */
    public List<FileInfo> upload(MultipartFile[] files, String gid, String location, boolean imageOnly, boolean single) {
        gid = gid == null || gid.isBlank() ? UUID.randomUUID().toString() : gid;

        // 이미지 형식의 파일만 업로드 가능 여부 체크 S
        if (imageOnly) {
            for (MultipartFile file : files) {
                if (file.getContentType().indexOf("image") == -1) { // 이미지 이외 형식인 경우
                    throw new FileTypeException("File.imageOnly");
                }
            }
        }
        // 이미지 형식의 파일만 업로드 가능 여부 체크 E

        /*
         * 단일파일만 업로드 처리
         * 기존 파일을 삭제하고 다시 올린다(gid, location)
         */
        if (single) {
            deleteService.delete(gid, location);
        }

        List<FileInfo> items = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String extension = fileName.lastIndexOf('.') != -1 ? fileName
                    .substring(fileName.lastIndexOf('.') + 1) : null;

            FileInfo item = FileInfo.builder()
                    .gid(gid)
                    .location(location)
                    .fileName(fileName)
                    .extension(extension)
                    .contentType(file.getContentType())
                    .build();
            item = repository.saveAndFlush(item);

            Long id = item.getId();
            String folder = "" + id % 10;
            File folderPath = new File(fileUploadPath + folder);
            if (!folderPath.exists()) {
                folderPath.mkdir();
            }

            String path = fileUploadPath + folder + "/" + id;
            String url = request.getContextPath() + "/uploads/" + folder + "/" + id;
            if (extension != null && !extension.isBlank()) {
                path += "." + extension;
                url += "." + extension;
            }

            item.setFilePath(path);
            item.setFileUrl(url);

            try {
                file.transferTo(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }

            items.add(item);
        }

        return items;
    }

    public void upload(MultipartFile[] files, String gid) {
        upload(files, gid, null, false, false);
    }

    public void upload(MultipartFile[] files) {
        upload(files, null);
    }
}
