package com.example.springbreaking.uploadingfiles.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 외부설정(properties or yml)의 속성을 java객체에 바인딩
 */
@ConfigurationProperties("storage")
public class StorageProperties {

	// 프로퍼티값이 지정되지 않았을때 사용되는 디폴트 값
	private String location = "upload-dir";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}