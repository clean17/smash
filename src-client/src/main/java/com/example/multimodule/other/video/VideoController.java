package com.example.multimodule.other.video;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/video")
public class VideoController {

    private static final String VIDEO_PATH = "C:\\Users\\piw94\\Documents\\dr";


    /**
     * 스프링에서 지원하는 Resource를 이용
     * 헤더에 attachment 속성이 있으므로 파일다운로드 형식으로 동작한다.
     * @return
     */
    @GetMapping("/stream")
    public ResponseEntity<Resource> getVideoUrl() {
        Resource resource = new FileSystemResource(VIDEO_PATH);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", "ㄷㄹ.mp4"));
        headers.setContentType(MediaType.parseMediaType("video/mp4"));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }


    /**
     * 마찬가지로 파일을 다운로드하여 메모리에 전체를 로드.. 큰 파일은 사용못함
     * @return
     * @throws IOException
     */
    @GetMapping("/stream2")
    public ResponseEntity<Resource> getVideo() throws IOException {
        File videoFile = new File(VIDEO_PATH);
        InputStreamResource videoStream = new InputStreamResource(new FileInputStream(videoFile));

        long videoLength = videoFile.length();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", Files.probeContentType(Paths.get(VIDEO_PATH)));
        headers.set("Accept-Ranges", "bytes"); // 서버가 바이트 단위의 요청을 지원함 -> 실시간 스트리밍 요청
        headers.set("Content-Length", String.valueOf(videoLength)); // 파일의 전체 크기
        headers.set("Content-Range", "bytes 0-" + (videoLength - 1) + "/" + videoLength); // 클라이언트에 전송된 데이터 범위와 전체 크기
        headers.setContentType(MediaType.parseMediaType("video/mp4"));
        return new ResponseEntity<>(videoStream, headers, HttpStatus.PARTIAL_CONTENT);
    }

    // 실시간 재생을 위해서는 Stream으로 응답해야한다.

    /**
     * 스트리밍 시도
     * @param request 
     * @return
     * @throws IOException
     */
    @GetMapping("/stream3")
    public ResponseEntity<Resource> streamVideo(HttpServletRequest request) throws IOException {
        File videoFile = new File(VIDEO_PATH);

        if (!videoFile.exists()) {
            return ResponseEntity.notFound().build();
        }

        RandomAccessFile file = new RandomAccessFile(videoFile, "r");
        long fileLength = file.length();
        String rangeString = request.getHeader("Range");

        long rangeStart = 0;
        long rangeEnd = fileLength - 1;
        boolean isPart = rangeString != null;
        int bufferSize = 16 * 1024;

        if (isPart) {
            String[] ranges = rangeString.substring("bytes=".length()).split("-");
            rangeStart = Long.parseLong(ranges[0]);
            rangeEnd = (ranges.length > 1) ? Long.parseLong(ranges[1]) : rangeEnd;
        }

        long contentLength = rangeEnd - rangeStart + 1;
        file.seek(rangeStart);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("video/mp4"));
        headers.setContentLength(contentLength);
        headers.add("Content-Range", "bytes " + rangeStart + "-" + rangeEnd + "/" + fileLength);
        headers.add("Accept-Ranges", "bytes");

        InputStreamResource videoResource = new InputStreamResource(new FileInputStream(file.getFD()) {
            @Override
            public int available() throws IOException {
                return (int) contentLength;
            }
        }) {
            @Override
            public InputStream getInputStream() throws IOException {
                return new BufferedInputStream(super.getInputStream(), bufferSize);
            }
        };

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .headers(headers)
                .body(videoResource);
    }


    /**
     * RandomAccessFile
     * 파일의 임의 접근으로 대용량 파일 처리할 때 사용할 수 있다.
     * 멀티 스레드가 파일을 공유할 때 사용할 수 있다.
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/random")
    public void viewStreaming(HttpServletRequest request, HttpServletResponse response) throws Exception {
        File file = new File(VIDEO_PATH);
        long rangeStart = 0, rangeEnd = 0;
        boolean isPart = false;

        try (RandomAccessFile randomFile = new RandomAccessFile(file, "r");
             BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream()); ) {
            long movieSize = randomFile.length();
            String range = request.getHeader("range");

            if(range != null) {
                if(range.endsWith("-")) {
                    range = range + (movieSize - 1);
                }
                int idxm = range.trim().indexOf("-");
                rangeStart = Long.parseLong(range.substring(6, idxm));
                rangeEnd = Long.parseLong(range.substring(idxm + 1));

                if(rangeStart > 0) isPart = true;
            } else {
                rangeStart = 0;
                rangeEnd = movieSize - 1;
            }

            long partSize = rangeEnd - rangeStart + 1;
            response.reset();
            response.setStatus(isPart ? 206: 200);
            headers.setContentType(MediaType.parseMediaType("video/mp4"));
            response.setHeader("Content-Range", "bytes " + rangeStart + "-" + rangeEnd + "/" + movieSize);
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("Content-Length", String.valueOf(partSize));
            randomFile.seek(rangeStart);

            int bufferSize = 8 * 1024;
            byte[] buf = new byte[bufferSize];

            do{
                int block = partSize > bufferSize ? bufferSize : (int)partSize;
                int len = randomFile.read(buf, 0, block);
                out.write(buf, 0, len);
                partSize -= block;
            }while(partSize > 0);
        }
    }

    @GetMapping("/region")
    public ResponseEntity<ResourceRegion> videoRegion(@RequestHeader HttpHeaders headers) throws Exception {
        Resource resource = new FileSystemResource(VIDEO_PATH);

        long chunkSize = 1024 * 1024;
        long contentLength = resource.contentLength();

        ResourceRegion region;

        try {
            HttpRange httpRange = headers.getRange().stream().findFirst().get();
            long start = httpRange.getRangeStart(contentLength);
            long end = httpRange.getRangeEnd(contentLength);
            long rangeLength = Long.min(chunkSize, end -start + 1);

            region = new ResourceRegion(resource, start, rangeLength);
        } catch (Exception e) {
            long rangeLength = Long.min(chunkSize, contentLength);
            region = new ResourceRegion(resource, 0, rangeLength);
        }

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES))
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.parseMediaType("video/mp4")))
                .header("Accept-Ranges", "bytes")
                .eTag(VIDEO_PATH)
                .body(region);
    }

}
