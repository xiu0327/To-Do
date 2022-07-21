package com.todolist.todolist.todo.model;

import lombok.Builder;

// 단일 todo를 감싸는 Response
public class TodoItemResponse extends ApiResponse<TodoItem>{
    @Builder
    public TodoItemResponse(final TodoItem todoItem, final String errors){
        this.setData(todoItem);
        this.setErrors(errors);
    }
}
