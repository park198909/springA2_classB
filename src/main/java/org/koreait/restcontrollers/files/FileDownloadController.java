package org.koreait.restcontrollers.files;


import lombok.RequiredArgsConstructor;
import org.koreait.models.file.FileDownloadService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FileDownloadController {
    private final FileDownloadService downloadService;

    @RequestMapping("/file/download/{id}")
    public void download(@PathVariable Long id) {
        downloadService.download(id);
    }
}
