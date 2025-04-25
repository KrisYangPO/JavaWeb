package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.List;

import model.entity.Todo;

// 繼承 BaseDao 將“資料庫連線相關程式” 繼承自這個 class
// 實作 TodoListDao 將 "DAO存取功能" 實作在這個 DAO 物件裡。
public class TodoListDaoImpl extends BaseDao implements TodoListDao {

	@Override
	public List<Todo> findAllTodos() {
		List<Todo> todos = new ArrayList<>();
		
		String sql = "select id, text, completed from todo order by id";
		
		// conn 來自 BaseDao 父類別所建立好的連線物件
		try(Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			
			// rs 根據 SQL statement 取得每一筆資料，建立成 ResultSet 陣列，
			// 接著將從 SQL 取出的資訊建立出 Todo Entity，再用 List 集合儲存。
			while(rs.next()) {
				
				// rs.get 根據每個欄位 "id", "text", "completed" 三個 SQL 欄位資訊，
				// 傳遞給 Todo Entity 物件相對應的 setter，儲存 Todo 屬性內容。
				Todo todo = new Todo();
				todo.setId(rs.getInt("id"));
				todo.setText(rs.getString("text"));
				todo.setCompleted(rs.getBoolean("completed"));
				// 加入 List 陣列
				todos.add(todo);
			}				
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return todos;
	}

	
	@Override
	public Todo getTodo(Integer id) {
		String sql = "select id, text, completed from todo where id=?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			// 第一個 sql statement 裡面的語句裡，id=? 是第一個問號，
			// 所以 setInt(1, id) 裡面的 1 就是將 sql 第一個? 儲存成 id (來自方法參數)
			pstmt.setInt(1, id);
			
			// PreparedStatement 已經將 sql 加入 pstmt (編譯完成)，
			// 這裡就需要把 sql 加入 executeQuery()。
			try(ResultSet rs = pstmt.executeQuery();){
				
				if(rs.next()) {
					Todo todo = new Todo();
					todo.setId(rs.getInt("id"));
					todo.setText(rs.getString("text"));
					todo.setCompleted(rs.getBoolean("completed"));
					
					return todo;
				}	
			}
			// 這裡不用再 catch，因為兩個 try 都會產生 SQLException。
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public void addTodo(Todo todo) {
		String sql = "insert into todo(text, completed) value(?,?)";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, todo.getText());
			pstmt.setBoolean(2, todo.getCompleted());
			
			// 使用 executeUpdate() 會產生整數回報，告訴你有多少資料被異動。
			// 0筆表示沒有被新增或是重複新增
			int rowcount = pstmt.executeUpdate();
			System.out.println("新增 todo 筆數：" + rowcount);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void updateTodoComplete(Integer id, Boolean completed) {
		// 修改 completed 內容，根據哪個 id
		String sql = "update todo set completed =? where id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setBoolean(1, completed);
			pstmt.setInt(2, id);
			
			int rowcount = pstmt.executeUpdate();
			System.out.println("修改 todo 筆數：" + rowcount);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateTodoText(Integer id, String text) {
		// 修改 text 內容，根據哪個 id
		String sql = "update todo set text =? where id = ?";
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, text);
			pstmt.setInt(2, id);
			
			int rowcount = pstmt.executeUpdate();
			System.out.println("修改 todo 筆數：" + rowcount);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void deleteTodo(Integer id) {
		String sql = "delete from todo where id=?";
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setInt(1, id);
			
			int rowcount = pstmt.executeUpdate();
			System.out.println("刪除 todo 筆數：" + rowcount);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
}// end of DAO implements
