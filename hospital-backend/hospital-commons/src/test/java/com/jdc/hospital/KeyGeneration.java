package com.jdc.hospital;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.jdc.onestop.hospital.security.SecretKeys;

public class KeyGeneration {

	@Test
	void generate() {
		
		var key1 = SecretKeys.getKey();
		var value1 = SecretKeys.keyToString(key1);
		
		System.out.println(value1);
		
		var key2 = SecretKeys.stringToKey(value1);
		var value2 = SecretKeys.keyToString(key2);
		
		System.out.println(value2);
		
		assertEquals(value1, value2);
	}
}
