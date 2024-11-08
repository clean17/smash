package com.example;

import org.springframework.mock.web.MockMultipartFile;

import java.io.*;
import java.util.UUID;

public class YtdlpTest {

    private String getYtDlpPath() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return "C:\\yt-dlp.exe";
        } else { // 리눅스
            return "/usr/local/bin/yt-dlp_linux";
        }
    }

    private String getDownloadDirectoryPath() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return "";
        } else { // 리눅스
            String path = "/tmp/youtube/";
            File directory = new File(path);
            if (!directory.exists()) {
                boolean created = directory.mkdirs();
                if (!created) {
                    throw new RuntimeException("다운로드 디렉토리를 생성하지 못했습니다: " + path);
                }
            }
            return path;
        }
    }

    public void download_youtube_video() {
        Long fileDtlId = null;
        try {
            String youtubeUrl = "https://www.youtube.com/watch?v=Qtkwq_vix8w";
            String ytDlpPath = getYtDlpPath();
            String downloadDirectoryPath = getDownloadDirectoryPath();
            String fileName = "youtube_" + UUID.randomUUID();

            String[] command = {
                    ytDlpPath,
                    "-f", "bestvideo[height<=480]+bestaudio/best[height<=1080]",
                    "--merge-output-format", "mp4",
                    "-o", downloadDirectoryPath + fileName + ".%(ext)s",
                    "--user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36",
                    "--postprocessor-args", "-strict experimental",
                    youtubeUrl
            };

            System.out.print("command = ");
            for (String com : command) {
                System.out.print(com+" ");
            }
            System.out.println("\n");

            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.err.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("exitCode: " + exitCode + "\n");

            // 다운로드된 파일을 MultipartFile로 변환하고 로컬 파일 삭제
            File videoFile = new File(downloadDirectoryPath + fileName+".mp4");
            if (videoFile.exists()) {
                try (InputStream inputStream = new FileInputStream(videoFile)) {

                } catch (Exception e) {
                    throw e;
                } finally {
                    // 스트림을 닫은 후 파일 삭제
                    if (videoFile.exists()) {
                        boolean deleted = videoFile.delete();
                        if (!deleted) {
                            System.err.println("파일 삭제 실패: " + videoFile.getAbsolutePath());
                        }
                    }
                }
            } else {
                System.err.println("다운로드된 비디오 파일을 찾을 수 없습니다: " + downloadDirectoryPath + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
