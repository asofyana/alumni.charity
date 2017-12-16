package com.alumni.service.impl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alumni.service.FileService;
import com.alumni.util.CommonUtil;

@Service
public class FileServiceImpl implements FileService {

	@Value("${file.location}")
	private String fileLocation;

	@Value("${file.max.width}")
	private String fileMaxWidth;
	
	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
			
	@Override
	public void uploadFile(String fileName, byte[] bytes) throws Exception {

		String fileExt = fileName.substring(fileName.length() - 3);
		byte[] rBytes = resize(bytes, fileExt, Integer.parseInt(fileMaxWidth));
		File file = new File(fileLocation + "/" + fileName);
		FileOutputStream fous = new FileOutputStream(file);
		fous.write(rBytes);
		fous.flush();
		fous.close();
		
	}

	public static byte[] resize(byte[] img, String fileType, int imageWidth) {
		byte[] imageBytes = null;
		ByteArrayOutputStream outputStream =  null;
		try {
			InputStream in = new ByteArrayInputStream(img);
			BufferedImage originalBufferedImage = ImageIO.read(in);
			in.close();
			
			double resizeFactor = (double) imageWidth / (double) originalBufferedImage.getWidth();
			logger.debug("resizeFactor: " + resizeFactor);

			if (resizeFactor > 1) {
				return img;
			}

			int height = (int) (resizeFactor * originalBufferedImage.getHeight());
			logger.debug("height: " + height);
			logger.debug("type: " + originalBufferedImage.getType());
			
			BufferedImage resizedImage = new BufferedImage(imageWidth, height, BufferedImage.TYPE_INT_RGB);
			
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalBufferedImage, 0, 0, imageWidth, height, null);
			g.dispose();
	
			outputStream = new ByteArrayOutputStream();
			ImageIO.write(resizedImage, fileType, outputStream);
			outputStream.flush();
			imageBytes = outputStream.toByteArray();
			
		} catch (Exception e) {
			CommonUtil.logInternalError(logger, e);
		} finally {
			if (outputStream != null) {
				try {
				outputStream.close();
				} catch (Exception e) {
					CommonUtil.logInternalError(logger, e);
				}
			}
		}
		return imageBytes;
	}

	
}
