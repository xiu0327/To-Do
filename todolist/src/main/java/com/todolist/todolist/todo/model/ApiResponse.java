package com.todolist.todolist.todo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ApiResponse<T> {
    private T data;
    private String errors;
}
