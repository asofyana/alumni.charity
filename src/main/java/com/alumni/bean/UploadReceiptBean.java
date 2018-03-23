package com.alumni.bean;

import org.springframework.web.multipart.MultipartFile;

public class UploadReceiptBean {
	
	private MultipartFile multipartFile;
	private String totalAmount;
	private String committedAmount;
	private String uncommittedAmount;
	
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getCommittedAmount() {
		return committedAmount;
	}
	public void setCommittedAmount(String committedAmount) {
		this.committedAmount = committedAmount;
	}
	public String getUncommittedAmount() {
		return uncommittedAmount;
	}
	public void setUncommittedAmount(String uncommittedAmount) {
		this.uncommittedAmount = uncommittedAmount;
	}
	
}
