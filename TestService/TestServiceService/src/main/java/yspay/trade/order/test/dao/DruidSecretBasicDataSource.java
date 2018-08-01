package yspay.trade.order.test.dao;

import hikefa.core.spring.util.SecretBasicDataSource;
import hikefa.core.util.security.JDES;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.druid.pool.DruidDataSource;

public class DruidSecretBasicDataSource extends DruidDataSource {

	/**
	 * 意义，目的和功能，以及被用到的地方<br>
	 */

	private static final long serialVersionUID = 1L;

	// des���� + base64
	public synchronized void setPassword(String password) {
		String newpassword = decryptDB(password);
		super.setPassword(newpassword);
	}

	public synchronized void setUsername(String username) {
		String newusername = decryptDB(username);
		super.setUsername(newusername);
	}

	public static void main(String[] args) throws Exception {
		JDES des = new JDES();
		des.SetKey("antlym98".getBytes());
		String password = "abc12";
		byte[] b1 = des.doECBEncrypt(password.getBytes(), password.length());
		byte[] b2 = Base64.encodeBase64(b1);
		String pp = new String(b2);
		System.out.println(pp);

		new SecretBasicDataSource().setPassword(pp);

	}

	public static String decryptDB(String password) {
		byte[] secpassword = Base64.decodeBase64(password);
		JDES des = new JDES();
		des.SetKey("antlym98".getBytes());
		String newpassword = "";
		try {

			byte[] newbyte = des.doECBDecrypt(secpassword, secpassword.length);
			newpassword = new String(newbyte);
		} catch (Exception e) {
			e.printStackTrace();
			newpassword = "";
		}
		return newpassword;
	}

}
