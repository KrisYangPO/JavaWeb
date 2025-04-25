package Case3;

import java.security.MessageDigest;

public class SimplePasswordHash {
	
	public static String hashPassword(String password) throws Exception {
		// 使用 SHA-256 雜湊演算法，定義哪個演算法。
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		// 將 Password 進行雜湊 (建立 byte 陣列)
		byte[] hashByte = md.digest(password.getBytes());
		
		// 將 byte 轉成 16 進位字串(比較好儲存)：
		StringBuilder sb = new StringBuilder();
		for (byte b: hashByte) {
			// 兩位數，如果是個位數就在前面加零
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception {
		String password = "1234";
		String hash = hashPassword(password);
		System.out.printf("password: %s hash: %s%n length: %d%n", password, hash, hash.length());
	}
}
