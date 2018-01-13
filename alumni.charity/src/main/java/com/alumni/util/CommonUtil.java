package com.alumni.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.util.DigestUtils;

@SuppressWarnings("restriction")
public class CommonUtil {

	private static String ALGORITHM = "PBKDF2WithHmacSHA1";
	private static int ITERATION_COUNT = 65536;
	private static int KEY_LENGTH = 128;

	public static String generateUniqueString(int length) {
		Random r = new SecureRandom();
		byte[] b = new byte[length];
		r.nextBytes(b);
		String str = DigestUtils.md5DigestAsHex(b);
		return str;
	}
	
	public static String hashPassword(String password, String salt) 
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		KeySpec spec = new PBEKeySpec(password.toCharArray(),
				DatatypeConverter.parseHexBinary(salt),
				ITERATION_COUNT, KEY_LENGTH);
		SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
		byte[] hash = f.generateSecret(spec).getEncoded();

		return DatatypeConverter.printHexBinary(hash);
		
	}

	public static void logInternalError(org.slf4j.Logger logger,Exception e)
	{
		StringBuilder s = new StringBuilder(e.getMessage() + "\n\t");

		StackTraceElement[] arrElement = e.getStackTrace();
		for (StackTraceElement element : arrElement) {
			s.append(element.toString() + "\n\t");
		}
		
		logger.error(s.toString());
	}

}
