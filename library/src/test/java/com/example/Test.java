package kr.go.lawcs.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadVideoFromLink {
    public static void main(String[] args) {
        String url = "https://scontent-ssn1-1.cdninstagram.com/o1/v/t16/f1/m86/A24EFB2A26F7B148DE2E17FFDFB301A3_video_dashinit.mp4?efg=eyJ4cHZfYXNzZXRfaWQiOjEzNjQ1NDA0MjExOTI2NTcsInZlbmNvZGVfdGFnIjoieHB2X3Byb2dyZXNzaXZlLklOU1RBR1JBTS5DTElQUy5DMy43MjAuZGFzaF9iYXNlbGluZV8xX3YxIn0&_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=107&vs=4db14ad772af9c24&_nc_vs=HBksFQIYUmlnX3hwdl9yZWVsc19wZXJtYW5lbnRfc3JfcHJvZC9BMjRFRkIyQTI2RjdCMTQ4REUyRTE3RkZERkIzMDFBM192aWRlb19kYXNoaW5pdC5tcDQVAALIAQAVAhg6cGFzc3Rocm91Z2hfZXZlcnN0b3JlL0dQR19sQnRqbVNnVDdZUUVBQTA2Qk9wMmpXOVNicV9FQUFBRhUCAsgBACgAGAAbAogHdXNlX29pbAExEnByb2dyZXNzaXZlX3JlY2lwZQExFQAAJqLvsbHawuwEFQIoAkMzLBdAYY3bItDlYBgSZGFzaF9iYXNlbGluZV8xX3YxEQB1_gcA&ccb=9-4&oh=00_AYB0hSdEI7iVCBP3eOufQhM6FrVE9s4iY9oMCxOpUaCpdg&oe=671CE71B&_nc_sid=1d576d";
        downloadVideo(url);
    }
    private static Long downloadVideo(String videoUrl) {
        Long fileDtlId = null;
        try {
            URL downloadUrl = new URL(videoUrl);
            HttpURLConnection connection = (HttpURLConnection) downloadUrl.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestMethod("GET");

            // 응답 코드 확인
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String fileName = "video_.mp4";
                File videoFile = new File("html" + File.separator + fileName);

                try (InputStream inputStream = connection.getInputStream();
                     FileOutputStream outputStream = new FileOutputStream(videoFile)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            } else {
                System.err.println("HTTP 응답 코드가 올바르지 않습니다: " + responseCode);
            }
            return fileDtlId;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
