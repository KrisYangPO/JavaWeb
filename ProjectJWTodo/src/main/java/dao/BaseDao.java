package dao;

import java.sql.Connection;
import java.sql.DriverManager;

// 用來讓不同的 xxxDao 透過 extends BaseDao 可以連接 MySQL
public class BaseDao {
	protected static Connection conn;

	static {
		// 連線參數
		String username = "root";
		String password = "NTUyang33";
		String dbUrl = "jdbc:mysql://localhost:3306/todolist?serverTimezone=Asia/Taipei&characterEncoding=utf-8&useUnicode=true&useSSL=false";
		// 時區參數一定要加：serverTimezone=Asia/Taipei

		// 建立連線
		try {
			// 動態建立物件，存放在專案MavenDependencies/mysql-connector/META-INF/services/java.sql.Driver 文件中
			Class.forName("com.mysql.cj.jdbc.Driver"); // 註冊 MySQL Driver
			conn = DriverManager.getConnection(dbUrl, username, password); // 連線建立
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
