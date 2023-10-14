package com.example.multimodule.servlet.uploadingfiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 뷰에서 보여줄 경로를 설정 -> yml 외부 설정
 */
@Configuration
public class StorageConfig {

    // 외부설정 가져올때 Lombok의 Value사용하지 않는다.
    // Lombok의 Value는 속성을 불변으로 만들때 사용 -> private final 필드 + Getter, equals(), hashCode(), toString() 생성
    @Value("${storage.location}")
    private String storageLocation;

    // Path 객체를 반환하는 bean
    @Bean
    public Path storagePath() {
        return Paths.get(storageLocation);
    }
}
