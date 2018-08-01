
/*
* 文件名：SecretBasicDataSource.java
* 版权：Copyright by www.ysepay.com
* 修改人：Cindy
* 修改时间：2018年5月4日
* 修改内容：
*/

package com.yspay.common.datasource;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecretBasicDataSource extends BasicDataSource {
	final static Logger logger = LoggerFactory
			.getLogger(SecretBasicDataSource.class);

	// des加密 + base64
	public synchronized void setPassword(String password) {
		String newpassword = decryptDB(password);
		super.setPassword(newpassword);
	}

	public synchronized void setUsername(String username) {
		String newusername = decryptDB(username);
		super.setUsername(newusername);
	}

	private String decryptDB(String password) {
		byte[] secpassword = Base64.decodeBase64(password);

		String newpassword = "";
		try {
			Cipher cipher = getCipher("DES", "DES/ECB/PKCS5Padding",
					Cipher.DECRYPT_MODE, null);

			// 现在，获取数据并解密
			byte encryptedData[] = secpassword;/* 获得经过加密的数据 */
			// 正式执行解密操作
			// cipher.update(encryptedData,0,len);
			// byte decryptedData[] = cipher.doFinal();
			byte decryptedData[] = cipher.doFinal(encryptedData, 0,
					secpassword.length);

			newpassword = new String(decryptedData);
		} catch (Exception e) {
			logger.error("密码解密失败", e);
		}
		return newpassword;
	}

	protected Cipher getCipher(String factory, String cipherName, int cryptMode,
			byte[] iv) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		byte rawKeyData[] = "antlym98".getBytes(); /* 用某种方法获取原始密匙数据 */
		// 从原始密匙数据创建一个DESKeySpec对象
		// DESKeySpec dks = new DESKeySpec(rawKeyData);//只能是单DES
		// DESedeKeySpec dks = new DESedeKeySpec(rawKeyData);//3DES
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		// SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(factory);
		// SecretKey key = keyFactory.generateSecret(dks);
		SecretKey key = new SecretKeySpec(rawKeyData, factory);

		// Cipher对象实际完成解密操作
		// using DES in ECB mode
		Cipher cipher = Cipher.getInstance(cipherName);

		// 用密匙初始化Cipher对象
		if (iv != null) {
			IvParameterSpec ips = new IvParameterSpec(iv);
			cipher.init(cryptMode, key, ips, sr);
		} else
			cipher.init(cryptMode, key, sr);

		return cipher;

	}

}
