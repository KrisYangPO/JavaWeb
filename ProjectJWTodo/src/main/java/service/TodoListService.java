package service;

import java.util.List;

import model.dto.TodoDTO;

public interface TodoListService {
	// 查詢所有 TodoDTO List 資料
	public abstract List<TodoDTO> findAllTodos();
	
	// 查詢單筆 Todo List 資料
	public abstract TodoDTO getTodo(Integer id);
	
	// 新增 Todo List 資料
	public abstract void addTodo(String text, Boolean completed);
	
	// 修改指定 Todo completed 完成與否
	public abstract void updateTodoComplete(Integer id, Boolean completed);
	
	// 修改指定 Todo Text
	public abstract void updateTodoText(Integer id, String text);
	
	// 刪除指定 Todo 紀錄 
	public abstract void deleteTodo(Integer id);
}
