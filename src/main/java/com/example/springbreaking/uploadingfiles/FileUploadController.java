package com.example.springbreaking.uploadingfiles;

import com.example.springbreaking.uploadingfiles.exception.StorageFileNotFoundException;
import com.example.springbreaking.uploadingfiles.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;


/**
 * 파일 업로드 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class FileUploadController {

    // 추상화된 인터페이스를 의존 - 유연성
    private final StorageService storageService;

    /**
     * Thymeleaf를 사용하면 String 반환을 src/main/resources/templates/ 내부의 html로 매핑
     * 
     * 디렉토리의 모든 파일을 가져와 모델에 전달
     * MvcUriComponentsBuilder.fromMethodName()를 통해서 리소스를 다운받을 URL을 제공
     *
     * @param model
     * @return
     * @throws IOException
     */
    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute("files", storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));
        
        return "uploadForm";
    }


    /**
     * 뷰에서 제공받은 url을 받아서 리소스를 반환 (다운로드)
     * 
     * `.+` : 정규표현식으로 파일명에 `.`이 포함될 수 있음 -> ex) image.jpg
     * Content-Disposition : 헤더를 통해 다운로드 가능하도록 함
     * .body(file) : 리소스를 반환
     *
     * @param filename
     * @return
     */
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        // 리소스 가져오기
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


    /**
     * submit -> 파일을 저장
     * addFlashAttribute : 리다이렉션 후 한번만 표시 - 새로고침하면 메세지는 사라짐 + listUploadedFiles에 의해서 파일 다운로드 URL 뷰에 생성
     *
     * @param file
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }


    /**
     * ResponseEntity는 HttpEntity를 구현한 클래스로써 상태코드와 응답데이터를 반환한다.
     * HttpEntity는 다양한 상태코드를 응답하지 못한다. ( 기본 200 )
     * 응답에 따른 다양한 상태코드를 응답하기 위해서는 ResonseEntity를 사용해야 한다.
     *
     * @param exc
     * @return
     */
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}