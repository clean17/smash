package com.example.springbreaking.uploadingfiles.storage;

import com.example.springbreaking.uploadingfiles.exception.StorageException;
import com.example.springbreaking.uploadingfiles.exception.StorageFileNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

/**
 * Impl 클래스 - 서비스 인터페이스와 분리
 *  - 추상화의 목적, 재사용의 목적, 확장성의 목적
 *  - 설계 방향이 달라진다면 새로운 Impl 구현 클래스를 만들면 된다.
 *  - 테스트용 클래스를 분리시킬 수 있다.
 *
 */
@Service
@RequiredArgsConstructor
public class FileSystemStorageServiceImpl implements StorageService {

	private final Path rootLocation;

	/**
	 * 디렉토리가 없다면 생성한다.
	 * 외부설정이 아래처럼 되어 있다면 'someDirectory'가 존재 하지 않을 경우 디렉토리를 생성
	 *
	 * storage:
	 *   location: D:\someDirectory
	 */
	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}


	/**
	 * 리소스를 받아서 저장
	 *
	 * destinationFile에는 파일을 저장할 절대 경로를 초기화
	 * getInputStream() 통해 입력 스트림을 얻은 뒤 저장할 경로에 저장
	 * StandardCopyOption.REPLACE_EXISTING : 덮어쓰기
	 * @param file
	 */
	@Override
	public void store(MultipartFile file) {
		try {
			// 리소스가 전달되지 않았으면 익셉션
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}

			// 리소스를 저장할 절대 경로를 설정
			Path destinationFile = this.rootLocation.resolve(
							Paths.get(file.getOriginalFilename()))
					.normalize().toAbsolutePath();

			// 외부에 저장될 때 익셉션
			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				// This is a security check
				throw new StorageException(
						"Cannot store file outside current directory.");
			}

			// 스트림을 통해 리소스를 저장
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,
						StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}


	/**
	 * 디렉토리 내부의 파일과 디렉토리의 정보를 가져오기 위한 메소드
	 * 지정 혹은 생성된 디렉토리의 모든 리소스의 Path를 스트림으로 반환
	 *
	 * Files.walk() : 재귀적 탐색, 두번째 인자는 탐색할 뎁스 지정
	 * filter : 부모 디렉토리 경로를 제외
	 * map : relativize 메소드로 상대경로를 반환
	 * @return
	 */
	@Override
	public Stream<Path> loadAll() {
		try {
			// 직계 자식만 탐색
			return Files.walk(this.rootLocation, 1)
					.filter(path -> !path.equals(this.rootLocation))
					.map(this.rootLocation::relativize);
		}
		catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	// 루트 디렉토리 + 파일 이름 -> Path 반환
	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}


	/**
	 * 리소스 가져오기
	 * @param filename
	 * @return
	 */
	@Override
	public Resource loadAsResource(String filename) {
		try {
			// 가져온 Path를 통해 리소스를 반환 (다운로드)
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			// 리소스를 찾지 못했을 때
			else {
				throw new StorageFileNotFoundException(
						"Could not read file: " + filename);
			}
		}
		// 경로가 잘못되었을 때
		catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	/**
	 * 생성한 디렉토리를 삭제 - CommandLineRunner에 의해 서버 실행 시 디레토리 리셋
	 */
	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

}