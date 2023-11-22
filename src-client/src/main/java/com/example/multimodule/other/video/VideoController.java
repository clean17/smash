package com.example.multimodule.other.video;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

@Controller
@RequestMapping("/video")
public class VideoController {

    @GetMapping("/stream")
    public ResponseEntity<?> getVideoUrl() {

        String videoUrl = "C:\\Users\\piw94\\Documents\\ㅍㅊ.mp4";
        HashMap<String, String> url = new HashMap<>();
        url.put("url", videoUrl);
        return new ResponseEntity<HashMap<String, String>>(url, HttpStatus.OK);
    }


    private static final String VIDEO_PATH = "C:\\Users\\piw94\\Documents\\ㅍㅊ.mp4";

    // 메모리에 전체를 로드.. 큰 파일은 사용못함
    @GetMapping("/stream2")
    public ResponseEntity<Resource> getVideo() throws IOException {
        File videoFile = new File(VIDEO_PATH);
        InputStreamResource videoStream = new InputStreamResource(new FileInputStream(videoFile));

        long videoLength = videoFile.length();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", Files.probeContentType(Paths.get(VIDEO_PATH)));
        headers.set("Accept-Ranges", "bytes");
        headers.set("Content-Length", String.valueOf(videoLength));
        headers.set("Content-Range", "bytes 0-" + (videoLength - 1) + "/" + videoLength);

        return new ResponseEntity<>(videoStream, headers, HttpStatus.PARTIAL_CONTENT);
    }

    @GetMapping("/stream3")
    public ResponseEntity<Resource> getVideo(HttpServletRequest request) throws IOException {
        File videoFile = new File(VIDEO_PATH);
        if (!videoFile.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String range = request.getHeader("Range");
        long videoLength = videoFile.length();
        long rangeStart = 0;
        long rangeEnd = videoLength - 1;

        if (range != null) {
            String[] ranges = range.replace("bytes=", "").split("-");
            rangeStart = Long.parseLong(ranges[0]);
            if (ranges.length > 1) {
                rangeEnd = Long.parseLong(ranges[1]);
            }
        }

        long contentLength = rangeEnd - rangeStart + 1;
        RandomAccessFile raf = new RandomAccessFile(videoFile, "r");
        raf.seek(rangeStart);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", Files.probeContentType(Paths.get(VIDEO_PATH)));
        headers.set("Accept-Ranges", "bytes");
        headers.set("Content-Length", String.valueOf(contentLength));
        headers.set("Content-Range", "bytes " + rangeStart + "-" + rangeEnd + "/" + videoLength);

        InputStreamResource videoStream = new InputStreamResource(new FileInputStream(raf.getFD()){
            @Override
            public void close() throws IOException {
                raf.close();
            }
        });

        return ResponseEntity.status(range == null ? HttpStatus.OK : HttpStatus.PARTIAL_CONTENT)
                .headers(headers)
                .body(videoStream);
    }

}
