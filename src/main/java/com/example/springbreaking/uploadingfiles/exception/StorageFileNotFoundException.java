package com.example.springbreaking.uploadingfiles.exception;

/**
 * 파일을 못찾을 경우 발생시키는 커스텀 익셉션
 */
public class StorageFileNotFoundException extends StorageException {

	public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}