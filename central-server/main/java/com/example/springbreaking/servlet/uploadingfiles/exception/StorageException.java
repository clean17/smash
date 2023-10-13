package com.example.springbreaking.servlet.uploadingfiles.exception;

/**
 * 스토리지와 관련된 커스텀 익셉션
 */
public class StorageException extends RuntimeException {

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}