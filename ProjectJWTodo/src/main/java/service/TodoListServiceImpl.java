package service;

import java.util.ArrayList;
import java.util.List;

import model.dto.TodoDTO;
import model.entity.Todo;
import dao.*;

// 實作 TodoListService interface，方便後續更改。
public class TodoListServiceImpl implements TodoListService {
	
	// 呼叫 DAO 存取功能的實作物件
	// 就可以呼叫他的方法存取所有 DAO entity 並轉成 DTO 物件
	private TodoListDao dao = new TodoListDaoImpl();
	
	
//	@Override
//	public List<TodoDTO> findAllTodos() {
//		
//		// 呼叫 DAO 物件，執行 findAllTodo() 將所有 todo 建立儲存所有 todo 物件的集合
//		List<Todo> todos = dao.findAllTodos();
//		List<TodoDTO> todoDTOs = new ArrayList<>();
//		
//		for (Todo todo: todos) {
//			todoDTOs.add(transferToDTO(todo));
//		}
//		return todoDTOs;
//	}
	
	// 用 Lambda 寫 findAlltodos():
	public List<TodoDTO> findAllTodos(){
		return dao.findAllTodos().stream()
				.map(todo -> transferToDTO(todo))
				// 也可以寫 (this::transferToDTO) 這個類別裡的方法：transferToDTO
				.toList();
	}
	

	@Override
	public TodoDTO getTodo(Integer id) {
		return transferToDTO(dao.getTodo(id));
	}

	@Override
	public void addTodo(String text, Boolean completed) {
		Todo todo = new Todo(0, text, completed);
		dao.addTodo(todo);
	}

	@Override
	public void updateTodoComplete(Integer id, Boolean completed) {
		dao.updateTodoComplete(id, completed);
	}

	@Override
	public void updateTodoText(Integer id, String text) {
		dao.updateTodoText(id, text);
	}

	@Override
	public void deleteTodo(Integer id) {
		dao.deleteTodo(id);
	}
	
	
	// Entity vs DTO 轉換
	// 將 Todo Entity 轉換成 TodoDTO 
	private TodoDTO transferToDTO(Todo todo) {
		return new TodoDTO(todo.getId(), todo.getText(), todo.getCompleted());
	}
	
	// 將 TodoDTO 轉換成 Todo Entity
	private Todo transferToDTO(TodoDTO todoDTO) {
		return new Todo(todoDTO.getId(), todoDTO.getText(), todoDTO.getCompleted());
	}
	
}
