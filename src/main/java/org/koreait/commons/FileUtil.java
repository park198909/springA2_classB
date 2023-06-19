package org.koreait.commons;

import lombok.RequiredArgsConstructor;
import org.koreait.models.file.FileInfoService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileUtil {
    private final FileInfoService fileInfoService;

    public String[] process(Long id) {

        return null;
    }
}
