package com.example.multimodule.servlet.uploadingfiles.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 외부설정(properties or yml)의 속성을 java객체에 바인딩
 */
@ConfigurationProperties("storage")
@Getter
@Setter
public class StorageProperties {

	// 디폴트 값
	private String location = "upload-dir";
}