package com.todolist.todolist.todo.model;

import java.util.List;

public class TodoItemAdapter {

    // todoItemRequest -> todoItem
    public static TodoItem todoItem(final TodoItemRequest todoItemRequest){
        if (todoItemRequest == null) return null;
        return TodoItem.builder().title(todoItemRequest.getTitle()).done(todoItemRequest.isDone()).build();
    }

    // todoItem + error msg -> todoItemResponse
    public static TodoItemResponse todoItemResponse(final TodoItem todoItem, final String errors){
        return TodoItemResponse.builder().todoItem(todoItem).errors(errors).build();
    }

    // List<TodoItem> + error mgs -> todoItemListResponse
    public static TodoItemListResponse todoItemListResponse(final List<TodoItem> todoItems, final String errors){
        return TodoItemListResponse.builder().todoItems(todoItems).errors(errors).build();
    }
}
