package org.koreait.models.file;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.FileInfo;
import org.koreait.repositories.FileInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileDoneService {
    private final FileInfoRepository repository;
    private final FileInfoService infoService;

    public void done(String gid) {
        List<FileInfo> files = infoService.gets(gid, null, false);
        files.stream().forEach(f -> f.setDone(true));
        repository.flush();
    }
}
