package com.alumni.bean;

import org.springframework.web.multipart.MultipartFile;

public class UploadReceiptBean {
	
	private MultipartFile multipartFile;
	private String amount;
	
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
