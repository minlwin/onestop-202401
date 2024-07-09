package com.jdc.onestop.hospital.security;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Jwts;

public class SecretKeys {

	public static SecretKey getKey() {
		return Jwts.SIG.HS512.key().build();
	}
	
	public static String keyToString(SecretKey key) {
		var bytes = key.getEncoded();
		return Base64.getEncoder().encodeToString(bytes);
	}
	
	public static SecretKey stringToKey(String str) {
		var bytes = Base64.getDecoder().decode(str);
		return new SecretKeySpec(bytes, "HmacSHA512");
	}
}
