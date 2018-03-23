package com.alumni.service;

public interface FileService {
	public void uploadFile(String fileName, byte[] bytes) throws Exception;
	public byte[] readFile(String fileName) throws Exception;
}
