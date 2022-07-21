package com.todolist.todolist.todo.model;

import lombok.Builder;

import java.util.List;

// 여러 개의 todo를 감싸는 Response
public class TodoItemListResponse extends ApiResponse<List<TodoItem>>{
    @Builder
    public TodoItemListResponse(final List<TodoItem> todoItems, final String errors){
        this.setData(todoItems);
        this.setErrors(errors);
    }
}
