package com.example.springbreaking.uploadingfiles.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * 리소스와 경로를 다루는 인터페이스 - 추상화를 위해 코드 블록은 적지 않는다.
 * java에서 인터페이스의 메소드는 디폴트로 public속성을 가진다.
 */
public interface StorageService {

	void init();

	void store(MultipartFile file);

	// Path 객체를 스트림 형태로 반환 ( Path - java의 파일, 디렉토리 경로를 나타내는 인터페이스 )
	Stream<Path> loadAll();

	// filename의 Path 경로를 반환
	Path load(String filename);

	// 외부 리소를 추상화한 Resource 인터페이스를 반환, 리소스 접근용
	Resource loadAsResource(String filename);

	void deleteAll();

}