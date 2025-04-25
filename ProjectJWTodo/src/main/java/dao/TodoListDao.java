package dao;

import java.util.List;

import model.entity.Todo;

public interface TodoListDao {
	
	// 查詢所有 Todo List 資料
	public abstract List<Todo> findAllTodos();
	
	// 查詢單筆 Todo List 資料
	public abstract Todo getTodo(Integer id);
	
	// 新增 Todo List 資料
	public abstract void addTodo(Todo todo);
	
	// 修改指定 Todo completed 完成與否
	public abstract void updateTodoComplete(Integer id, Boolean completed);
	
	// 修改指定 Todo Text
	public abstract void updateTodoText(Integer id, String text);
	
	// 刪除指定 Todo 紀錄 
	public abstract void deleteTodo(Integer id);
}
