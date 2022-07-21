package com.todolist.todolist.todo;


import com.todolist.todolist.todo.model.TodoItem;
import com.todolist.todolist.todo.model.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//GET, POST, PUT, DELETE 작성
@Service
public class TodoItemService {
    // GET : 전체 todo를 가져온다

    @Autowired
    private TodoItemRepository todoRepo;
    public List<TodoItem> getTodos(){
        List<TodoItem> todos = todoRepo.findAll();

        if (!todos.isEmpty()) return todos;
        else throw new IllegalArgumentException("no such data");
    }

    // GET : 특정 todo를 가져온다
    public TodoItem getTodoById(final Long id){
        return todoRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("no such data"));
    }

    // POST : 새로운 todo를 생성한다
    public TodoItem createTodo(final TodoItem createTodoitem){
        if(createTodoitem == null) throw new IllegalArgumentException("todo item cannot be null");
        return todoRepo.save(createTodoitem);
    }

    // PUT : 기존 todo를 수정한다
    public TodoItem updateTodo(final Long id, final TodoItem updateTodoItem){
        TodoItem todoItem = getTodoById(id);
        todoItem.setTitle(updateTodoItem.getTitle());
        todoItem.setDone(updateTodoItem.isDone());

        return todoRepo.save(todoItem);

    }

    // DELETE : 기존 todo를 삭제한다
    public void deleteTodo(final Long id){
        todoRepo.deleteById(id);
    }
}
