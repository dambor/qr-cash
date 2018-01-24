package br.com.dambor.qrcash.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author saulo.borges
 *
 */
public final class CryptUtil {

	/**
	 * Encrypt text according to BCrypt strong hashing function.
	 * 
	 * @param text to be encrypted
	 * @return
	 */
	public static String encrypt(String text) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(text);
	}
}
