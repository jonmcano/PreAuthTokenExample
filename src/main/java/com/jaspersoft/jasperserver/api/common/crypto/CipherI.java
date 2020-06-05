package com.jaspersoft.jasperserver.api.common.crypto;

public interface CipherI {
	public String decrypt(String encryptedToken);
	public String encrypt(String encryptedToken);
	
}
