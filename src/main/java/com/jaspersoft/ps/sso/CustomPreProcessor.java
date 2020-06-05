package com.jaspersoft.ps.sso;

import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.jaspersoft.jasperserver.api.common.crypto.CipherI;

public class CustomPreProcessor implements CipherI {
	private final static Logger log = LogManager.getLogger(CustomPreProcessor.class);

	// Default Key and IV
	private String key = "ijwrfbbvwouhwre3";  // Has to be 16 charters
	private String iv = "SomeRandomIVText";   // Has to be 16 charters

	private byte[] keyArray;
	private byte[] ivArray;
	private SecretKeySpec secretKey;
	private Cipher cipher;

    public CustomPreProcessor() {
    	try { 
    		keyArray = key.getBytes(StandardCharsets.UTF_8);
    		secretKey = new SecretKeySpec(keyArray, "AES");
    		ivArray = iv.getBytes(StandardCharsets.UTF_8);
    		cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");   
    	} catch (Exception e) {
    		log.error("Could not load SSO Processor", e);
    	}
	}
	
	public void setKey(String key) {
		this.key = key;
		keyArray = key.getBytes(StandardCharsets.UTF_8);
		secretKey = new SecretKeySpec(keyArray, "AES");
	}

	public void setIv(String iv) {
		this.iv = iv;
		ivArray = iv.getBytes(StandardCharsets.UTF_8);
	}

	public String encrypt(String plainText)
	{
		 try {			
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(ivArray));
			return Hex.encodeHexString(cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8)));
		 } catch (Exception e) {
			 if (log.isDebugEnabled())
				 log.debug(e.getMessage());
			 return null;
		 }
	 }

	 public String decrypt(String messageHex) 
	 {
		 try {
			cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivArray));
			return new String(cipher.doFinal(Hex.decodeHex(messageHex.toCharArray())));
		 } catch (Exception e) {
			 if (log.isDebugEnabled())
				 log.debug(e.getMessage());
			return null;
		 }
	}
}
    
